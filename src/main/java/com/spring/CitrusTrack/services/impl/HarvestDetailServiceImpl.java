package com.spring.CitrusTrack.services.impl;

import com.spring.CitrusTrack.dto.HarvestDetailDTO;
import com.spring.CitrusTrack.mappers.HarvestDetailMapper;
import com.spring.CitrusTrack.repositories.HarvestDetailRepository;
import com.spring.CitrusTrack.repositories.HarvestRepository;
import com.spring.CitrusTrack.repositories.TreeRepository;
import com.spring.CitrusTrack.services.HarvestDetailService;
import com.spring.CitrusTrack.entities.Harvest;
import com.spring.CitrusTrack.entities.HarvestDetail;
import com.spring.CitrusTrack.entities.Tree;
import com.spring.CitrusTrack.entities.enums.TreeStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class HarvestDetailServiceImpl implements HarvestDetailService {
    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailMapper harvestDetailMapper;

    @Override
    public HarvestDetailDTO saveHarvestDetail(HarvestDetailDTO harvestDetailDTO) {
        Harvest harvest = harvestRepository.findById(harvestDetailDTO.getHarvest().getId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));

        Tree tree = treeRepository.findById(harvestDetailDTO.getTree().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));

        if (harvestDetailRepository.existsByTreeAndHarvestSeason(tree, harvest.getSeason())) {
            throw new IllegalArgumentException("Tree has already been included in another harvest for this season");
        }

        TreeStatus treeStatus = TreeStatus.determineTreeStatus(tree.getPlantationDate());
        double maxQuantity = treeStatus.getAnnualProductivity() / 4;
        if (harvestDetailDTO.getQuantity() > maxQuantity) {
            throw new IllegalArgumentException("Quantity exceeds tree's seasonal productivity");
        }

        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(harvestDetailDTO);
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        HarvestDetail savedHarvestDetail = harvestDetailRepository.save(harvestDetail);

        double totalQuantity = harvestDetailRepository.sumQuantityByHarvest(harvest);
        harvest.setQuantity(totalQuantity);
        harvest.setStock(totalQuantity);
        harvestRepository.save(harvest);

        return harvestDetailMapper.toDTO(savedHarvestDetail);
    }

    @Override
    public Optional<HarvestDetailDTO> getHarvestDetail(Long id) {
        return harvestDetailRepository.findById(id)
                .map(harvestDetailMapper::toDTO);
    }

    @Override
    public HarvestDetailDTO updateHarvestDetail(HarvestDetailDTO harvestDetailDTO) {
        HarvestDetail existingHarvestDetail = harvestDetailRepository.findById(harvestDetailDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found"));

        double oldQuantity = existingHarvestDetail.getQuantity();
        existingHarvestDetail.setQuantity(harvestDetailDTO.getQuantity());

        Tree tree = treeRepository.findById(harvestDetailDTO.getTree().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tree not found"));

        Harvest harvest = harvestRepository.findById(harvestDetailDTO.getHarvest().getId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));

        Optional<HarvestDetail> existingHarvestForTree = harvestDetailRepository.findByTreeAndHarvestSeason(tree, harvest.getSeason());
        if (existingHarvestForTree.isPresent() && !existingHarvestForTree.get().getId().equals(harvestDetailDTO.getId())) {
            throw new IllegalArgumentException("Tree cannot be part of more than one harvest per season");
        }

        existingHarvestDetail.setTree(tree);
        existingHarvestDetail.setHarvest(harvest);

        TreeStatus treeStatus = TreeStatus.determineTreeStatus(tree.getPlantationDate());
        double maxQuantity = treeStatus.getAnnualProductivity() / 4;
        if (harvestDetailDTO.getQuantity() > maxQuantity) {
            throw new IllegalArgumentException("Quantity exceeds tree's seasonal productivity");
        }

        double stockAdjustment = harvestDetailDTO.getQuantity() - oldQuantity;
        double currentStock = harvest.getStock();

        harvest.setStock(currentStock + stockAdjustment);

        harvestRepository.save(harvest);

        HarvestDetail updatedHarvestDetail = harvestDetailRepository.save(existingHarvestDetail);

        double totalQuantity = harvestDetailRepository.sumQuantityByHarvest(updatedHarvestDetail.getHarvest());
        updatedHarvestDetail.getHarvest().setQuantity(totalQuantity);
        harvestRepository.save(updatedHarvestDetail.getHarvest());

        return harvestDetailMapper.toDTO(updatedHarvestDetail);
    }

    @Override
    public void deleteHarvestDetail(Long id) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HarvestDetail not found"));

        Harvest harvest = harvestDetail.getHarvest();

        if (harvest.getQuantity() != null && harvestDetail.getQuantity() != null) {
            double updatedQuantity = harvest.getQuantity() - harvestDetail.getQuantity();
            harvest.setQuantity(Math.max(0, updatedQuantity));
            harvestRepository.save(harvest);
        }

        harvestDetailRepository.deleteById(id);
    }

    @Override
    public List<HarvestDetailDTO> getAllHarvestDetail() {
        return harvestDetailMapper.toDTOList(harvestDetailRepository.findAll());
    }
}
