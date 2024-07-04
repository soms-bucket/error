package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	

	@Query(value = "select p from Patient p where p.doc_id = :docId")
	List<Patient> getByDoctorID(@Param("docId") int docId);
//	@Query(nativeQuery = true, value = "select * from patient where doc_id = 1")
//	List<Patient> findByDoctorID();
}
