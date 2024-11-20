package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.TreeDTO;
import com.spring.CitrusTrack.dto.TreeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TreeService {
    TreeResponseDTO saveTree(TreeDTO treeDTO);
    Optional<TreeResponseDTO> getTree(Long id);
    TreeResponseDTO updateTree(TreeDTO treeDTO);
    void deleteTree(Long id);
    List<TreeResponseDTO> getAllTree();
}
