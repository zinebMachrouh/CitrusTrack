package com.spring.CitrusTrack.mappers;

import com.spring.CitrusTrack.dto.HarvestDetailDTO;
import com.spring.CitrusTrack.entities.HarvestDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
    HarvestDetailDTO toDTO(HarvestDetail harvestDetail);
    HarvestDetail toEntity(HarvestDetailDTO harvestDetailDTO);
    List<HarvestDetailDTO> toDTOList(List<HarvestDetail> harvestDetails);
    List<HarvestDetail> toEntityList(List<HarvestDetailDTO> harvestDetailDTOs);
}
