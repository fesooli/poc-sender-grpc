package br.com.dasa.pocsendergrpc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private Integer brandId;
    private String location;
    private Integer roomId;
    private List<String> exam;
    private List<String> relatedExam;
    private String horarioExameRelacionado;
    private String startHour;
    private String endHour;
    private String healthPlan;
    private String date;
}
