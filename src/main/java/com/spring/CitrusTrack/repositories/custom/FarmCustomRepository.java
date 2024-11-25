package com.spring.CitrusTrack.repositories.custom;

import com.spring.CitrusTrack.dto.response.FarmResponseDTO;

import java.util.List;

public interface FarmCustomRepository {
    List<FarmResponseDTO> searchFarms(String name, String location, Double area);
}
