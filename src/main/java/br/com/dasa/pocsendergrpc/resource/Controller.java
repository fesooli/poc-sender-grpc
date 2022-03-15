package br.com.dasa.pocsendergrpc.resource;

import br.com.dasa.pocsendergrpc.client.TestService;
import br.com.dasa.pocsendergrpc.client.dto.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class Controller {

    @Autowired
    private TestService service;

    @GetMapping
    public List<ScheduleResponse> search() throws IOException {
        return service.search();
    }
}
