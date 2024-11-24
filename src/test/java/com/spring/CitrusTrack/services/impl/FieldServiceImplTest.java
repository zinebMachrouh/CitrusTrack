package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.FieldDTO;
import com.spring.CitrusTrack.dto.FieldResponseDTO;
import com.spring.CitrusTrack.entities.Farm;
import com.spring.CitrusTrack.entities.Field;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.FieldMapper;
import com.spring.CitrusTrack.mappers.FieldResponseMapper;
import com.spring.CitrusTrack.repositories.FarmRepository;
import com.spring.CitrusTrack.repositories.FieldRepository;
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
public class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;
    @Mock
    private FarmRepository farmRepository;
    @Mock
    private FieldMapper fieldMapper;
    @Mock
    private FieldResponseMapper fieldResponseMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;

    private Field field;
    private FieldDTO fieldDTO;
    private FieldResponseDTO fieldResponseDTO;

    @BeforeEach
    public void init() {
        fieldDTO = FieldDTO.builder()
                .id(1L)
                .area(100.0)
                .farm(
                        FarmDTO.builder()
                                .id(1L)
                                .name("Farm 1")
                                .location("Location 1")
                                .area(1000.0)
                                .creationDate(LocalDate.of(2021, 1, 1))
                                .build()
                )
                .build();

        field = Field.builder()
                .id(1L)
                .area(100.0)
                .farm(
                        Farm.builder()
                                .id(1L)
                                .name("Farm 1")
                                .location("Location 1")
                                .area(1000.0)
                                .creationDate(LocalDate.of(2021, 1, 1))
                                .fields(new ArrayList<>())
                                .build()
                )
                .build();

        fieldResponseDTO = FieldResponseDTO.builder()
                .area(100.0)
                .farm(
                        FarmDTO.builder()
                                .id(1L)
                                .name("Farm 1")
                                .location("Location 1")
                                .area(1000.0)
                                .creationDate(LocalDate.of(2021, 1, 1))
                                .build()
                )
                .trees(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("Test saveField method")
    void testSaveField() {
        // Arrange
        given(fieldRepository.existsById(1L)).willReturn(false); // Simulate that the field does not exist
        given(fieldMapper.toEntity(fieldDTO)).willReturn(field); // Simulate the mapping from DTO to entity
        given(farmRepository.findById(1L)).willReturn(Optional.of(field.getFarm())); // Simulate finding the farm

        given(fieldRepository.save(field)).willReturn(field); // Simulate saving the field
        given(fieldResponseMapper.toDTO(field)).willReturn(fieldResponseDTO); // Simulate mapping from entity to DTO

        // Act
        FieldResponseDTO result = fieldService.saveField(fieldDTO);

        // Assert
        assertNotNull(result);
        assertEquals(fieldResponseDTO, result);
        verify(fieldRepository).save(field);
    }

    @Test
    @DisplayName("Test getAllField method")
    void testGetAllField() {
        List<Field> fields = Collections.singletonList(field);
        given(fieldRepository.findAll()).willReturn(fields);
        given(fieldResponseMapper.toDTOList(fields)).willReturn(Collections.singletonList(fieldResponseDTO));

        List<FieldResponseDTO> result = fieldService.getAllField();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(fieldResponseDTO.getArea(), result.get(0).getArea());
    }

    @Test
    @DisplayName("Test getField method when field exists")
    void testGetField() {
        given(fieldRepository.existsById(1L)).willReturn(true);
        given(fieldRepository.findById(1L)).willReturn(Optional.of(field));
        given(fieldResponseMapper.toDTO(field)).willReturn(fieldResponseDTO);

        Optional<FieldResponseDTO> result = fieldService.getField(1L);

        assertTrue(result.isPresent());
        assertEquals(fieldResponseDTO.getArea(), result.get().getArea());
    }

    @Test
    @DisplayName("Test getField method when field does not exist")
    void testGetFieldNotFound() {
        given(fieldRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> fieldService.getField(1L));
    }

    @Test
    @DisplayName("Test deleteField method")
    void testDeleteField() {
        given(fieldRepository.existsById(1L)).willReturn(true);

        fieldService.deleteField(1L);

        verify(fieldRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test deleteField method when field does not exist")
    void testDeleteFieldNotFound() {
        given(fieldRepository.existsById(1L)).willReturn(false);

        assertThrows(DoesNotExistsException.class, () -> fieldService.deleteField(1L));
    }
}
