package com.comic.user.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comic.user.exception.ResourceNotFoundException;
import com.comic.user.model.ComicBook;
import com.comic.user.model.User;
import com.comic.user.repository.ComicBookRepository;

/**
 * @author Ashish
 * 
 * This {@link ComicBookController} class is used to get Comic book details.
 *
 */
@RestController
@RequestMapping("api/comic")
public class ComicBookController {

	/*
	 *  Comic Book Repository
	 */
	@Autowired
	private ComicBookRepository comicBookRepository;


	/**
	 * PostMapping creates a new Comic book in the database.
	 * 
	 * @param comicBook details
	 * @return comicBook
	 */
	@PostMapping() 
	public ResponseEntity<ComicBook> createComicBook(@Valid @RequestBody ComicBook comicBook){        
		return new ResponseEntity<ComicBook>(comicBookRepository.save(comicBook),HttpStatus.CREATED);
	}

	/**
	 *GetMapping provides detail of an comic book with a particular id in the
	 * database. 
	 * 
	 * @param Comic Book id
	 * @return Comic book
	 */
	@GetMapping("/{id}") 
	public ComicBook getComicBookById(@PathVariable int id){
		// If the record exists by id return it, otherwise throw an exception
		return this.comicBookRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("ComicBook", id)
				);
	}

	/**GetMapping provides detail of all the comic books  in the
	 * database.
	 * 
	 * @return List of Comic book
	 */
	@GetMapping() 
	public ResponseEntity<List<ComicBook>> getComicBooks(){
		return new ResponseEntity<List<ComicBook>>(comicBookRepository.findAll(),HttpStatus.OK);
	}

	/**
	 * PutMapping updates the details of a comic book with a particular id in
	 * the database.
	 * 
	 * @param comicBook details
	 * @return updated comicBook
	 */
	@PutMapping("/{id}")
	public ComicBook updateComicBookById(@PathVariable(value = "id") Integer id,@Valid @RequestBody ComicBook comicBook){
		// Finds comicBook by id, maps it's content, updates new values and save. Throws an exception if not found.
		return this.comicBookRepository.findById(id).map((toUpdate) -> {
			toUpdate.setTitle(comicBook.getTitle());
			toUpdate.setWriter(comicBook.getWriter());
			toUpdate.setPublisher(comicBook.getPublisher());
			toUpdate.setGenre(comicBook.getGenre());
			return this.comicBookRepository.save(toUpdate);
		}).orElseThrow(() -> new ResourceNotFoundException("ComicBook", comicBook.getId()));
	}

	/**
	 * DeleteMapping deletes a comic book with a particular id in the
	 * database.
	 * 
	 * @param Comic book id
	 * @return updated list of comic book
	 */
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteComicBookById(@PathVariable int id){
		// If id exists, delete the record and return a response message, otherwise throws exception
		return this.comicBookRepository.findById(id).map((toDelete) -> {
			this.comicBookRepository.delete(toDelete);
			return ResponseEntity.ok("ComicBook id " + id + " deleted");
		}).orElseThrow(() -> new ResourceNotFoundException("ComicBook", id));
	}

	/**
	 * GetMapping finds Users who all have a comic book of particular id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/users")
	public Set<User> getUsersByComicBookId(@PathVariable int id){
		// Finds comic by id and returns it's recorded users, otherwise throws exception
		return this.comicBookRepository.findById(id).map((comicBook) -> {
			return comicBook.getUsers();
		}).orElseThrow(() -> new ResourceNotFoundException("ComicBook", id));
	}
}
