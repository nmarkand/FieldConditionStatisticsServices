package de.atfarm.ms.fieldconditionstatistics.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.db.repos.FieldConditionRepository;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;

public class FieldConditionServiceTest {
    
    private FieldConditionService service;
    private FieldConditionRepository fieldConditionRepository;
    
    @Before
    public void setUp() throws Exception {
        fieldConditionRepository = mock(FieldConditionRepository.class);
        service = new FieldConditionService(fieldConditionRepository);
    }
    
    @Test
    public void storeFieldConditionTest() {
        StoreFieldConditionRequest storeFieldConditionRequest = new StoreFieldConditionRequest(new BigDecimal("0.55"), "2019-08-05T08:50Z");
        FieldCondition fieldCondition = new FieldCondition(new BigDecimal("0.55"), toZonedDateTime("2019-08-05T08:50Z"));
        
        when(fieldConditionRepository.save(Mockito.any(FieldCondition.class))).thenReturn(fieldCondition);
        
        FieldCondition fieldConditionResult = service.storeFieldCondition(storeFieldConditionRequest);
        
        assertNotNull(fieldConditionResult);
        assertNotNull(fieldConditionResult.getId());
        assertEquals(fieldConditionResult.getVegetation(), storeFieldConditionRequest.getVegetation());
        assertEquals(fieldConditionResult.getOccurrenceAt(), toZonedDateTime("2019-08-05T08:50Z"));
        
        verify(fieldConditionRepository).save(ArgumentMatchers.any(FieldCondition.class));
    }
    
    @Test
    public void getFieldStatisticsTest() {
        FieldStatistics mockFieldStatistics = new FieldStatistics(new BigDecimal("0.50"), new BigDecimal("0.55"), new Double("0.50"));
        
        when(fieldConditionRepository.findMinimumMaximumAndAverageVegetation())
        .thenReturn(mockFieldStatistics);
        
        FieldStatistics resultFieldStatistics = service.getFieldStatistics();
        
        assertNotNull(resultFieldStatistics);
        assertEquals(mockFieldStatistics, resultFieldStatistics);
        assertEquals(mockFieldStatistics.getMin(), resultFieldStatistics.getMin());
        assertEquals(mockFieldStatistics.getMax(), resultFieldStatistics.getMax());
        assertEquals(mockFieldStatistics.getAvg(), resultFieldStatistics.getAvg());
    }
    
    @Test
    public void toZonedDateTimeTest() {
        ZonedDateTime zdt = service.toZonedDateTime("2019-08-05T08:50Z");
        assertTrue(service.toZonedDateTime("2019-08-05T08:50Z") instanceof ZonedDateTime);
        assertNotNull(zdt);
        assertEquals(zdt.getZone(), java.time.ZoneId.of("UTC"));
        assertEquals(zdt.getDayOfMonth(), 05);
        assertEquals(zdt.getMonth(), Month.AUGUST);
        assertEquals(zdt.getYear(), 2019);
        assertEquals(zdt.getHour(), 8);
        assertEquals(zdt.toLocalDateTime(), LocalDateTime.of(2019, Month.AUGUST, 05, 8, 50));
        
        // Berlin is 2 hous ahead of UTC.
        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.of(2019, Month.AUGUST, 05, 8, 50), ZoneOffset.UTC);
        ZoneId berlinZone = ZoneId.of("Europe/Berlin");
        ZonedDateTime berlinZoned = utcZoned.withZoneSameInstant(berlinZone);
        LocalDateTime berlinLocal = berlinZoned.toLocalDateTime();
        
        assertEquals(zdt.toLocalDateTime().plusHours(2), berlinLocal);
    }
    
    private ZonedDateTime toZonedDateTime(final String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return ZonedDateTime.parse(dateTime, formatter.withZone(ZoneId.of("UTC")));
    }
}
