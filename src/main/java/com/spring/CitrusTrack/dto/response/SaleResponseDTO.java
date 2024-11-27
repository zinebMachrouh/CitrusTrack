package com.spring.CitrusTrack.dto.response;

import com.spring.CitrusTrack.dto.HarvestDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleResponseDTO {
    private Long id;

    @NotNull(message = "The sale date is required")
    private LocalDate date;

    @NotNull(message = "The unit price is required")
    @Min(value = 1, message = "The unit price must be greater than 0")
    private Double unitPrice;

    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "The quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "The client is required")
    @Size(min = 3, max = 50, message = "The client must be between 3 and 50 characters")
    private String client;

    @NotNull(message = "The harvest id is required")
    private HarvestDTO harvest;

    @NotNull(message = "The total revenue is required")
    @Min(value = 1, message = "The total revenue must be greater than 0")
    private Double totalRevenue;
}
