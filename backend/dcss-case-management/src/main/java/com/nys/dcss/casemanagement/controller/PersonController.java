package com.nys.dcss.casemanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nys.dcss.casemanagement.dto.PersonDTO;
import com.nys.dcss.casemanagement.service.PersonService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/v1/persons")
@Validated
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Create Person

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(
            @Valid @RequestBody PersonDTO personDTO) {

        PersonDTO createdPerson = personService.createPerson(personDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdPerson);
    }

    // Get Person By ID

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(
            @PathVariable @Positive Long id) {

        return ResponseEntity.ok(
                personService.getPersonById(id));
    }

    // Get All Persons with Pagination

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> getAllPersons(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection =
                Sort.Direction.fromString(direction);

        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, 100),
                Sort.by(sortDirection, sortBy));

        return ResponseEntity.ok(
                personService.getAllPersons(pageable));
    }

    // Update Person

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable @Positive Long id,
            @Valid @RequestBody PersonDTO personDTO) {

        return ResponseEntity.ok(
                personService.updatePerson(id, personDTO));
    }
 

    // Deactivate Person
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivatePerson(
            @PathVariable @Positive Long id) {

        personService.deactivatePerson(id);

        return ResponseEntity.noContent().build();
    }
}