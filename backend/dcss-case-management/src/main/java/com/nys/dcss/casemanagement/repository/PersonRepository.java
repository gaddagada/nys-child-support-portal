package com.nys.dcss.casemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nys.dcss.casemanagement.entity.Person;

public interface PersonRepository
        extends JpaRepository<Person, Long>,
                JpaSpecificationExecutor<Person> {

    Optional<Person> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}