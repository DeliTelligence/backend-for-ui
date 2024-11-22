package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.DeliSaleInputDto;
import com.DeliTelligenceBackEndService.service.DeliServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class DeliSaleController {

    private final DeliServiceImpl deliService;

    public DeliSaleController(DeliServiceImpl deliService) {
        this.deliService = deliService;
    }

//    public String createSale(DeliSaleInputDto deliSaleInputDto){
//        return deliService.createSale(deliSaleInputDto);
//    }
}
