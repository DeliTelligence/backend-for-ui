package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.DeliSale;
import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliSaleMapper;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DailySaleDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleFetchDto;
import com.DeliTelligenceBackEndService.helper.DateUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService {

    private final DeliServiceImpl deliService;
    private final DeliSaleMapper deliSaleMapper;

    public AnalysisService(DeliServiceImpl deliService, DeliSaleMapper deliSaleMapper) {
        this.deliService = deliService;
        this.deliSaleMapper = deliSaleMapper;
    }

    public List<DailySaleDto> getDailySalesPercentages(String startDate, String endDate) {
        // Define the date format that matches your input string
        LocalDate localDateStart = DateUtil.parseDate(startDate);
        LocalDate localDateEnd = null;

        if (endDate != null && !endDate.isEmpty()) {
            localDateEnd = DateUtil.parseDate(endDate);
        }

        List<DeliSale> salesInGivenPeriod;

        if (localDateEnd == null) {
            // Single day query
            salesInGivenPeriod = deliService.getAllSalesByDate(localDateStart);
        } else {
            // Range query
            salesInGivenPeriod = deliService.getAllSalesByDatesBetween(localDateStart, localDateEnd);
        }
        // Fetch sales data for the whole day


        // Define time intervals
        LocalTime morningStart = LocalTime.of(6, 0);
        LocalTime morningEnd = LocalTime.of(9, 0);
        LocalTime midMorningStart = LocalTime.of(9, 0);
        LocalTime midMorningEnd = LocalTime.of(12, 0);
        LocalTime lunchStart = LocalTime.of(12, 0);
        LocalTime lunchEnd = LocalTime.of(14, 0);
        LocalTime eveningStart = LocalTime.of(14, 0);
        LocalTime eveningEnd = LocalTime.of(17, 0);

        // Aggregate sales for each interval
        double morningSales = 0.0, midMorningSales = 0.0, lunchSales = 0.0, eveningSales = 0.0, totalSales = 0.0;

        for (DeliSale sale : salesInGivenPeriod) {
            Instant timestamp = sale.getSaleTime();
            double amount = sale.getSalePrice();

            // Convert Instant to LocalTime
            LocalTime saleTime = LocalTime.ofInstant(timestamp, ZoneId.systemDefault());

            totalSales += amount;

            if (!saleTime.isBefore(morningStart) && saleTime.isBefore(morningEnd)) {
                morningSales += amount;
            } else if (!saleTime.isBefore(midMorningStart) && saleTime.isBefore(midMorningEnd)) {
                midMorningSales += amount;
            } else if (!saleTime.isBefore(lunchStart) && saleTime.isBefore(lunchEnd)) {
                lunchSales += amount;
            } else if (!saleTime.isBefore(eveningStart) && saleTime.isBefore(eveningEnd)) {
                eveningSales += amount;
            }
        }
        float flMorningSales = 0f;
        float flMidMorningSales = 0f;
        float flLunchSales = 0f;
        float flEveningSales = 0f;

        // Calculate sales percentages
        List<Float> salesPercentagesList = new ArrayList<>();
        if (totalSales > 0) {
            flMorningSales = (float) ((morningSales / totalSales) * 100);
            flMidMorningSales = (float) ((midMorningSales / totalSales) * 100);
            flLunchSales = (float) ((lunchSales / totalSales) * 100);
            flEveningSales = (float) ((eveningSales / totalSales) * 100);
        }

        // Define sale categories


       List<DailySaleDto> dailySalesPercentagesList = new ArrayList<>();

       DailySaleDto dailySaleMorning = new DailySaleDto();
       dailySaleMorning.setSalePercentage(flMorningSales);
       dailySaleMorning.setSaleCategoryTime("Morning (6-9am)");
       dailySaleMorning.setSaleAmount((float) morningSales);
       dailySalesPercentagesList.add(dailySaleMorning);

       DailySaleDto dailySaleMidMorning = new DailySaleDto();
       dailySaleMidMorning.setSalePercentage(flMidMorningSales);
       dailySaleMidMorning.setSaleCategoryTime("Mid-Morning (9-12pm)");
       dailySaleMidMorning.setSaleAmount((float) midMorningSales);
       dailySalesPercentagesList.add(dailySaleMidMorning);

       DailySaleDto dailySaleLunch = new DailySaleDto();
       dailySaleLunch.setSalePercentage(flLunchSales);
       dailySaleLunch.setSaleCategoryTime("Lunch (12-2pm)");
       dailySaleLunch.setSaleAmount((float) lunchSales);
       dailySalesPercentagesList.add(dailySaleLunch);

       DailySaleDto dailySaleEvening = new DailySaleDto();
       dailySaleEvening.setSalePercentage(flEveningSales);
       dailySaleEvening.setSaleCategoryTime("Evening (2-5pm)");
       dailySaleEvening.setSaleAmount((float) eveningSales);
       dailySalesPercentagesList.add(dailySaleEvening);

       return dailySalesPercentagesList;
    }

    public float CalculateSales(String date){
        float dailySales = 0f;

        LocalDate localDate = DateUtil.parseDate(date);

        List<DeliSale> dailySalesForTheDay = deliService.getAllSalesByDate(localDate);

        for(DeliSale sale : dailySalesForTheDay){
            dailySales += sale.getSalePrice();
        }
        return dailySales;
    }

    public Float totalSales(){
        float totalSaleNumber = 0f;
        List<DeliSale> totalSales = deliService.getAllSales();

        for(DeliSale sale : totalSales){
            totalSaleNumber += sale.getSalePrice();

        }
        return totalSaleNumber;
    }




}
