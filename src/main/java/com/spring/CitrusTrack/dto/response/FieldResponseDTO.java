package com.spring.CitrusTrack.dto.response;

import com.spring.CitrusTrack.dto.embedded.EmbeddedTreeDTO;
import com.spring.CitrusTrack.dto.FarmDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FieldResponseDTO {
    private Double area;
    private FarmDTO farm;
    private List<EmbeddedTreeDTO> trees = new ArrayList<>();
}
