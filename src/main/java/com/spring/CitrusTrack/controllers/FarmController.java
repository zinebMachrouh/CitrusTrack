package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.FarmResponseDTO;
import com.spring.CitrusTrack.services.FarmService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/farm")
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmResponseDTO> createFarm(@RequestBody FarmDTO farmDTO) {
        FarmResponseDTO savedFarm = farmService.saveFarm(farmDTO);
        return ResponseEntity.status(201).body(savedFarm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> getFarm(@PathVariable Long id) {
        Optional<FarmResponseDTO> farmDTO = farmService.getFarm(id);
        return farmDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDTO farmDTO) {
        if (!id.equals(farmDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        FarmResponseDTO updatedFarm = farmService.updateFarm(farmDTO);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> getAllFarms() {
        List<FarmResponseDTO> farms = farmService.getAllFarm();
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponseDTO>> searchFarm(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double area) {
        List<FarmResponseDTO> farms = farmService.searchFarms(name, location, area);
        return ResponseEntity.ok(farms);
    }
}