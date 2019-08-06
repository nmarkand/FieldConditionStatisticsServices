package de.atfarm.ms.fieldconditionstatistics.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class StoreFieldConditionRequestTest {
    
    private StoreFieldConditionRequest storeFieldConditionRequest = null;
    
    @Before
    public void setUp() {
        storeFieldConditionRequest = new StoreFieldConditionRequest(new BigDecimal("0.50"), "2019-04-23T08:50Z");
    }
    
    @Test
    public void fieldStatisticsTest() {
        assertNotNull(storeFieldConditionRequest);
        assertEquals(storeFieldConditionRequest.getVegetation(), new BigDecimal("0.50"));
        assertEquals(storeFieldConditionRequest.getOccurrenceAt(), "2019-04-23T08:50Z");
    }
}
