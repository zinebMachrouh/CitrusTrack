package com.spring.CitrusTrack.dto;

import com.spring.CitrusTrack.entities.enums.Season;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HarvestDTO {
    @Builder.Default
    private Long id = 0L;

    @NotNull(message = "Date is required")
    private LocalDate date;
}
