package com.spring.CitrusTrack.repositories.custom;

import com.spring.CitrusTrack.dto.FarmResponseDTO;

import java.util.List;

public interface FarmCustomRepository {
    List<FarmResponseDTO> searchFarms(String name, String location, Double area);
}
