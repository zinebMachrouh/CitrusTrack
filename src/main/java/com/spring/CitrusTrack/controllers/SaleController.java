package com.spring.CitrusTrack.controllers;

import com.spring.CitrusTrack.dto.SaleDTO;
import com.spring.CitrusTrack.dto.SaleResponseDTO;
import com.spring.CitrusTrack.services.SaleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/sale")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody @Valid SaleDTO saleDTO) {
        return ResponseEntity.ok(saleService.saveSale(saleDTO));
    }

    @PutMapping
    public ResponseEntity<SaleResponseDTO> updateSale(@RequestBody @Valid SaleDTO saleDTO) {
        return ResponseEntity.ok(saleService.updateSale(saleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSale(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSale(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<SaleResponseDTO>> getAllSales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "unitPrice") String sort,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Page<SaleResponseDTO> salesPage = saleService.getAllSales(page, size, sort, direction);
        return ResponseEntity.ok(salesPage);
    }
}
