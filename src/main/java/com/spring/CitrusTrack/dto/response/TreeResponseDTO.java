package com.spring.CitrusTrack.dto.response;

import com.spring.CitrusTrack.dto.FieldDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TreeResponseDTO {
    private Long id;

    @NotNull(message = "Plantation date is required")
    @PastOrPresent(message = "Plantation date must be in the past or present")
    private LocalDate plantationDate;

    @NotNull(message = "Field id is required")
    private FieldDTO field;

    @NotNull(message = "Productivity is required")
    @Min(value = 1, message = "Productivity must be greater than 0")
    private Double productivity;
}
