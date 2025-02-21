package com.DeliTelligenceBackEndService.exception;

public class ProductNotWeighedException extends RuntimeException {

        private static final long serialVersionID = 1L;
        private Object fieldValue;
        private float weightValue;

    public ProductNotWeighedException(Object fieldValue, float weightValue) {
        super(String.format("%s Not Weighed returned %s", fieldValue, weightValue));
        this.fieldValue = fieldValue;
    }
}
