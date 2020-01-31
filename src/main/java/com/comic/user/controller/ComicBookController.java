package com.comic.user.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

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

import com.comic.user.exception.ResourceNotFoundException;
import com.comic.user.model.ComicBook;
import com.comic.user.model.User;
import com.comic.user.repository.ComicBookRepository;
import com.comic.user.repository.UserRepository;

@RestController
@RequestMapping("api/comic")
public class ComicBookController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComicBookRepository comicBookRepository;


	@PostMapping() 
    public ComicBook createComicBook(@Valid @RequestBody ComicBook comicBook){        
        return this.comicBookRepository.save(comicBook);
    }
	
	 @GetMapping("/{id}") 
	 public ComicBook getComicBook(@PathVariable int id){
	        // If the record exists by id return it, otherwise throw an exception
	        return this.comicBookRepository.findById(id).orElseThrow(() -> 
	                new ResourceNotFoundException("ComicBook", id)
	        );
	    }
	 
	 @GetMapping() 
	    public List<ComicBook> getComicBooks(){
	        return this.comicBookRepository.findAll();
	    }
	 
	 @PutMapping() 
	    public ComicBook updateComicBook(@Valid @RequestBody ComicBook comicBook){
	        // Finds comicBook by id, maps it's content, updates new values and save. Throws an exception if not found.
	        return this.comicBookRepository.findById(comicBook.getId()).map((toUpdate) -> {
	            toUpdate.setTitle(comicBook.getTitle());
	            toUpdate.setWriter(comicBook.getWriter());
	            toUpdate.setPublisher(comicBook.getPublisher());
	            toUpdate.setGenre(comicBook.getGenre());
	            return this.comicBookRepository.save(toUpdate);
	        }).orElseThrow(() -> new ResourceNotFoundException("ComicBook", comicBook.getId()));
	    }
	    
	    @DeleteMapping("/{id}") 
	    public ResponseEntity<?> deleteComicBook(@PathVariable int id){
	        // If id exists, delete the record and return a response message, otherwise throws exception
	        return this.comicBookRepository.findById(id).map((toDelete) -> {
	            this.comicBookRepository.delete(toDelete);
	            return ResponseEntity.ok("ComicBook id " + id + " deleted");
	        }).orElseThrow(() -> new ResourceNotFoundException("ComicBook", id));
	    }
	    
	    @GetMapping("/{id}/users")
	    public Set<User> getLecturers(@PathVariable int id){
	        // Finds comic by id and returns it's recorded users, otherwise throws exception
	        return this.comicBookRepository.findById(id).map((comicBook) -> {
	            return comicBook.getUsers();
	        }).orElseThrow(() -> new ResourceNotFoundException("ComicBook", id));
	    }
}
