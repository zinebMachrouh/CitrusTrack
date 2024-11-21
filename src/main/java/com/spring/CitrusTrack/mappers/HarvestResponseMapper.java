package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.HarvestResponseDTO;
import com.spring.CitrusTrack.entities.Harvest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestResponseMapper {
    HarvestResponseDTO toDTO(Harvest harvest);
    Harvest toEntity(HarvestResponseDTO harvestDTO);
    List<HarvestResponseDTO> toDTOList(List<Harvest> harvests);
    List<Harvest> toEntityList(List<HarvestResponseDTO> harvestDTOs);
}
