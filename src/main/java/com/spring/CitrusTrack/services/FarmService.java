package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.FarmResponseDTO;

import java.util.List;
import java.util.Optional;

public interface FarmService {
    FarmResponseDTO saveFarm(FarmDTO farmDTO);
    Optional<FarmResponseDTO> getFarm(Long id);
    FarmResponseDTO updateFarm(FarmDTO farmDTO);
    void deleteFarm(Long id);
    List<FarmResponseDTO> getAllFarm();
    List<FarmResponseDTO> searchFarms(String name, String location, Double area);
}
