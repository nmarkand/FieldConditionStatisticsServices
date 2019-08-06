package de.atfarm.ms.fieldconditionstatistics.db.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class FieldConditionTest {
    
    private FieldCondition fieldCondition = null;
    
    @Before
    public void setUp() {
        fieldCondition = new FieldCondition(new BigDecimal("0.50"), toZonedDateTime("2019-04-23T08:50Z"));
    }
    
    @Test
    public void fieldStatisticsTest() {
        assertNotNull(fieldCondition);
        assertEquals(fieldCondition.getVegetation(), new BigDecimal("0.50"));
        assertEquals(fieldCondition.getOccurrenceAt(), toZonedDateTime("2019-04-23T08:50Z"));
    }
    
    private ZonedDateTime toZonedDateTime(final String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return ZonedDateTime.parse(dateTime, formatter.withZone(ZoneId.of("UTC")));
    }
    
}
