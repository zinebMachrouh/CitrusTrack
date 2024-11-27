package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.SaleDTO;
import com.spring.CitrusTrack.entities.Sale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    SaleDTO toDTO(Sale sale);
    Sale toEntity(SaleDTO saleDTO);
    List<SaleDTO> toDTOList(List<Sale> sales);
    List<Sale> toEntityList(List<SaleDTO> saleDTOs);
}
