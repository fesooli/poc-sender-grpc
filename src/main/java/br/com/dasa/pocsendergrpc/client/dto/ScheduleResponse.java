package br.com.dasa.pocsendergrpc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {

    private Integer brandId;
    private String locationId;
    private Integer roomId;
    private List<String> exam;
    private String startTime;
    private String endTime;
}
