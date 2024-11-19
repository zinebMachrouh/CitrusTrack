package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.FarmResponseDTO;
import com.spring.CitrusTrack.entities.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmResponseMapper {
    FarmResponseDTO toDTO(Farm farm);

    @Mapping(target = "creationDate", ignore = true)
    Farm toEntity(FarmResponseDTO farmDTO);

    List<FarmResponseDTO> toDTOList(List<Farm> farms);
    List<Farm> toEntityList(List<FarmResponseDTO> farmDTOs);
}
