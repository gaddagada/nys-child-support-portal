package com.nys.dcss.casemanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonResponseDTO {
	private Long personId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private LocalDateTime createdAt;
	private boolean active;

	public PersonResponseDTO() {
	}

	public Long getPersonId() {
	    return personId;
	}

	public void setPersonId(Long personId) {
	    this.personId = personId;
	}

	public String getFirstName() {
	    return firstName;
	}

	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}

	public String getLastName() {
	    return lastName;
	}

	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
	    return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
	    this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getCreatedAt() {
	    return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
	    this.createdAt = createdAt;
	}

	public boolean isActive() {
	    return active;
	}

	public void setActive(boolean active) {
	    this.active = active;
	}
}
