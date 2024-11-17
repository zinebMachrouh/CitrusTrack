package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HarvestDetailDTO {
    @Builder.Default
    private Long id = 0L;

    @NotNull(message = "Quantity is required")
    private Double quantity;

    @NotNull(message = "Harvest is required")
    private HarvestDTO harvest;

    @NotNull(message = "Tree is required")
    private TreeDTO tree;
}
