package br.com.dasa.pocsendergrpc.client;

import br.com.dasa.pocgrpc.AvailabilityRequest;
import br.com.dasa.pocgrpc.Patient;
import br.com.dasa.pocgrpc.ReceiverTestServiceGrpc;
import br.com.dasa.pocgrpc.Schedule;
import br.com.dasa.pocsendergrpc.client.dto.Request;
import br.com.dasa.pocsendergrpc.client.dto.ScheduleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @GrpcClient("client")
    ReceiverTestServiceGrpc.ReceiverTestServiceBlockingStub client;

    public List<ScheduleResponse> search() throws IOException {
        var json =
                new ObjectMapper().readValue(
                        ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "disponibilidade.json"),
                        Request.class);
        var request = AvailabilityRequest.newBuilder()
                .setPatientInfo(Patient.newBuilder()
                        .setBirthDate(json.getPatient().getBirthDate())
                        .setGender(json.getPatient().getGender())
                        .setHeight(json.getPatient().getHeight())
                        .setWeight(json.getPatient().getWeight())
                        .build())
                .addAllSchedule(json.getSchedule()
                        .parallelStream()
                        .map(disp -> Schedule.newBuilder()
                                .setBrandId(disp.getBrandId())
                                .setLocationId(disp.getLocation())
                                .setRoomId(disp.getRoomId())
                                .setDate(disp.getDate())
                                .setEndHour(disp.getEndHour())
                                .setStartHour(disp.getStartHour())
                                .setRelatedExamTime(disp.getHorarioExameRelacionado())
                                .setHealthPlan(disp.getHealthPlan())
                                .addAllExam(disp.getExam())
                                .addAllRelatedExam(disp.getRelatedExam())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        var response = client.receive(request).getResponseList();
        return response.stream()
                .map(res -> ScheduleResponse.builder()
                        .brandId(res.getBrandId())
                        .locationId(res.getLocationId())
                        .roomId(res.getRoomId())
                        .exam(res.getExamList())
                        .startTime(res.getStartTime())
                        .endTime(res.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }
}
