package de.atfarm.ms.fieldconditionstatistics;

public class FieldConditionStatisticsServicesException extends RuntimeException {
    private static final long serialVersionUID = -3155056304831037735L;
    
    public FieldConditionStatisticsServicesException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public FieldConditionStatisticsServicesException(String message) {
        super(message);
    }
}

