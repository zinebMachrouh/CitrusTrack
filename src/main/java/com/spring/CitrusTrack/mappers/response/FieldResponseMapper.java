package com.spring.CitrusTrack.mappers.response;

import com.spring.CitrusTrack.dto.embedded.EmbeddedTreeDTO;
import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.response.FieldResponseDTO;
import com.spring.CitrusTrack.entities.Field;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FieldResponseMapper {
    default FieldResponseDTO toDTO(Field field) {
        if (field == null) {
            return null;
        }

        return FieldResponseDTO.builder()
                .area(field.getArea())
                .farm(field.getFarm() != null ? FarmDTO.builder()
                        .id(field.getFarm().getId())
                        .name(field.getFarm().getName())
                        .location(field.getFarm().getLocation())
                        .area(field.getFarm().getArea())
                        .build() : null)
                .trees(field.getTrees() != null && !field.getTrees().isEmpty() ?
                        field.getTrees().stream()
                                .map(tree -> EmbeddedTreeDTO.builder()
                                        .id(tree.getId())
                                        .plantationDate(tree.getPlantationDate())
                                        .build())
                                .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    Field toEntity(FieldResponseDTO fieldDTO);

    List<FieldResponseDTO> toDTOList(List<Field> fields);
    List<Field> toEntityList(List<FieldResponseDTO> fieldDTOs);
}
