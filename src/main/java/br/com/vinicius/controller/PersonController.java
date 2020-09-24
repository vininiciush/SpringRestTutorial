package br.com.vinicius.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.hateoas.*;

import br.com.vinicius.data.vo.v1.PersonVO;
import br.com.vinicius.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/person/v1")
@Api(tags = "Person End-Point")
//@CrossOrigin(origins = "http://localhost:8080")
public class PersonController {
	
	@Autowired
	private PersonService services;
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
//	@ApiOperation(value = "find all people recorded")
//	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
//	public List<PersonVO> findAll() throws Exception {
//		List<PersonVO> voList = services.findAll();
//		for(PersonVO personVO : voList) {
//			personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
//		}
//		
//		return voList;
//	}
	
	@ApiOperation(value = "find all people recorded")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAllPageble(@RequestParam(value="page",defaultValue = "0") Integer page, 
										 @RequestParam(value = "limit", defaultValue = "12") Integer limit,
										 @RequestParam(value = "direction", defaultValue = "asc") String direction
										 ) throws Exception {
		
		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC: Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firsName"));
		Page<PersonVO> voList = services.findAll(pageable);
		for(PersonVO personVO : voList) {
			personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		}
		
		PagedModel<?> model = assembler.toModel(voList);
		
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	
	@ApiOperation(value = "find person by name")
	@GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findPersonByName(
										 @PathVariable(value = "firstName") String firstName,
										 @RequestParam(value="page",defaultValue = "0") Integer page, 
										 @RequestParam(value = "limit", defaultValue = "12") Integer limit,
										 @RequestParam(value = "direction", defaultValue = "asc") String direction) throws Exception {
		
		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC: Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firsName"));
		Page<PersonVO> voList = services.findPersonByName(firstName, pageable);
		for(PersonVO personVO : voList) {
			personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		}
		
		PagedModel<?> model = assembler.toModel(voList);
		
		return new ResponseEntity<>(model,HttpStatus.OK);
	}
	
	@ApiOperation(value = "find a person by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		PersonVO personVO =  services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "create a new person")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		PersonVO personVO = services.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "update person by id")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		PersonVO personVO = services.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "disable person by id")
	@PatchMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
		PersonVO personVO =  services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value = "delete person by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
