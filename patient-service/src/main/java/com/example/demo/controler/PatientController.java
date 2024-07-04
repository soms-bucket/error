package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.IPatientService;

import jakarta.validation.Valid;

@RestController // http://localhost:9191/api/hello
@RequestMapping("/patapi")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private PatientRepository patientRepo;

	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot Patient.... Welcome to You!";
	}
	
	
	//for Feign
	// http://localhost:9193/api/patient/doctor/1
	@GetMapping("/patient/doctor/{doctorId}")
	public List<Patient> getPatientByDoctor(@PathVariable int doctorId) {
        return patientRepo.getByDoctorID(doctorId);
	}


	@GetMapping("/patient")
	List<Patient> patient() {
		// System.out.println("Running");
		return patientService.getPatientFromDatabase();

	}

	// http://localhost:9192/api/patient/1
	@GetMapping("/patient/{id}")
	Optional<Patient> findByPatientId(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Patient> product = patientService.getPatientById(id);
		product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return product;
	}

	// http://localhost:9192/api/patient/1
	@DeleteMapping("/patient/{id}")
	void deletePatientById(@PathVariable int id) {
		patientService.deletePatientById(id);
	}
	
	// http://localhost:9192/api/patient
	@PostMapping("/patient")
	public Patient createPatient(@Valid @RequestBody Patient newPatient) {
		return patientService.createPatient(newPatient);
	}

	// http://localhost:9192/api/patient/1
	@PutMapping("/patient/{id}")
	public ResponseEntity<Patient> updatePatient
	(@PathVariable(value = "id") Integer patientId,@Valid @RequestBody Patient newPatient) {
		return patientService.updatePatient(patientId,newPatient);
	}

	// http://localhost:9192/api/patient/req?id=1
	@GetMapping("/patient/req")
	Optional<Patient> findByPatientIdUsingRequest(@RequestParam int id) {
		
		return patientService.getPatientById(id);
	}
	
	
	
}
