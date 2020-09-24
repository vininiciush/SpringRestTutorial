package br.com.vinicius.services;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinicius.converter.DozerConverter;
import br.com.vinicius.data.vo.v1.PersonVO;
import br.com.vinicius.model.Person;
import br.com.vinicius.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public Page<PersonVO> findAll(Pageable pageable){
		Page<Person> page = repository.findAll(pageable);
		
		
		return page.map(new Function<Person, PersonVO>() {
			@Override
			public PersonVO apply(Person t) {
				return DozerConverter.parseObject(t, PersonVO.class);
			}
		
		});
	}
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable){
		Page<Person> page = repository.findPersonByName(firstName, pageable);
		
		
		return page.map(new Function<Person, PersonVO>() {
			@Override
			public PersonVO apply(Person t) {
				return DozerConverter.parseObject(t, PersonVO.class);
			}
		
		});
	}
	
	
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirsName(person.getFirsName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(entity);
	}
	
}
