package de.atfarm.ms.fieldconditionstatistics.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class FieldConditionServiceTestInt {
    
    @Autowired
    FieldConditionService fieldConditionService;
    
    StoreFieldConditionRequest storeRequest;
    
    @Test
    public void storeFieldConditionTest() {
        storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.81"), "2019-08-05T08:50Z");
        FieldCondition fieldCondition = fieldConditionService.storeFieldCondition(storeRequest);
        
        assertNotNull(fieldCondition);
        assertNotNull(fieldCondition.getId());
        assertEquals(fieldCondition.getVegetation(), new BigDecimal("0.81"));
        assertEquals(fieldCondition.getOccurrenceAt(), toZonedDateTime("2019-08-05T08:50Z"));
    }
    
    @Test
    public void getFieldStatisticsTest() {
        storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.85"), "2019-04-23T06:50Z");
        fieldConditionService.storeFieldCondition(storeRequest);
        
        storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.92"), "2019-08-05T08:50Z");
        fieldConditionService.storeFieldCondition(storeRequest);
        
        storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.90"), "2019-07-30T12:50Z");
        fieldConditionService.storeFieldCondition(storeRequest);
        
        storeRequest = new StoreFieldConditionRequest(new BigDecimal("0.95"), "2019-05-30T09:50Z");
        fieldConditionService.storeFieldCondition(storeRequest);
        
        FieldStatistics fieldStatistics = fieldConditionService.getFieldStatistics();
        
        assertNotNull(fieldStatistics);
        
        // 0.95 vegetation is out of last 30 days range
        assertNotEquals(fieldStatistics.getMax(), new BigDecimal("0.95"));
        assertEquals(fieldStatistics.getMax(), new BigDecimal("0.92"));
        
        // 0.85 vegetation is out of last 30 days range
        assertNotEquals(fieldStatistics.getMin(), new BigDecimal("0.85"));
        assertEquals(fieldStatistics.getMin(), new BigDecimal("0.90"));
        
        // 0.95 and 0.85 vegetations are out of last 30 days range 
        assertNotEquals(fieldStatistics.getAvg(), new BigDecimal((0.85 + 0.92 + 0.90 + 0.95) / 4.0));
        
        //average (0.92 + 0.90)/2 =.91
        assertEquals(fieldStatistics.getAvg(), new BigDecimal("0.91"));
    }
        
    private ZonedDateTime toZonedDateTime(final String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return ZonedDateTime.parse(dateTime, formatter.withZone(ZoneId.of("UTC")));
    }    
}
