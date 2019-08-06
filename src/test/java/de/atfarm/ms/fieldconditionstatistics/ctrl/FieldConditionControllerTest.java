package de.atfarm.ms.fieldconditionstatistics.ctrl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionResponse;
import de.atfarm.ms.fieldconditionstatistics.services.FieldConditionService;


public class FieldConditionControllerTest {
    
    private FieldConditionService service;
    private FieldConditionController controller;
    
    @Before
    public void setUp() {
        service = mock(FieldConditionService.class);
        controller = new FieldConditionController(service);
    }
    
    @Test
    public void storeFieldConditionTest() {
        StoreFieldConditionRequest request = new StoreFieldConditionRequest(new BigDecimal("0.50"), "2019-04-23T08:50Z");
        
        when(service.storeFieldCondition(request)).thenReturn(new FieldCondition());
        
        ResponseEntity<StoreFieldConditionResponse> result = controller.storeFieldCondition(request);
        
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
    @Test
    public void getFieldStatisticsTest() {
        when(service.getFieldStatistics()).thenReturn(new FieldStatistics());
        
        ResponseEntity<FieldStatistics> result = controller.getFieldStatistics();
        
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
}
