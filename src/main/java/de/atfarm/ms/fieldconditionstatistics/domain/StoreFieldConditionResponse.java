package de.atfarm.ms.fieldconditionstatistics.domain;

public class StoreFieldConditionResponse {
    
    private Long dbId;
    
    public StoreFieldConditionResponse() {
    }
    
    public StoreFieldConditionResponse(Long dbId) {
        this.dbId = dbId;
    }
    
    public Long getDbId() {
        return dbId;
    }
    
    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }
    
    @Override
    public String toString() {
        return "StoreFieldStatisticsResponse [dbId=" + dbId + "]";
    }
}
