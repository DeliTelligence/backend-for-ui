package com.DeliTelligenceBackEndService.helper;

import java.util.Arrays;
import java.util.List;

public class EditCustomMapping {
    public static boolean isDifferentOrNull(Object entityValue, Object dtoValue) {
        if (dtoValue == null) {
            return false;
        }
        if (dtoValue.equals("")) {
            return false;
        }
        if (entityValue == null) {
            return true;
        }
        if (entityValue.getClass().isArray() && dtoValue.getClass().isArray()) {
            return !Arrays.equals((byte[]) entityValue, (byte[]) dtoValue);
        }
        if (entityValue instanceof List && dtoValue instanceof List) {
            return !entityValue.equals(dtoValue);
        }
        return !dtoValue.equals(entityValue);
    }
}