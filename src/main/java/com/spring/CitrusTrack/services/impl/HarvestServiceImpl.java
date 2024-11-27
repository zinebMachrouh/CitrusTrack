package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.HarvestDTO;
import com.spring.CitrusTrack.dto.response.HarvestResponseDTO;
import com.spring.CitrusTrack.entities.Harvest;
import com.spring.CitrusTrack.entities.enums.Season;
import com.spring.CitrusTrack.exceptions.AlreadyExistsException;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.HarvestMapper;
import com.spring.CitrusTrack.mappers.response.HarvestResponseMapper;
import com.spring.CitrusTrack.repositories.HarvestRepository;
import com.spring.CitrusTrack.services.HarvestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;

    private final HarvestMapper harvestMapper;
    private final HarvestResponseMapper harvestResponseMapper;

    @Override
    public HarvestResponseDTO saveHarvest(HarvestDTO harvestDTO) {
        if (harvestRepository.existsById(harvestDTO.getId())) {
            throw new AlreadyExistsException("Harvest with id " + harvestDTO.getId() + " already exists.");
        }

        Harvest harvest = harvestMapper.toEntity(harvestDTO);
        harvest.setSeason(getSeasonFromDate(harvestDTO.getDate()));
        harvest.setQuantity(0.0);
        harvest.setStock(harvest.getQuantity());

        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestResponseMapper.toDTO(savedHarvest);
    }

    @Override
    public HarvestResponseDTO updateHarvest(HarvestDTO harvestDTO) {
        Harvest existingHarvest = harvestRepository.findById(harvestDTO.getId())
                .orElseThrow(() -> new DoesNotExistsException("Harvest with id " + harvestDTO.getId() + " does not exist."));

        Harvest updatedHarvest = harvestMapper.toEntity(harvestDTO);
        updatedHarvest.setSeason(getSeasonFromDate(harvestDTO.getDate()));
        updatedHarvest.setQuantity(existingHarvest.getQuantity());
        updatedHarvest.setStock(existingHarvest.getStock());

        return harvestResponseMapper.toDTO(harvestRepository.save(updatedHarvest));
    }


    @Override
    public void deleteHarvest(Long id) {
        if (!harvestRepository.existsById(id)) {
            throw new DoesNotExistsException("Harvest with id " + id + " does not exist.");
        } else {
            harvestRepository.deleteById(id);
        }
    }

    @Override
    public HarvestResponseDTO getHarvest(Long id) {
        return harvestResponseMapper.toDTO(harvestRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException("Harvest with id " + id + " does not exist.")));
    }

    @Override
    public List<HarvestResponseDTO> getAllHarvest() {
        return harvestResponseMapper.toDTOList(harvestRepository.findAll());
    }

    private Season getSeasonFromDate(LocalDate date) {
        int dayOfYear = date.getDayOfYear();

        // Approximate day ranges for seasons
        if (dayOfYear >= 355 || dayOfYear <= 79) { // Dec 21 - Mar 20
            return Season.WINTER;
        } else if (dayOfYear <= 171) { // Mar 21 - Jun 20
            return Season.SPRING;
        } else if (dayOfYear <= 263) { // Jun 21 - Sep 22
            return Season.SUMMER;
        } else { // Sep 23 - Dec 20
            return Season.AUTUMN;
        }
    }
}
