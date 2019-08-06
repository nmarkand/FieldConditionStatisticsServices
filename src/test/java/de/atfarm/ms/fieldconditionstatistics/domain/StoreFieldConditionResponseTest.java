package de.atfarm.ms.fieldconditionstatistics.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class StoreFieldConditionResponseTest {
    
    private StoreFieldConditionResponse storeFieldConditionResponse = null;
    
    @Before
    public void setUp() {
        storeFieldConditionResponse = new StoreFieldConditionResponse(new Long(1L));
    }
    
    @Test
    public void fieldStatisticsTest() {
        assertNotNull(storeFieldConditionResponse);
        assertEquals(storeFieldConditionResponse.getDbId(), new Long(1L));
    }
}
