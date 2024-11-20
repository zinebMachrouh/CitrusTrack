package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.FarmDTO;
import com.spring.CitrusTrack.dto.FieldDTO;
import com.spring.CitrusTrack.entities.Farm;
import com.spring.CitrusTrack.entities.Field;
import com.spring.CitrusTrack.exceptions.AlreadyExistsException;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.FieldMapper;
import com.spring.CitrusTrack.repositories.FarmRepository;
import com.spring.CitrusTrack.repositories.FieldRepository;
import com.spring.CitrusTrack.services.FieldService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
@Slf4j
@Transactional
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;

    private final FieldMapper fieldMapper;

    @Override
    public FieldDTO saveField(FieldDTO fieldDTO) {
        if (fieldRepository.existsById(fieldDTO.getId())) {
            throw new AlreadyExistsException("Field with id " + fieldDTO.getId() + " already exists.");
        } else {
            validateField(fieldDTO);

            Field field = fieldMapper.toEntity(fieldDTO);
            List<Field> fields = field.getFarm().getFields();

            if (fields == null) {
                fields = new ArrayList<>();
                field.getFarm().setFields(fields);
            }

            fields.add(field);
            field = fieldRepository.save(field);

            return fieldMapper.toDTO(field);
        }
    }

    @Transactional
    @Override
    public FieldDTO updateField(FieldDTO fieldDTO) {
        Field existingField = fieldRepository.findById(fieldDTO.getId())
                .orElseThrow(() -> new DoesNotExistsException("Field with id " + fieldDTO.getId() + " does not exist."));

        validateField(fieldDTO);

        Field fieldToUpdate = fieldMapper.toEntity(fieldDTO);

        Farm existingFarm = farmRepository.findById(fieldDTO.getFarm().getId())
                .orElseThrow(() -> new DoesNotExistsException("Farm with id " + fieldDTO.getFarm().getId() + " does not exist."));

        fieldToUpdate.setFarm(existingFarm);

        List<Field> fields = existingFarm.getFields();
        fields.removeIf(field -> field.getId().equals(fieldToUpdate.getId()));
        fields.add(fieldToUpdate);

        fieldToUpdate.setTrees(existingField.getTrees());

        Field updatedField = fieldRepository.save(existingField);

        return fieldMapper.toDTO(updatedField);
    }


    @Override
    public void deleteField(Long id) {
        if (fieldRepository.existsById(id)) {
            fieldRepository.findById(id).ifPresent(field -> field.getFarm().getFields().remove(field));
            fieldRepository.deleteById(id);
        } else {
            throw new DoesNotExistsException("Field with id " + id + " does not exist");
        }
    }

    @Override
    public Optional<FieldDTO> getField(Long id) {
        if (fieldRepository.existsById(id)) {
            Field field = fieldRepository.findById(id).get();
            return Optional.of(fieldMapper.toDTO(field));
        } else {
            throw new DoesNotExistsException("Field with id " + id + " does not exist");
        }
    }

    @Override
    public List<FieldDTO> getAllField() {
        List<Field> fields = fieldRepository.findAll();
        return fieldMapper.toDTOList(fields);
    }

    @Override
    public List<FieldDTO> getFieldsByFarm(Long farmId) {
        List<Field> fields = farmRepository.findById(farmId).get().getFields();
        return fieldMapper.toDTOList(fields);
    }

    private void validateField(FieldDTO fieldDTO) {
        FarmDTO farm = fieldDTO.getFarm();
        if (farm == null || farm.getId() == null) {
            throw new IllegalArgumentException("Field must be associated with a valid Farm.");
        }

        Farm existingFarm = farmRepository.findById(farm.getId())
                .orElseThrow(() -> new DoesNotExistsException("Farm with id " + farm.getId() + " does not exist"));

        if (fieldDTO.getArea() < 0.1) {
            throw new IllegalArgumentException("Field size must be at least 0.1 hectare.");
        }
        if (fieldDTO.getArea() > existingFarm.getArea() * 0.5) {
            throw new IllegalArgumentException("Field size must be less than half of the farm size.");
        }

        List<Field> fields = existingFarm.getFields();
        if (fields == null) {
            throw new IllegalArgumentException("Fields list in the farm is not initialized.");
        }
        if (fields.size() >= 10) {
            throw new IllegalArgumentException("Farm can have at most 10 fields.");
        }

        double totalFieldArea = fields.stream()
                .mapToDouble(Field::getArea)
                .sum();

        if (totalFieldArea + fieldDTO.getArea() >= existingFarm.getArea()) {
            throw new IllegalArgumentException("The sum of the areas of all fields in the farm must be less than the total farm size.");
        }
    }
}
