package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.HarvestDetailDTO;
import com.spring.CitrusTrack.services.HarvestDetailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/harvest-detail")
public class HarvestDetailController {
    private final HarvestDetailService harvestDetailService;

    @PostMapping
    public ResponseEntity<HarvestDetailDTO> createHarvestDetail(@RequestBody @Valid HarvestDetailDTO harvestDetailDTO) {
        return ResponseEntity.ok(harvestDetailService.saveHarvestDetail(harvestDetailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailDTO> getHarvestDetail(@PathVariable Long id) {
        Optional<HarvestDetailDTO> harvestDetailDTO = harvestDetailService.getHarvestDetail(id);
        return harvestDetailDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvestDetail(@PathVariable Long id) {
        harvestDetailService.deleteHarvestDetail(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<HarvestDetailDTO> updateHarvestDetail(@RequestBody @Valid HarvestDetailDTO harvestDetailDTO) {
        return ResponseEntity.ok(harvestDetailService.updateHarvestDetail(harvestDetailDTO));
    }

    @GetMapping
    public ResponseEntity<List<HarvestDetailDTO>> getAllHarvestDetail() {
        return ResponseEntity.ok(harvestDetailService.getAllHarvestDetail());
    }
}
