package br.com.dasa.pocsendergrpc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private String birthDate;
    private String gender;
    private Double height;
    private Double weight;
}
