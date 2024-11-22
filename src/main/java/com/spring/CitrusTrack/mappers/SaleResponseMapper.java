package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.SaleResponseDTO;
import com.spring.CitrusTrack.entities.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleResponseMapper {
    SaleResponseDTO toDTO(Sale sale);
    Sale toEntity(SaleResponseDTO saleResponseDTO);
    List<SaleResponseDTO> toDTOList(List<Sale> sales);
    List<Sale> toEntityList(List<SaleResponseDTO> saleResponseDTOs);
}
