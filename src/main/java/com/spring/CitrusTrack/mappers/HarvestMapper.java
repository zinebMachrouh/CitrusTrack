package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.HarvestDTO;
import com.spring.CitrusTrack.entities.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    HarvestDTO toDTO(Harvest harvest);
    Harvest toEntity(HarvestDTO harvestDTO);
    List<HarvestDTO> toDTOList(List<Harvest> harvests);
    List<Harvest> toEntityList(List<HarvestDTO> harvestDTOs);
}
