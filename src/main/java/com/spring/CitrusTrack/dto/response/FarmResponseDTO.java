package com.spring.CitrusTrack.dto.response;

import com.spring.CitrusTrack.dto.embedded.EmbeddedFieldDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FarmResponseDTO {
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    private String location;

    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.2" , message = "Area must be greater than 0.2")
    private Double area;

    private LocalDate creationDate;

    private List<EmbeddedFieldDTO> fields = new ArrayList<>();
}