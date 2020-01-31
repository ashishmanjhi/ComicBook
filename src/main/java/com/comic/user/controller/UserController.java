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
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComicBookRepository comicBookRepository;

	@PostMapping()
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping()
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User",id)
				);
	}
	
	@PutMapping()
	public User updateUser(@Valid @RequestBody User user) {
		
		return  this.userRepository.findById(user.getId()).map(toUpdate -> {
            toUpdate.setName(user.getName());
            toUpdate.setAge(user.getAge());
            toUpdate.setSex(user.getSex());        
            return userRepository.save(toUpdate);
        }).orElseThrow(() -> new ResourceNotFoundException("User", user.getId()));
	}

	@DeleteMapping("/{id}") // Finds lecturer by id
    public ResponseEntity<?> deleteUser(@PathVariable int id){
      
		return this.userRepository.findById(id).map((toDelete) -> {
            this.userRepository.delete(toDelete);
            return ResponseEntity.ok("User id " + id + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
    }
	
	@GetMapping("/{userId}/comicbooks")
    public Set<ComicBook> getComicBooks(@PathVariable int userId){
        // Finds User by id and returns it's recorded comicbooks, otherwise throws exception 
        return this.userRepository.findById(userId).map((user) -> {
            return user.getComicBook();
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }
	
	 @PostMapping("/{id}/comicbooks/{comicBookId}")
	    public Set<ComicBook> addComicBook(@PathVariable int id, @PathVariable int comicBookId){
	        // Finds a persisted comic
	        ComicBook comicBook = this.comicBookRepository.findById(comicBookId).orElseThrow(
	                () -> new ResourceNotFoundException("ComicBook", comicBookId)
	        );
	        
	        // Finds a user and adds the given comic to the User's set.
	        return this.userRepository.findById(id).map((user) -> {
	           user.getComicBook().add(comicBook);
	            return this.userRepository.save(user).getComicBook(); 
	        }).orElseThrow(() -> new ResourceNotFoundException("User", id));
	    }
	 
	 
	 
	 //finding User by a particular comic genre.
	 @GetMapping("/genre/{genre}")
	 public List<User> getUsersByComicBooksGenre(@PathVariable("genre") String genre){
		 
		return userRepository.findByComicBooksGenre(genre);
		 
	 }

}
