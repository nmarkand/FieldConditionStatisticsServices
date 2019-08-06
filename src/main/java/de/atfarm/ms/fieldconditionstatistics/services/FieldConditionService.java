package de.atfarm.ms.fieldconditionstatistics.services;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.db.repos.FieldConditionRepository;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;
import de.atfarm.ms.fieldconditionstatistics.domain.StoreFieldConditionRequest;

@Service
public class FieldConditionService {
    
    private static final Logger log = LoggerFactory.getLogger(FieldConditionService.class);
    FieldConditionRepository fieldConditionRepository;
    
    @Autowired
    public FieldConditionService(FieldConditionRepository fieldConditionRepository) {
        this.fieldConditionRepository = fieldConditionRepository;
    }
    
    public FieldStatistics getFieldStatistics() {
        log.info("getFieldStatistics");
        return fieldConditionRepository.findMinimumMaximumAndAverageVegetation();
    }
    
    public FieldCondition storeFieldCondition(final StoreFieldConditionRequest storeFieldConditionRequest) {
        log.info("storeFieldCondition " + storeFieldConditionRequest.getVegetation() + " " + storeFieldConditionRequest.getOccurrenceAt());
        BigDecimal vegitation = storeFieldConditionRequest.getVegetation();
        ZonedDateTime zonedDateTime = toZonedDateTime(storeFieldConditionRequest.getOccurrenceAt());
        return fieldConditionRepository.save(new FieldCondition(vegitation, zonedDateTime));
    }
    
    // Protected for testing
    protected ZonedDateTime toZonedDateTime(final String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return ZonedDateTime.parse(dateTime, formatter.withZone(ZoneId.of("UTC")));
    }
}