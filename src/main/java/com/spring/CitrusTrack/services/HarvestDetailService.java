package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.HarvestDetailDTO;

import java.util.List;
import java.util.Optional;

public interface HarvestDetailService {
    HarvestDetailDTO saveHarvestDetail(HarvestDetailDTO harvestDetailDTO);
    Optional<HarvestDetailDTO> getHarvestDetail(Long id);
    HarvestDetailDTO updateHarvestDetail(HarvestDetailDTO harvestDetailDTO);
    void deleteHarvestDetail(Long id);
    List<HarvestDetailDTO> getAllHarvestDetail();
}
