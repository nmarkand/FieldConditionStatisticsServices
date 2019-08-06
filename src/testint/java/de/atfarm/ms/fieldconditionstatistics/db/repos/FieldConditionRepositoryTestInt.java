package de.atfarm.ms.fieldconditionstatistics.db.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class FieldConditionRepositoryTestInt {
    
    @Autowired
    private FieldConditionRepository repository;
    
    @Test
    public void findMinimumMaximumAndAverageVegetationTest() {
        FieldCondition fieldCondition = new FieldCondition(new BigDecimal("0.81"), toZonedDateTime("2019-08-05T08:50Z"));
        repository.saveAndFlush(fieldCondition);
        assertNotNull(repository.findById(fieldCondition.getId()));
        
        fieldCondition = new FieldCondition(new BigDecimal("0.85"), toZonedDateTime("2019-04-23T06:50Z"));
        repository.saveAndFlush(fieldCondition);
        final Long storedId = fieldCondition.getId();
        assertNotNull(repository.findById(storedId));
        assertEquals(repository.getOne(storedId).getVegetation(), new BigDecimal("0.85"));
        assertTrue(repository.findAll() instanceof List);
        assertEquals(repository.count(), 2);
        
        FieldStatistics fieldStatistics = repository.findMinimumMaximumAndAverageVegetation();
        // 0.85 vegetation is out of last 30 days range
        assertEquals(fieldStatistics.getMax(), new BigDecimal("0.81"));
        // 0.85 vegetation is out of last 30 days range
        assertEquals(fieldStatistics.getMin(), new BigDecimal("0.81"));
        // 0.85 vegetation is out of last 30 days range
        assertEquals(fieldStatistics.getAvg(), new BigDecimal("0.81"));
    }
    
    private ZonedDateTime toZonedDateTime(final String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return ZonedDateTime.parse(dateTime, formatter.withZone(ZoneId.of("UTC")));
    }
}
