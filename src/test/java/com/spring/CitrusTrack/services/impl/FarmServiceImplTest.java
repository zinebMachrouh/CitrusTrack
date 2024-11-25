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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;
    @Mock
    private FarmCustomRepository farmCustomRepository;
    @Mock
    private FarmMapper farmMapper;
    @Mock
    private FarmResponseMapper farmResponseMapper;

    @InjectMocks
    private FarmServiceImpl farmService;

    private Farm farm;
    private FarmDTO farmDTO;
    private FarmResponseDTO farmResponseDTO;

    @BeforeEach
    public void init() {
        farmDTO = FarmDTO.builder()
                .id(1L)
                .name("Farm 1")
                .location("Location 1")
                .area(1.0)
                .creationDate(LocalDate.of(2021, 1, 1))
                .build();

        farm = Farm.builder()
                .id(1L)
                .name("Farm 1")
                .location("Location 1")
                .area(1.0)
                .creationDate(LocalDate.of(2021, 1, 1))
                .build();

        farmResponseDTO = FarmResponseDTO.builder()
                .id(1L)
                .name("Farm 1")
                .location("Location 1")
                .area(1.0)
                .creationDate(LocalDate.of(2021, 1, 1))
                .fields(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("Test save farm")
    public void testSaveFarm() {
        given(farmRepository.existsById(1L)).willReturn(false);
        given(farmMapper.toEntity(farmDTO)).willReturn(farm);
        given(farmRepository.save(farm)).willReturn(farm);
        given(farmResponseMapper.toDTO(farm)).willReturn(farmResponseDTO);

        FarmResponseDTO result = farmService.saveFarm(farmDTO);

        assertEquals(farmDTO.getId(), result.getId());
        verify(farmRepository).save(farm);
    }

    @Test
    @DisplayName("Test save method - success")
    void testSave() {
        given(farmRepository.existsById(1L)).willReturn(false);
        given(farmMapper.toEntity(farmDTO)).willReturn(farm);
        given(farmRepository.save(farm)).willReturn(farm);
        given(farmResponseMapper.toDTO(farm)).willReturn(farmResponseDTO);

        FarmResponseDTO result = farmService.saveFarm(farmDTO);

        assertEquals(farmDTO.getId(), result.getId());
        verify(farmRepository).save(farm);
    }

    @Test
    @DisplayName("Test save method - already exists")
    void testSaveAlreadyExists() {
        given(farmRepository.existsById(1L)).willReturn(true);

        assertThrows(AlreadyExistsException.class, () -> farmService.saveFarm(farmDTO));
    }

    @Test
    @DisplayName("Test get method")
    void testGet() {
        given(farmRepository.existsById(1L)).willReturn(true);
        given(farmRepository.findById(1L)).willReturn(Optional.of(farm));
        given(farmResponseMapper.toDTO(farm)).willReturn(farmResponseDTO);

        Optional<FarmResponseDTO> result = farmService.getFarm(1L);

        assertTrue(result.isPresent());
        assertEquals(farmResponseDTO.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Test get method - farm does not exist")
    void testGetNotFound() {
        given(farmRepository.existsById(1L)).willReturn(false);

        Optional<FarmResponseDTO> result = farmService.getFarm(1L);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Test update method - success")
    void testUpdate() {
        given(farmRepository.existsById(1L)).willReturn(true);
        given(farmRepository.findById(1L)).willReturn(Optional.of(farm));
        given(farmMapper.toEntity(farmDTO)).willReturn(farm);
        given(farmRepository.save(farm)).willReturn(farm);
        given(farmResponseMapper.toDTO(farm)).willReturn(farmResponseDTO);

        FarmResponseDTO result = farmService.updateFarm(farmDTO);

        assertEquals(farmDTO.getId(), result.getId());
        verify(farmRepository).save(farm);
    }



    @Test
    @DisplayName("Test update method - farm does not exist")
    void testUpdateNotFound() {
        given(farmRepository.existsById(1L)).willReturn(false); // Mocking that the farm does not exist

        assertThrows(DoesNotExistsException.class, () -> farmService.updateFarm(farmDTO)); // Expecting DoesNotExistsException to be thrown
    }


    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        given(farmRepository.existsById(1L)).willReturn(true);

        farmService.deleteFarm(1L);

        verify(farmRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test delete method - farm does not exist")
    void testDeleteNotFound() {
        given(farmRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> farmService.deleteFarm(1L));
    }

    @Test
    @DisplayName("Test get all method")
    void testGetAll() {
        List<Farm> farms = Collections.singletonList(farm);
        given(farmRepository.findAll()).willReturn(farms);
        given(farmResponseMapper.toDTO(farm)).willReturn(farmResponseDTO);

        List<FarmResponseDTO> result = farmService.getAllFarm();

        assertEquals(1, result.size());
        assertEquals("Farm 1", result.get(0).getName());
    }


    @Test
    @DisplayName("Test search method")
    void testSearch() {
        String searchKeyword = "Farm 1";
        List<FarmResponseDTO> farms = Collections.singletonList(farmResponseDTO); // Mock list of DTOs returned by the repository
        given(farmCustomRepository.searchFarms(searchKeyword, null, null)).willReturn(farms); // Mock repository response

        List<FarmResponseDTO> result = farmService.searchFarms(searchKeyword, null, null); // Call the service method

        assertEquals(1, result.size()); // Expecting 1 result
        assertEquals("Farm 1", result.get(0).getName()); // Ensure the returned farm matches the mock data
    }


}
