package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import com.DeliTelligenceBackEndService.entitymodel.mapper.StandardWeightMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.StandardWeightRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandardWeightService {

    private final StandardWeightRepository standardWeightRepository;
    private final StandardWeightMapper standardWeightMapper;

    public StandardWeightService(StandardWeightRepository standardWeightRepository, StandardWeightMapper standardWeightMapper) {
        this.standardWeightRepository = standardWeightRepository;
        this.standardWeightMapper = standardWeightMapper;
    }

    public List<StandardWeightDto> getStandardWeightList(){
        List<StandardWeight> standardWeights = standardWeightRepository.findAll();
        List<StandardWeightDto> standardWeightDtos = new ArrayList<>();

        for (StandardWeight standardWeight : standardWeights) {
            StandardWeightDto standardWeightDto = standardWeightMapper.toStandardWeightDto(standardWeight);
            standardWeightDtos.add(standardWeightDto);
        }

        return standardWeightDtos;
    }
}
