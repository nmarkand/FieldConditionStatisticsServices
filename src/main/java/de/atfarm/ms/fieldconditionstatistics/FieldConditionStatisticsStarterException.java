package de.atfarm.ms.fieldconditionstatistics;

public class FieldConditionStatisticsStarterException extends RuntimeException {
    private static final long serialVersionUID = -3155056304831037735L;
    
    public FieldConditionStatisticsStarterException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public FieldConditionStatisticsStarterException(String message) {
        super(message);
    }
}

