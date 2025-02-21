package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightDto;
import com.DeliTelligenceBackEndService.service.StandardWeightService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StandardWeightController {

    private final StandardWeightService standardWeightService;

    public StandardWeightController(StandardWeightService standardWeightService) {
        this.standardWeightService = standardWeightService;
    }

    @QueryMapping
    public List<StandardWeightDto> getAllStandardWeights() {
        return standardWeightService.getStandardWeightList();
    }
}
