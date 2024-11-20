package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.TreeDTO;
import com.spring.CitrusTrack.dto.TreeResponseDTO;
import com.spring.CitrusTrack.entities.Tree;
import com.spring.CitrusTrack.entities.Field;
import com.spring.CitrusTrack.entities.enums.TreeStatus;
import com.spring.CitrusTrack.exceptions.AlreadyExistsException;
import com.spring.CitrusTrack.exceptions.DoesNotExistsException;
import com.spring.CitrusTrack.mappers.TreeMapper;
import com.spring.CitrusTrack.mappers.TreeResponseMapper;
import com.spring.CitrusTrack.repositories.FieldRepository;
import com.spring.CitrusTrack.repositories.TreeRepository;
import com.spring.CitrusTrack.services.TreeService;
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
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;
    private final TreeResponseMapper treeResponseMapper;

    @Override
    public TreeResponseDTO saveTree(TreeDTO treeDTO) {
        if (treeRepository.existsById(treeDTO.getId())) {
            throw new AlreadyExistsException("Tree with id " + treeDTO.getId() + " already exists.");
        }

        validateTree(treeDTO);

        Tree tree = treeMapper.toEntity(treeDTO);
        Field field = tree.getField();
        List<Tree> trees = field.getTrees();

        if (trees == null) {
            trees = new ArrayList<>();
            field.setTrees(trees);
        }
        tree.setAge(TreeStatus.calculateAge(tree.getPlantationDate()));
        tree.setStatus(TreeStatus.determineTreeStatus(tree.getPlantationDate()));
        trees.add(tree);
        tree = treeRepository.save(tree);

        TreeResponseDTO treeResponseDTO = buildTreeResponse(tree);
        log.info("Tree with id {} added successfully.", tree.getId());
        return treeResponseDTO;
    }

    @Override
    public TreeResponseDTO updateTree(TreeDTO treeDTO) {
        if (!treeRepository.existsById(treeDTO.getId())) {
            throw new DoesNotExistsException("Tree with id " + treeDTO.getId() + " does not exist.");
        }

        validateTree(treeDTO);

        Tree treeToUpdate = treeMapper.toEntity(treeDTO);
        Field field = treeToUpdate.getField();
        List<Tree> trees = field.getTrees();

        if (trees != null) {
            trees.removeIf(t -> t.getId().equals(treeToUpdate.getId()));
            trees.add(treeToUpdate);
        }

        treeToUpdate.setAge(TreeStatus.calculateAge(treeToUpdate.getPlantationDate()));
        treeToUpdate.setStatus(TreeStatus.determineTreeStatus(treeToUpdate.getPlantationDate()));
        Tree updatedTree = treeRepository.save(treeToUpdate);
        log.info("Tree with id {} updated successfully.", updatedTree.getId());
        return buildTreeResponse(updatedTree);
    }

    @Override
    public void deleteTree(Long id) {
        Optional<Tree> treeOptional = treeRepository.findById(id);
        if (treeOptional.isPresent()) {
            Tree tree = treeOptional.get();
            Field field = tree.getField();
            List<Tree> trees = field.getTrees();

            if (trees != null) {
                trees.remove(tree);
            }

            treeRepository.delete(tree);
            log.info("Tree with id {} deleted successfully.", id);
        } else {
            throw new DoesNotExistsException("Tree with id " + id + " does not exist.");
        }
    }

    @Override
    public Optional<TreeResponseDTO> getTree(Long id) {
        return treeRepository.findById(id).map(this::buildTreeResponse);
    }

    @Override
    public List<TreeResponseDTO> getAllTree() {
        List<Tree> trees = treeRepository.findAll();
        return treeResponseMapper.toDTOList(trees);
    }

    private void validateTree(TreeDTO treeDTO) {
        if (treeDTO.getPlantationDate() == null || !isValidPlantingPeriod(treeDTO.getPlantationDate())) {
            throw new IllegalArgumentException("Tree must be planted between March and May.");
        }

        Field field = fieldRepository.findById(treeDTO.getField().getId())
                .orElseThrow(() -> new DoesNotExistsException("Field with id " + treeDTO.getField().getId() + " does not exist."));

        List<Tree> trees = field.getTrees();
        if (trees == null) {
            trees = new ArrayList<>();
        }

        double areaInSquareMeters = field.getArea() * 10000;
        double maxTreesPerField = areaInSquareMeters / 100;

        if (trees.size() >= maxTreesPerField) {
            throw new IllegalArgumentException("Field is full. Cannot add more trees.");
        }
    }

    private TreeResponseDTO buildTreeResponse(Tree tree) {
        TreeResponseDTO treeResponseDTO = treeResponseMapper.toDTO(tree);
        treeResponseDTO.setAge(TreeStatus.calculateAge(tree.getPlantationDate()));
        treeResponseDTO.setProductivity(TreeStatus.determineTreeStatus(tree.getPlantationDate()).getAnnualProductivity());
        return treeResponseDTO;
    }

    private boolean isValidPlantingPeriod(LocalDate plantationDate) {
        return plantationDate.getMonthValue() >= 3 && plantationDate.getMonthValue() <= 5;
    }
}
