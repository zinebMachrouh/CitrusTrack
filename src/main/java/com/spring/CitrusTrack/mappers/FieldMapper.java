package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.FieldDTO;

import com.spring.CitrusTrack.entities.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldDTO toDTO(Field field);

    //@Mapping(target = "trees", ignore = true)
    Field toEntity(FieldDTO fieldDTO);

    List<FieldDTO> toDTOList(List<Field> fields);

    List<Field> toEntityList(List<FieldDTO> fieldDTOs);
}

