package com.spring.CitrusTrack.repositories;

import com.spring.CitrusTrack.entities.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
