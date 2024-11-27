package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.FieldDTO;
import com.spring.CitrusTrack.dto.response.FieldResponseDTO;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    FieldResponseDTO saveField(FieldDTO fieldDTO);
    FieldResponseDTO updateField(FieldDTO fieldDTO);
    void deleteField(Long id);
    Optional<FieldResponseDTO> getField(Long id);
    List<FieldResponseDTO> getAllField();
    List<FieldResponseDTO> getFieldsByFarm(Long farmId);
}
