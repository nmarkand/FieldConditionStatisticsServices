package de.atfarm.ms.fieldconditionstatistics.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class FieldStatisticsTest {
    
    private FieldStatistics fieldStatistics = null;
    
    @Before
    public void setUp() {
        fieldStatistics = new FieldStatistics(new BigDecimal("0.50"), new BigDecimal("0.55"), new Double("0.60"));
    }
    
    @Test
    public void fieldStatisticsTest() {
        assertNotNull(fieldStatistics);
        assertEquals(fieldStatistics.getMin(), new BigDecimal("0.50"));
        assertEquals(fieldStatistics.getMax(), new BigDecimal("0.55"));
        assertEquals(fieldStatistics.getAvg(), new BigDecimal("0.60"));
    }
}
