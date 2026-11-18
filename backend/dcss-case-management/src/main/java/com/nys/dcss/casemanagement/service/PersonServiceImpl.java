package com.nys.dcss.casemanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nys.dcss.casemanagement.dto.PersonDTO;
import com.nys.dcss.casemanagement.entity.Person;
import com.nys.dcss.casemanagement.exception.ResourceNotFoundException;
import com.nys.dcss.casemanagement.mapper.PersonMapper;
import com.nys.dcss.casemanagement.repository.PersonRepository;

import org.springframework.transaction.annotation.Transactional;
@Service
    @Transactional
    public class PersonServiceImpl implements PersonService {

        private final PersonRepository personRepository;
        private final PersonMapper personMapper;

        // Constructor Dependency Injection
        public PersonServiceImpl(
                PersonRepository personRepository,
                PersonMapper personMapper) {
            this.personRepository = personRepository;
            this.personMapper = personMapper;
        }

        /**
         * Create a new person.
         */
        @Override
        public PersonDTO createPerson(PersonDTO personDTO) {
            Person person = personMapper.toEntity(personDTO);
            Person savedPerson = personRepository.save(person);
            return personMapper.toDTO(savedPerson);
        }

        /**
         * Retrieve a person by ID.
         */
        @Override
        @Transactional(readOnly = true)
        public PersonDTO getPersonById(Long id) {
            Person person = findPersonOrThrow(id);
            return personMapper.toDTO(person);
        }

        /**
         * Retrieve all persons with pagination and sorting.
         */
        @Override
        @Transactional(readOnly = true)
        public Page<PersonDTO> getAllPersons(Pageable pageable) {
            return personRepository.findAll(pageable)
                    .map(personMapper::toDTO);
        }

        /**
         * Update an existing person.
         */
        @Override
        public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
            Person existingPerson = findPersonOrThrow(id);
            existingPerson.setFirstName(personDTO.getFirstName());
            existingPerson.setMiddleName(personDTO.getMiddleName());
            existingPerson.setLastName(personDTO.getLastName());
            existingPerson.setDateOfBirth(personDTO.getDateOfBirth());
            existingPerson.setGender(personDTO.getGender());
            existingPerson.setEmail(personDTO.getEmail());
            existingPerson.setPhoneNumber(personDTO.getPhoneNumber());
            existingPerson.setAddress(personDTO.getAddress());
            existingPerson.setCity(personDTO.getCity());
            existingPerson.setState(personDTO.getState());
            existingPerson.setZipCode(personDTO.getZipCode());

            Person updatedPerson = personRepository.save(existingPerson);

            return personMapper.toDTO(updatedPerson);
        }

        /**
         * Deactivate a person (Soft Delete).
         */
        @Override
        public void deactivatePerson(Long id) {
            Person person = findPersonOrThrow(id);
            person.setActive(false);
            personRepository.save(person);
        }

        /**
         * Find person by ID or throw custom exception.
         */
        private Person findPersonOrThrow(Long id) {
            return personRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Person not found with ID: " + id
                            ));
        }

        @Override
        @Transactional(readOnly = true)
        public List<PersonDTO> getAllPersons() {
            return personRepository.findAll()
                    .stream()
                    .map(personMapper::toDTO)
                    .toList();
        }

       
}