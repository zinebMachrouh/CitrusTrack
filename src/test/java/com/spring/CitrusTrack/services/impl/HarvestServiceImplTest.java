package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.HarvestDTO;
import com.spring.CitrusTrack.dto.HarvestResponseDTO;
import com.spring.CitrusTrack.entities.Harvest;
import com.spring.CitrusTrack.entities.enums.Season;
import com.spring.CitrusTrack.exceptions.AlreadyExistsException;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.HarvestMapper;
import com.spring.CitrusTrack.mappers.HarvestResponseMapper;
import com.spring.CitrusTrack.repositories.HarvestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HarvestServiceImplTest {

    @Mock
    private HarvestRepository harvestRepository;
    @Mock
    private HarvestMapper harvestMapper;
    @Mock
    private HarvestResponseMapper harvestResponseMapper;

    @InjectMocks
    private HarvestServiceImpl harvestService;

    private Harvest harvest;
    private HarvestDTO harvestDTO;
    private HarvestResponseDTO harvestResponseDTO;

    @BeforeEach
    public void init() {
        harvestDTO = HarvestDTO.builder()
                .id(1L)
                .date(LocalDate.of(2021, 6, 1))
                .build();

        harvestResponseDTO = HarvestResponseDTO.builder()
                .id(1L)
                .date(LocalDate.of(2021, 6, 1))
                .build();

        harvest = Harvest.builder()
                .id(1L)
                .date(LocalDate.of(2021, 6, 1))
                .season(Season.SUMMER)
                .quantity(0.0)
                .stock(0.0)
                .build();
    }

    @Test
    @DisplayName("Test saving a new harvest")
    public void testSaveHarvest() {
        given(harvestRepository.existsById(1L)).willReturn(false);
        given(harvestMapper.toEntity(harvestDTO)).willReturn(harvest);
        given(harvestRepository.save(harvest)).willReturn(harvest);
        given(harvestResponseMapper.toDTO(harvest)).willReturn(harvestResponseDTO);

        HarvestResponseDTO result = harvestService.saveHarvest(harvestDTO);

        assertNotNull(result);
        assertEquals(harvestResponseDTO, result);
        verify(harvestRepository).save(harvest);
    }

    @Test
    @DisplayName("Test saving a harvest that already exists")
    public void testSaveHarvestAlreadyExists() {
        given(harvestRepository.existsById(1L)).willReturn(true);

        assertThrows(AlreadyExistsException.class, () -> harvestService.saveHarvest(harvestDTO));
    }

    @Test
    @DisplayName("Test updating an existing harvest")
    public void testUpdateHarvest() {
        given(harvestRepository.findById(1L)).willReturn(Optional.of(harvest));
        given(harvestMapper.toEntity(harvestDTO)).willReturn(harvest);
        given(harvestRepository.save(harvest)).willReturn(harvest);
        given(harvestResponseMapper.toDTO(harvest)).willReturn(harvestResponseDTO);

        HarvestResponseDTO result = harvestService.updateHarvest(harvestDTO);

        assertNotNull(result);
        assertEquals(harvestResponseDTO, result);
        verify(harvestRepository).save(harvest);
    }

    @Test
    @DisplayName("Test updating a harvest that does not exist")
    public void testUpdateHarvestDoesNotExist() {
        given(harvestRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(DoesNotExistsException.class, () -> harvestService.updateHarvest(harvestDTO));
    }

    @Test
    @DisplayName("Test deleting an existing harvest")
    public void testDeleteHarvest() {
        given(harvestRepository.existsById(1L)).willReturn(true);

        harvestService.deleteHarvest(1L);

        verify(harvestRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test deleting a harvest that does not exist")
    public void testDeleteHarvestDoesNotExist() {
        given(harvestRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> harvestService.deleteHarvest(1L));
    }

    @Test
    @DisplayName("Test retrieving an existing harvest")
    public void testGetHarvest() {
        given(harvestRepository.findById(1L)).willReturn(Optional.of(harvest));
        given(harvestResponseMapper.toDTO(harvest)).willReturn(harvestResponseDTO);

        HarvestResponseDTO result = harvestService.getHarvest(1L);

        assertNotNull(result);
        assertEquals(harvestResponseDTO, result);
    }

    @Test
    @DisplayName("Test retrieving a harvest that does not exist")
    public void testGetHarvestDoesNotExist() {
        given(harvestRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(DoesNotExistsException.class, () -> harvestService.getHarvest(1L));
    }

    @Test
    @DisplayName("Test retrieving all harvests")
    public void testGetAllHarvests() {
        List<Harvest> harvestList = List.of(harvest);
        List<HarvestResponseDTO> harvestResponseList = List.of(harvestResponseDTO);

        given(harvestRepository.findAll()).willReturn(harvestList);
        given(harvestResponseMapper.toDTOList(harvestList)).willReturn(harvestResponseList);

        List<HarvestResponseDTO> result = harvestService.getAllHarvest();

        assertNotNull(result);
        assertEquals(harvestResponseList, result);
    }
}
