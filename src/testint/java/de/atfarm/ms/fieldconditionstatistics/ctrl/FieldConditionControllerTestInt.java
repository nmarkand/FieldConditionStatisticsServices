package de.atfarm.ms.fieldconditionstatistics.ctrl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class FieldConditionControllerTestInt {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    
    @Test
    public void storeFieldConditionTest() {
        String adr = getApiAdr("/field-conditions");
        StoreFieldConditionRequest storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.92"), "2019-08-05T08:50Z");
        HttpEntity<String> requestEntity = makeStoreFieldConditionRequestEntity(storeRequest);
        ResponseEntity<StoreFieldConditionResponse> result = restTemplate.exchange(adr, HttpMethod.POST, requestEntity,
                StoreFieldConditionResponse.class);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertNotNull(result.getBody().getDbId());
    }
    
    @Test
    public void getFieldStatisticsTest() throws JSONException {
        String adr = getApiAdr("/field-statistics");
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
        };
        
        // We used a wrapper object "vegetation" and hence String (Json) is used as a result instead FieldStatistics.
        // Json string can be parsed further to get to know different elements of json.
        ResponseEntity<String> result = restTemplate.exchange(adr, HttpMethod.GET, HttpEntity.EMPTY, responseType);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(result.getBody());
        
        assertTrue(jsonTree.isJsonObject());
        assertNotNull(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("min"));
        assertNotNull(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("max"));
        assertNotNull(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("avg"));
        assertEquals(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("min"), new JsonPrimitive(0.92));
        assertEquals(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("max"), new JsonPrimitive(0.92));
        assertEquals(jsonTree.getAsJsonObject().get("vegetation").getAsJsonObject().get("avg"), new JsonPrimitive(0.92));
    }
    
    private String getApiAdr(String apiPath) {
        return "http://localhost:" + port + apiPath;
    }
    
    private HttpEntity<String> makeStoreFieldConditionRequestEntity(final StoreFieldConditionRequest storeRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(data2json(storeRequest), headers);
    }
    
    private String data2json(Object data) {
        try {
            return new ObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
