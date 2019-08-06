package de.atfarm.ms.fieldconditionstatistics.domain;

import java.math.BigDecimal;

public class StoreFieldConditionRequest {
    
    private BigDecimal vegetation;
    private String occurrenceAt;
    
    public StoreFieldConditionRequest() {
        
    }
    
    public StoreFieldConditionRequest(BigDecimal vegetation, String occurrenceAt) {
        this.vegetation = vegetation;
        this.occurrenceAt = occurrenceAt;
    }
    
    public BigDecimal getVegetation() {
        return vegetation;
    }
    
    public void setVegetation(BigDecimal vegetation) {
        this.vegetation = vegetation;
    }
    
    public String getOccurrenceAt() {
        return occurrenceAt;
    }
    
    public void setOccurrenceAt(String occurrenceAt) {
        this.occurrenceAt = occurrenceAt;
    }
    
}
