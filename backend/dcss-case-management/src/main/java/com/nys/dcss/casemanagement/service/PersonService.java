package com.nys.dcss.casemanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nys.dcss.casemanagement.dto.PersonDTO;

import jakarta.validation.constraints.Positive;

public interface PersonService {

	// Create a new person
	PersonDTO createPerson(PersonDTO personDTO);
	// Retrieve person by ID

	PersonDTO getPersonById(Long id);

	List<PersonDTO> getAllPersons();
	// Update existing person

	PersonDTO updatePerson(Long id, PersonDTO personDTO);

	// Deactivate person
	void deactivatePerson(Long id);

	// Retrieve all persons with pagination
	Page<PersonDTO> getAllPersons(Pageable pageable);

}
