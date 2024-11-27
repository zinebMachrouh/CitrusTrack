package com.spring.CitrusTrack.mappers.response;

import com.spring.CitrusTrack.dto.response.TreeResponseDTO;
import com.spring.CitrusTrack.entities.Tree;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreeResponseMapper {
    TreeResponseDTO toDTO(Tree tree);

    Tree toEntity(TreeResponseDTO treeResponseDTO);

    List<TreeResponseDTO> toDTOList(List<Tree> trees);
    List<Tree> toEntityList(List<TreeResponseDTO> treeResponseDTOs);
}
