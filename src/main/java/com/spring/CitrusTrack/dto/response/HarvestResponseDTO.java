package com.spring.CitrusTrack.dto.response;

import com.spring.CitrusTrack.entities.enums.Season;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HarvestResponseDTO {
    private Long id;

    @NotNull(message = "Season is required")
    private Season season;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "Stock is required")
    @Min(value = 1, message = "Stock must be greater than 0")
    private Double stock;
}
