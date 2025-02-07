package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.enumformodel.StandardType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StandardWeightDto {
    private UUID standardWeightId;
    private StandardType standardType;
}
