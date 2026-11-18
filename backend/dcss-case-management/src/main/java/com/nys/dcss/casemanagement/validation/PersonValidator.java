package com.nys.dcss.casemanagement.validation;

import org.springframework.stereotype.Component;

import com.nys.dcss.casemanagement.dto.PersonDTO;

@Component
public class PersonValidator {
	public void validatePerson(PersonDTO personDTO) {
		if(personDTO == null) {
			throw new IllegalArgumentException(
					"Person information cannot be nulls");
		}
		
		if(personDTO.getFirstName() == null || 
				personDTO.getFirstName().isBlank()) {
			throw new IllegalArgumentException(
					"First name is required"); 
		}
		
		if(personDTO.getLastName() == null || 
				personDTO.getLastName().isBlank()) {
			throw new IllegalArgumentException(
					"Last name is required"); 
		}
		
	}
}
