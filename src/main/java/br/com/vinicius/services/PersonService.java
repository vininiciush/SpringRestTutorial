package br.com.vinicius.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.vinicius.model.Person;

@Service
public class PersonService {
	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirsName("Vinicius");
		person.setLastName("Michel Schunke");
		person.setAddress("Candelária, Rio Grande do Sul, Brasil");
		person.setGender("Male");
		return person;
	}
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	
	public Person create(Person person) {
		//Salva no Bd e retorna
		return person;
	}
	
	public Person update(Person person) {
		//update no Bd e retorna
		return person;
	}
	
	public void delete(String id) {
		//Deletaria o recurso no bd
	}
	

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirsName("Vinicius");
		person.setLastName("Michel Schunke");
		person.setAddress("Candelária, Rio Grande do Sul, Brasil");
		person.setGender("Male");
		return person;
	}
}
