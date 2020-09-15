package br.com.vinicius.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.vinicius.converter.DozerConverter;
import br.com.vinicius.data.vo.v1.BookVO;
import br.com.vinicius.data.vo.v1.PersonVO;
import br.com.vinicius.model.Book;
import br.com.vinicius.model.Person;
import br.com.vinicius.repository.BookRepository;
import br.com.vinicius.repository.PersonRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO findById(Long id) {
		Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}
	
	public List<BookVO> findAll(){
		return DozerConverter.parseListObject(repository.findAll(), BookVO.class);
	}
	
	public BookVO create(BookVO book) {
		Book entity = DozerConverter.parseObject(book, Book.class);
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {
		Book entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		repository.delete(entity);
	}
	
}
