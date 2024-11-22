package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.SaleDTO;
import com.spring.CitrusTrack.dto.SaleResponseDTO;
import com.spring.CitrusTrack.entities.Harvest;
import com.spring.CitrusTrack.entities.Sale;
import com.spring.CitrusTrack.mappers.SaleMapper;
import com.spring.CitrusTrack.mappers.SaleResponseMapper;
import com.spring.CitrusTrack.repositories.HarvestRepository;
import com.spring.CitrusTrack.repositories.SaleRepository;
import com.spring.CitrusTrack.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;
    private final SaleResponseMapper saleResponseMapper;

    @Override
    public SaleResponseDTO saveSale(SaleDTO saleDTO) {
        Harvest harvest = harvestRepository.findById(saleDTO.getHarvest().getId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + saleDTO.getHarvest().getId()));


        if (harvest.getStock() < saleDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock in harvest: " + harvest.getStock());
        }

        Sale sale = saleMapper.toEntity(saleDTO);
        sale.setTotalRevenue(sale.getUnitPrice() * sale.getQuantity());
        Sale savedSale = saleRepository.save(sale);

        harvest.setStock(harvest.getStock() - saleDTO.getQuantity());
        harvestRepository.save(harvest);

        log.info("Sale saved successfully: {}", savedSale);

        return saleResponseMapper.toDTO(savedSale);
    }

    @Override
    public SaleResponseDTO updateSale(SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(saleDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + saleDTO.getId()));

        Harvest harvest = harvestRepository.findById(saleDTO.getHarvest().getId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + saleDTO.getHarvest().getId()));

        double quantityDifference = saleDTO.getQuantity() - existingSale.getQuantity();
        if (harvest.getStock() < quantityDifference) {
            throw new IllegalArgumentException("Insufficient stock to update sale");
        }
        harvest.setStock(harvest.getStock() - quantityDifference);
        harvestRepository.save(harvest);

        existingSale.setDate(saleDTO.getDate());
        existingSale.setUnitPrice(saleDTO.getUnitPrice());
        existingSale.setQuantity(saleDTO.getQuantity());
        existingSale.setClient(saleDTO.getClient());
        existingSale.setTotalRevenue(saleDTO.getUnitPrice() * saleDTO.getQuantity());
        Sale updatedSale = saleRepository.save(existingSale);

        log.info("Sale updated successfully: {}", updatedSale);

        return saleResponseMapper.toDTO(updatedSale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + id));

        Harvest harvest = sale.getHarvest();

        harvest.setStock(harvest.getStock() + sale.getQuantity());
        harvestRepository.save(harvest);

        saleRepository.deleteById(id);
        log.info("Sale deleted successfully with ID: {}", id);
    }

    @Override
    public SaleResponseDTO getSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with ID: " + id));

        return saleResponseMapper.toDTO(sale);
    }

    @Override
    public Page<SaleResponseDTO> getAllSales(int page, int size, String sort, String direction) {
        Sort sortConfig = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sort).ascending()
                : Sort.by(sort).descending();

        Page<Sale> salesPage = saleRepository.findAll(PageRequest.of(page, size, sortConfig));

        return salesPage.map(saleResponseMapper::toDTO);
    }
}
