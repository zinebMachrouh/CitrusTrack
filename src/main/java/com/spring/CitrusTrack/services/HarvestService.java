package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.HarvestDTO;
import com.spring.CitrusTrack.dto.response.HarvestResponseDTO;

import java.util.List;

public interface HarvestService {
    HarvestResponseDTO saveHarvest(HarvestDTO harvestDTO);
    HarvestResponseDTO updateHarvest(HarvestDTO harvestDTO);
    void deleteHarvest(Long id);
    HarvestResponseDTO getHarvest(Long id);
    List<HarvestResponseDTO> getAllHarvest();
}
