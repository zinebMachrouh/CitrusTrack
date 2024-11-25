package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.EmbeddedFieldDTO;
import com.spring.CitrusTrack.dto.FarmResponseDTO;
import com.spring.CitrusTrack.entities.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FarmResponseMapper {
    default FarmResponseDTO toDTO(Farm farm){
        if (farm == null) {
            return null;
        }

        return FarmResponseDTO.builder()
                .id(farm.getId())
                .name(farm.getName())
                .location(farm.getLocation())
                .area(farm.getArea())
                .creationDate(farm.getCreationDate())
                .fields(farm.getFields() != null && !farm.getFields().isEmpty() ?
                        farm.getFields().stream()
                                .map(field -> EmbeddedFieldDTO.builder()
                                        .id(field.getId())
                                        .area(field.getArea())
                                        .build())
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    Farm toEntity(FarmResponseDTO farmDTO);

    List<FarmResponseDTO> toDTOList(List<Farm> farms);
    List<Farm> toEntityList(List<FarmResponseDTO> farmDTOs);
}
