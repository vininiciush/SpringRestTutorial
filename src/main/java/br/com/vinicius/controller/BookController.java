package br.com.vinicius.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.data.vo.v1.BookVO;
import br.com.vinicius.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/book/v1")
@Api(tags = "Book End-Point")
public class BookController {
	
	@Autowired
	private BookService services;
	
	@ApiOperation(value = "find all books recorded")
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<BookVO> findAll() throws Exception {
		List<BookVO> voList = services.findAll();
		for(BookVO bookVO : voList) {
			bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
		}
		
		return voList;
	}
	
	@ApiOperation(value = "find a book by id")
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO findById(@PathVariable("id") Long id) throws Exception {
		BookVO bookVO =  services.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "create a new book")
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO create(@RequestBody BookVO book) throws Exception {
		BookVO bookVO = services.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "update book by id")
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
	public BookVO update(@RequestBody BookVO book) throws Exception {
		BookVO bookVO = services.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getId())).withSelfRel());
		return bookVO;
	}
	
	@ApiOperation(value = "delete book by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
