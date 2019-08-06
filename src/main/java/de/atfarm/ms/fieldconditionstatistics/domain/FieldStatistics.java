package de.atfarm.ms.fieldconditionstatistics.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName("vegetation")
public class FieldStatistics {
    
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal avg;
    
    public FieldStatistics() {
        
    }
    
    public FieldStatistics(BigDecimal min, BigDecimal max, Double avg) {
        this.min = min == null ? BigDecimal.ZERO : min;
        this.max = max == null ? BigDecimal.ZERO : max;
        this.avg = avg == null ? BigDecimal.ZERO : new BigDecimal(Double.toString(avg)).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getMin() {
        return min;
    }
    
    public void setMin(BigDecimal min) {
        this.min = min;
    }
    
    public BigDecimal getMax() {
        return max;
    }
    
    public void setMax(BigDecimal max) {
        this.max = max;
    }
    
    public BigDecimal getAvg() {
        return avg;
    }
    
    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }
}
