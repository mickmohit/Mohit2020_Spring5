package com.example.demo.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins="*")
public class BookController {

	@Autowired
	private BookService bookService;
	
	/*
	 * @GetMapping public ResponseEntity<Collection<Book>> findAll(){ return new
	 * ResponseEntity<>(bookService.findAll(),HttpStatus.OK); }
	 */

	//this can only handle pagination not sorting
	/*
	 * @GetMapping public ResponseEntity<Page<Book>> findAll(Pageable pageable){
	 * return new ResponseEntity<>(bookService.findAll(pageable),HttpStatus.OK); }
	 */
	
	// this will handle pagination and sorting both at once
	@GetMapping
	public ResponseEntity<Page<Book>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir){	
		return new ResponseEntity<>(bookService.findAll(
				PageRequest.of(pageNumber, pageSize, 
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending())
					),HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchText}")
	public ResponseEntity<Page<Book>> findAll(Pageable pageable, @PathVariable String searchText){	
		return new ResponseEntity<>(bookService.findAll( pageable,searchText),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Optional<Book> book=bookService.findById(id);
		 if(!book.isPresent())
		 {
			 throw new BookNotFoundException("Book Not Found");
		 }
		return new ResponseEntity<>(book.get(),HttpStatus.OK);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> save(@RequestBody Book book) {
		System.out.println("boook000000000000000000  "+ book.getAuthor() +"--"+book.getIsbnNumber());
		return new ResponseEntity<>(bookService.save(book),HttpStatus.CREATED);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> update(@RequestBody Book book) {
		Book currentBook = bookService.findById(book.getId()).orElse(null);
		 
        if (currentBook == null) {
             return  ResponseEntity.notFound().build();
        }
 
        currentBook.setAuthor(book.getAuthor());
        currentBook.setCoverPhotoURL(book.getCoverPhotoURL());
        currentBook.setIsbnNumber(book.getIsbnNumber());
        currentBook.setLanguage(book.getLanguage());
        currentBook.setPrice(book.getPrice());
        currentBook.setTitle(book.getTitle());
        currentBook.setGenre(book.getGenre());
        return new ResponseEntity<>(bookService.save(currentBook), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		Optional<Book> book=bookService.findById(id);
		 if(!book.isPresent())
		 {
			 throw new BookNotFoundException("Book Not Found");
		 }
		
		return new ResponseEntity<>(bookService.deleteById(id),HttpStatus.OK);
	}
	
	@GetMapping("/genre")
	public ResponseEntity<Set<String>> findAllGenre()
	{
		return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Technology","Science","History", "Medical")),HttpStatus.OK);
	}
	
	@GetMapping("/invalid")
	public ResponseEntity<String> invalid()
	{
		return new ResponseEntity<>(
				"{'message':'Request is not appropiate, Please Check the provided details.'}",HttpStatus.OK);

	}
}
