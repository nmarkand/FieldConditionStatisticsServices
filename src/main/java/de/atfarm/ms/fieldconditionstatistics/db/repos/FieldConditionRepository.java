package de.atfarm.ms.fieldconditionstatistics.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.atfarm.ms.fieldconditionstatistics.db.entities.FieldCondition;
import de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics;

public interface FieldConditionRepository extends JpaRepository<FieldCondition, Long> {
    
    @Query("SELECT new de.atfarm.ms.fieldconditionstatistics.domain.FieldStatistics(MIN(F.vegetation), MAX(F.vegetation), AVG(F.vegetation)) FROM FieldCondition F WHERE F.occurrenceAt > SYSDATE-30")
    public FieldStatistics findMinimumMaximumAndAverageVegetation();
}

