package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DailySaleDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.QuantitySaleSplitDto;
import com.DeliTelligenceBackEndService.service.AnalysisService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @QueryMapping
    public List<DailySaleDto> getAllSalesByDate(@Argument String startDate, @Argument String endDate) {
        return analysisService.getDailySalesPercentages(startDate, endDate);
    }
    @QueryMapping
    public float dailySalesTotal(@Argument String date) {
        return analysisService.CalculateSales(date);
    }

    @QueryMapping
    public float totalSales(){
        return analysisService.totalSales();}

    @QueryMapping
    public List<QuantitySaleSplitDto> getSalesByQuantity(){
        return analysisService.getSalesByQuantity();
    }
}
