package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.FieldDTO;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    FieldDTO saveField(FieldDTO fieldDTO);
    FieldDTO updateField(FieldDTO fieldDTO);
    void deleteField(Long id);
    Optional<FieldDTO> getField(Long id);
    List<FieldDTO> getAllField();
    List<FieldDTO> getFieldsByFarm(Long farmId);
}
