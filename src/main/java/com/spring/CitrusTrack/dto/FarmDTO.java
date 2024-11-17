package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FarmDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "location is required")
    @Size(min = 3, max = 50, message = "location must be between 3 and 50 characters")
    private String location;

    @NotBlank(message = "area is required")
    @Min(value = 1, message = "area must be greater than 0")
    private Double area;
}
