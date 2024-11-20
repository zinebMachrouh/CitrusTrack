package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.FieldDTO;
import com.spring.CitrusTrack.services.FieldService;
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
    public ResponseEntity<FieldDTO> createField(@RequestBody FieldDTO fieldDTO) {
        FieldDTO savedField = fieldService.saveField(fieldDTO);
        return ResponseEntity.status(201).body(savedField);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getField(@PathVariable Long id) {
        Optional<FieldDTO> fieldDTO = fieldService.getField(id);
        return fieldDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id, @RequestBody FieldDTO fieldDTO) {
        if (!id.equals(fieldDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        FieldDTO updatedField = fieldService.updateField(fieldDTO);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        List<FieldDTO> fields = fieldService.getAllField();
        return ResponseEntity.ok(fields);
    }

    @GetMapping("/farm/{id}")
    public ResponseEntity<List<FieldDTO>> getFieldsByFarm(@PathVariable Long id) {
        List<FieldDTO> fields = fieldService.getFieldsByFarm(id);
        return ResponseEntity.ok(fields);
    }
}
