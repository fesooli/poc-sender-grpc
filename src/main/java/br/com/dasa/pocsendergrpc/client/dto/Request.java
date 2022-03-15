package br.com.dasa.pocsendergrpc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Patient patient;
    private List<Schedule> schedule;
}
