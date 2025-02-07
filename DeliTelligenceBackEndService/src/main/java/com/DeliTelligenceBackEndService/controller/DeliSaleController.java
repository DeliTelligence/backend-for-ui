package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleInputDto;
import com.DeliTelligenceBackEndService.service.DeliServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DeliSaleController {

    private final DeliServiceImpl deliService;

    public DeliSaleController(DeliServiceImpl deliService) {
        this.deliService = deliService;
    }

    @MutationMapping
    public String createSale(@Argument DeliSaleInputDto input){
        return deliService.CreateSale(input);
    }
}
