package de.atfarm.ms.fieldconditionstatistics.db.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MS_FIELD_CONDITION")
public class FieldCondition {

	@Id
    @GeneratedValue(generator = "FieldConditionSeq")
    @SequenceGenerator(name = "FieldConditionSeq", sequenceName = "SEQ_TBL_MS_FIELD_CONDITION", allocationSize = 1)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;
    
    @Column(name = "vegetation")
    private BigDecimal vegetation;
    
    //Thread safe and immutable
    @Column(name = "occurrence_at")
    private ZonedDateTime occurrenceAt;
    
	public FieldCondition() {
	}

	public FieldCondition(BigDecimal vegetation, ZonedDateTime occurrenceAt) {
		this.vegetation = vegetation;
		this.occurrenceAt = occurrenceAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getVegetation() {
		return vegetation;
	}

	public void setVegetation(BigDecimal vegetation) {
		this.vegetation = vegetation;
	}

	public ZonedDateTime getOccurrenceAt() {
		return occurrenceAt;
	}

	public void setOccurrenceAt(ZonedDateTime occurrenceAt) {
		this.occurrenceAt = occurrenceAt;
	}

	@Override
	public String toString() {
		return "FieldCondition [id=" + id + ", vegetation=" + vegetation + ", "
				+ "occurrenceAt=" + occurrenceAt + "]";
	}
}
