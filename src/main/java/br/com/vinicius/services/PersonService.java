package br.com.vinicius.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.vinicius.converter.DozerConverter;
import br.com.vinicius.data.vo.PersonVO;
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
	
	public List<PersonVO> findAll(){
		return DozerConverter.parseListObject(repository.findAll(), PersonVO.class);
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
	
	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(entity);
	}
	
}
