package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

import jakarta.validation.Valid;
@Service
public class PatientService implements IPatientService{
	//for getting log in console
	private final Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	@Autowired
	PatientRepository patientRepo;
	
	@Override
	public List<Patient> getPatientFromDatabase() {
		logger.info("Fetching all records from Database...");
		return patientRepo.findAll();
	}
	
	@Override
	public Optional<Patient> getPatientById(int id) {
		
		return patientRepo.findById(id);
	}

	@Override
	public void deletePatientById(int id) {
		// TODO Auto-generated method stub
		patientRepo.deleteById(id);
		
	}

	@Override
	public Patient createPatient(@Valid Patient newPatient) {
		// TODO Auto-generated method stub
		return patientRepo.save(newPatient);
	}

	@Override
	public ResponseEntity<Patient> updatePatient(Integer patientId, @Valid Patient newPatient) {
		Optional<Patient> existingProduct = patientRepo.findById(patientId);
		existingProduct.get().setAge(newPatient.getAge());
		existingProduct.get().setAddress(newPatient.getAddress());
		patientRepo.save(existingProduct.get());
		return ResponseEntity.ok(existingProduct.get());
	}
//	@Override
//	public List<Patient> getPatientByDoctor(@PathVariable int doctorId){
//		return patienceRepo.findByDoctorID(doctorId);
//	}
	
	
	
	
	

}
