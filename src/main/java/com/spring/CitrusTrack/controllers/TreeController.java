package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.TreeDTO;
import com.spring.CitrusTrack.dto.response.TreeResponseDTO;
import com.spring.CitrusTrack.services.TreeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/tree")
public class TreeController {
    private final TreeService treeService;

    @PostMapping
    public ResponseEntity<TreeResponseDTO> createTree(@RequestBody @Valid TreeDTO treeDTO) {
        TreeResponseDTO savedTree = treeService.saveTree(treeDTO);
        return ResponseEntity.status(201).body(savedTree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> getTree(@PathVariable Long id) {
        Optional<TreeResponseDTO> treeDTO = treeService.getTree(id);
        return treeDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<TreeResponseDTO> updateTree(@RequestBody @Valid TreeDTO treeDTO) {
        TreeResponseDTO updatedTree = treeService.updateTree(treeDTO);
        return ResponseEntity.ok(updatedTree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        treeService.deleteTree(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TreeResponseDTO>> getAllTrees() {
        List<TreeResponseDTO> trees = treeService.getAllTree();
        return ResponseEntity.ok(trees);
    }
}
