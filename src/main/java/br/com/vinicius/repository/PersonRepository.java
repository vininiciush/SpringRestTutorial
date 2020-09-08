package br.com.vinicius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
