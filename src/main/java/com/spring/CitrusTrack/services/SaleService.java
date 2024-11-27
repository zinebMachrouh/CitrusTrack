package com.spring.CitrusTrack.services;

import com.spring.CitrusTrack.dto.SaleDTO;
import com.spring.CitrusTrack.dto.response.SaleResponseDTO;
import org.springframework.data.domain.Page;

public interface SaleService {
    SaleResponseDTO saveSale(SaleDTO saleDTO);
    SaleResponseDTO updateSale(SaleDTO saleDTO);
    void deleteSale(Long id);
    SaleResponseDTO getSale(Long id);
    Page<SaleResponseDTO> getAllSales(int page, int size, String sort, String direction);
}
