package com.nys.dcss.casemanagement.mapper;

import org.springframework.stereotype.Component;

import com.nys.dcss.casemanagement.dto.PersonDTO;
import com.nys.dcss.casemanagement.entity.Person;

@Component
public class PersonMapper {

	public Person toEntity(PersonDTO personDTO) {

		Person person = new Person();

		person.setFirstName(personDTO.getFirstName());
		person.setMiddleName(personDTO.getMiddleName());
		person.setLastName(personDTO.getLastName());
		person.setDateOfBirth(personDTO.getDateOfBirth());
		person.setGender(personDTO.getGender());
		person.setEmail(personDTO.getEmail());
		person.setPhoneNumber(personDTO.getPhoneNumber());
		person.setAddress(personDTO.getAddress());
		person.setCity(personDTO.getCity());
		person.setState(personDTO.getState());
		person.setZipCode(personDTO.getZipCode());

		return person;
	}

	public PersonDTO toResponseDTO(Person person) {

		PersonDTO dto = new PersonDTO();

		dto.setId(person.getId());
		dto.setFirstName(person.getFirstName());
		dto.setMiddleName(person.getMiddleName());
		dto.setLastName(person.getLastName());
		dto.setDateOfBirth(person.getDateOfBirth());
		dto.setGender(person.getGender());
		dto.setEmail(person.getEmail());
		dto.setPhoneNumber(person.getPhoneNumber());
		dto.setAddress(person.getAddress());
		dto.setCity(person.getCity());
		dto.setState(person.getState());
		dto.setZipCode(person.getZipCode());
		dto.setActive(person.getActive());
		dto.setCreatedAt(person.getCreatedAt());
		dto.setUpdatedAt(person.getUpdatedAt());

		return dto;
	}
	// Convert Person Entity to PersonDTO

	public PersonDTO toDTO(Person person) {
		PersonDTO dto = new PersonDTO();
		dto.setId(person.getId());
		dto.setFirstName(person.getFirstName());
		dto.setMiddleName(person.getMiddleName());
		dto.setLastName(person.getLastName());
		dto.setDateOfBirth(person.getDateOfBirth());
		dto.setGender(person.getGender());
		dto.setEmail(person.getEmail());
		dto.setPhoneNumber(person.getPhoneNumber());
		dto.setAddress(person.getAddress());
		dto.setCity(person.getCity());
		dto.setState(person.getState());
		dto.setZipCode(person.getZipCode());
		dto.setActive(person.getActive());
		dto.setCreatedAt(person.getCreatedAt());
		dto.setUpdatedAt(person.getUpdatedAt());

		return dto;

	}
}