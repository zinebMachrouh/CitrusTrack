package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.HarvestDTO;
import com.spring.CitrusTrack.dto.response.HarvestResponseDTO;
import com.spring.CitrusTrack.services.HarvestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/harvest")
public class HarvestController {
    private final HarvestService harvestService;

    @PostMapping
    public ResponseEntity<HarvestResponseDTO> createHarvest(@RequestBody @Valid HarvestDTO harvestDTO) {
        return ResponseEntity.ok(harvestService.saveHarvest(harvestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvest(@PathVariable Long id) {
        return ResponseEntity.ok(harvestService.getHarvest(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<HarvestResponseDTO> updateHarvest(@RequestBody @Valid HarvestDTO harvestDTO) {
        return ResponseEntity.ok(harvestService.updateHarvest(harvestDTO));
    }

    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> getAllHarvest() {
        return ResponseEntity.ok(harvestService.getAllHarvest());
    }

}
