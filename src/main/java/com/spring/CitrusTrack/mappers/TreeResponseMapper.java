package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.TreeResponseDTO;
import com.spring.CitrusTrack.entities.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreeResponseMapper {
    TreeResponseDTO toDTO(Tree tree);

    Tree toEntity(TreeResponseDTO treeResponseDTO);

    List<TreeResponseDTO> toDTOList(List<Tree> trees);
    List<Tree> toEntityList(List<TreeResponseDTO> treeResponseDTOs);
}
