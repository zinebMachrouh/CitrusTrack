package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.entities.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    FarmDTO toDTO(Farm farm);

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "fields", ignore = true)
    Farm toEntity(FarmDTO farmDTO);

    List<FarmDTO> toDTOList(List<Farm> farms);
    List<Farm> toEntityList(List<FarmDTO> farmDTOs);
}