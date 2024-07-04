package com.example.demo.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Patient;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient(name = "patient-service", url = "${PRODUCT_SERVICE:http://localhost:9193}", 
													fallback = PatientFeignFallback.class)

public interface PatientFeignClient {

	@GetMapping("/patapi/patient/doctor/{doctorId}")
	List<Patient> getPatientByDoctorId(@PathVariable("doctorId") int doctorId);

	@RequestMapping(method = RequestMethod.GET, value = "/patientnotfound")
	String getException();
}
