package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FarmDTO {
    @Builder.Default
    private Long id = 0L;

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    private String location;

    @NotNull(message = "Area is required")
    @PositiveOrZero(message = "Area must be 0 or greater")
    private Double area;
}
