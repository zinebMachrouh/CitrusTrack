package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.FarmResponseDTO;
import com.spring.CitrusTrack.entities.Farm;
import com.spring.CitrusTrack.exceptions.AlreadyExistsException;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.FarmMapper;
import com.spring.CitrusTrack.mappers.FarmResponseMapper;
import com.spring.CitrusTrack.repositories.FarmRepository;
import com.spring.CitrusTrack.repositories.custom.FarmCustomRepository;
import com.spring.CitrusTrack.services.FarmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FarmCustomRepository farmCustomRepository;

    private final FarmMapper farmMapper;
    private final FarmResponseMapper farmResponseMapper;

    @Override
    public FarmResponseDTO saveFarm(FarmDTO farmDTO) {
        if (farmRepository.existsById(farmDTO.getId())) {
            throw new AlreadyExistsException("Farm with id " + farmDTO.getId() + " already exists");
        } else {
            Farm farm = farmMapper.toEntity(farmDTO);
            farm.setCreationDate(LocalDate.now());
            farm.setFields(new ArrayList<>());
            farm = farmRepository.save(farm);
            return farmResponseMapper.toDTO(farm);
        }
    }

    @Override
    public Optional<FarmResponseDTO> getFarm(Long id) {
        if (farmRepository.existsById(id)) {
            Farm farm = farmRepository.findById(id).get();
            return Optional.of(farmResponseMapper.toDTO(farm));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public FarmResponseDTO updateFarm(FarmDTO farmDTO) {
        if (!farmRepository.existsById(farmDTO.getId())) {
            throw new DoesNotExistsException("Farm with id " + farmDTO.getId() + " does not exist");
        }

        Farm existingFarm = farmRepository.findById(farmDTO.getId())
                .orElseThrow(() -> new DoesNotExistsException("Farm with id " + farmDTO.getId() + " does not exist"));

        Farm updatedFarm = farmMapper.toEntity(farmDTO);

        updatedFarm.setCreationDate(existingFarm.getCreationDate());
        updatedFarm.setFields(existingFarm.getFields());

        updatedFarm = farmRepository.save(updatedFarm);

        return farmResponseMapper.toDTO(updatedFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        if (farmRepository.existsById(id)) {
            farmRepository.deleteById(id);
        } else {
            throw new DoesNotExistsException("Farm with id " + id + " does not exist");
        }
    }

    @Override
    public List<FarmResponseDTO> getAllFarm() {
        List<Farm> farms = farmRepository.findAll();
        return farmResponseMapper.toDTOList(farms);
    }

    @Override
    public List<FarmResponseDTO> searchFarms(String name, String location, Double area) {
        if (name == null && location == null && area == null) {
            return farmResponseMapper.toDTOList(farmRepository.findAll());
        } else {
            return farmCustomRepository.searchFarms(name, location, area);
        }
    }
}
