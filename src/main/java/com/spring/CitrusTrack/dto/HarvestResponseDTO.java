package com.spring.CitrusTrack.dto;

import com.spring.CitrusTrack.entities.enums.Season;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class HarvestResponseDTO {
    private Long id;

    @NotNull(message = "Season is required")
    private Season season;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Field is required")
    private FieldDTO field;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Double quantity;
}
