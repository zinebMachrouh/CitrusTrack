package com.spring.CitrusTrack.repositories;

import com.spring.CitrusTrack.entities.Harvest;
import com.spring.CitrusTrack.entities.HarvestDetail;
import com.spring.CitrusTrack.entities.Tree;
import com.spring.CitrusTrack.entities.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {
    @Query("SELECT SUM(hd.quantity) FROM HarvestDetail hd WHERE hd.harvest = :harvest")
    Double sumQuantityByHarvest(@Param("harvest") Harvest harvest);

    boolean existsByTreeAndHarvestSeason(Tree tree, Season season);
    List<HarvestDetail> findByHarvest(Harvest harvest);
    Optional<HarvestDetail> findByTreeAndHarvestSeason(Tree tree, Season season);
}
