package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.FieldDTO;
import com.spring.CitrusTrack.dto.response.FieldResponseDTO;
import com.spring.CitrusTrack.services.FieldService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/field")
public class FieldController {
    private final FieldService fieldService;

    @PostMapping
    public ResponseEntity<FieldResponseDTO> createField(@RequestBody @Valid FieldDTO fieldDTO) {
        FieldResponseDTO savedField = fieldService.saveField(fieldDTO);
        return ResponseEntity.status(201).body(savedField);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> getField(@PathVariable Long id) {
        Optional<FieldResponseDTO> fieldDTO = fieldService.getField(id);
        return fieldDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<FieldResponseDTO> updateField(@RequestBody FieldDTO fieldDTO) {
        FieldResponseDTO updatedField = fieldService.updateField(fieldDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FieldResponseDTO>> getAllFields() {
        List<FieldResponseDTO> fields = fieldService.getAllField();
        return ResponseEntity.ok(fields);
    }

    @GetMapping("/farm/{id}")
    public ResponseEntity<List<FieldResponseDTO>> getFieldsByFarm(@PathVariable Long id) {
        List<FieldResponseDTO> fields = fieldService.getFieldsByFarm(id);
        return ResponseEntity.ok(fields);
    }
}
