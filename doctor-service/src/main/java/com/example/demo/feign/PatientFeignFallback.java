package com.example.demo.feign;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientFeignFallback implements PatientFeignClient {

	@Override
    public List<Patient> getPatientByDoctorId(int n) {
        return Stream.of(
                new Patient("Komal","female",31,"45678","jaipur"),
                new Patient("parthavi","female",23,"78952","jaipur"),
                new Patient("Rik","male",41,"4644","kanpur"),
                new Patient("suman","female",56,"22223","patna")
        ).collect(Collectors.toList());
    }
	
	@Override
    public String getException() {
        return "Fixed response";
    }
}
