package com.spring.CitrusTrack.dto;

import com.spring.CitrusTrack.entities.enums.TreeStatus;
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
public class TreeResponseDTO {
    private Long id;

    @NotNull(message = "Plantation date is required")
    private LocalDate plantationDate;

    @NotNull(message = "Status is required")
    @Size(min=3, max=15, message = "Status must be between 3 and 15 characters")
    private TreeStatus status;

    @NotNull(message = "Field id is required")
    private FieldDTO field;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;
}
