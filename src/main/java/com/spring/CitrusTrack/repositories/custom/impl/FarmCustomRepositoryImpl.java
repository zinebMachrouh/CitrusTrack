package com.spring.CitrusTrack.repositories.custom.impl;

import com.spring.CitrusTrack.repositories.custom.FarmCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.spring.CitrusTrack.dto.FarmResponseDTO;
import com.spring.CitrusTrack.entities.Farm;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FarmCustomRepositoryImpl implements FarmCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FarmResponseDTO> searchFarms(String name, String location, Double area) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farmRoot = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("name")), "%" + name.trim().toLowerCase() + "%"));
        }
        if (location != null && !location.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("location")), "%" + location.trim().toLowerCase() + "%"));
        }
        if (area != null) {
            predicates.add(cb.greaterThanOrEqualTo(farmRoot.get("area"), area));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        List<Farm> farms = entityManager.createQuery(query).getResultList();
        return farms.stream()
                .map(farm -> FarmResponseDTO.builder()
                        .id(farm.getId())
                        .name(farm.getName())
                        .location(farm.getLocation())
                        .area(farm.getArea())
                        .creationDate(farm.getCreationDate())
                        .build())
                .toList();
    }
}
