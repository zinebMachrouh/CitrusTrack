package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.TreeDTO;
import com.spring.CitrusTrack.entities.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    TreeDTO toDTO(Tree tree);

    @Mapping(target = "age", ignore = true)
    Tree toEntity(TreeDTO treeDTO);

    List<TreeDTO> toDTOList(List<Tree> trees);
    List<Tree> toEntityList(List<TreeDTO> treeDTOs);
}
