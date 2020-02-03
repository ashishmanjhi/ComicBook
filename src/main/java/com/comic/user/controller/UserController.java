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
import com.comic.user.repository.UserRepository;

/**
 * @author Ashish.manjhi
 * 
 * This {@link UserController} class is used to get User details.
 *
 */
@RestController
@RequestMapping("api/user")
public class UserController {

	/*
	 *  User Repository
	 */
	@Autowired
	private UserRepository userRepository;

	/*
	 *  Comic Book Repository
	 */
	@Autowired
	private ComicBookRepository comicBookRepository;


	/**
	 * PostMapping creates a new User in the database.
	 * 
	 * @param User details
	 * @return User
	 */
	@PostMapping()
	public  ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new  ResponseEntity<User>(userRepository.save(user),HttpStatus.CREATED);
	}

	/**
	 * GetMapping  provides the list of all the users in the database.
	 * 
	 * @return List of User details
	 */
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userRepository.findAll(),HttpStatus.OK);
	}

	/**
	 * GetMapping provides detail of an user with a particular id in the
	 * database.
	 * 
	 * @param User id
	 * @return User details
	 */
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User",id)
				);
	}

	/**
	 * PutMapping updates the details of an user with a particular id in
	 * the database.
	 * 
	 * @param user details
	 * @return updated user
	 */
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable(value = "id") Integer id,@Valid @RequestBody User user) {
		// Finds user by id, maps it's content, updates new values and save. Throws an exception if not found.
		return  this.userRepository.findById(id).map(toUpdate -> {
			toUpdate.setName(user.getName());
			toUpdate.setAge(user.getAge());
			toUpdate.setSex(user.getSex());        
			return userRepository.save(toUpdate);
		}).orElseThrow(() -> new ResourceNotFoundException("User", user.getId()));
	}

	/**
	 * DeleteMapping deletes a user with a particular id in the
	 * database.
	 * 
	 * @param User id
	 * @return Updated List of User.
	 */
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteUserById(@PathVariable int id){

		return this.userRepository.findById(id).map((toDelete) -> {
			this.userRepository.delete(toDelete);
			return ResponseEntity.ok("User id " + id + " deleted");
		}).orElseThrow(() -> new ResourceNotFoundException("User", id));
	}

	/**
	 * GetMapping finds all the comic books under a user by using user id.
	 * @param userId
	 * @return User
	 */
	@GetMapping("/{userId}/comicbooks")
	public Set<ComicBook> getComicBooksByUserId(@PathVariable int userId){
		// Finds User by id and returns it's recorded comicbooks, otherwise throws exception 
		return this.userRepository.findById(userId).map((user) -> {
			return user.getComicBook();
		}).orElseThrow(() -> new ResourceNotFoundException("User", userId));
	}

	/**
	 * PostMapping add comic book into the user library.
	 * @param User id
	 * @param comicBookId
	 * @return User
	 */
	@PostMapping("/{userId}/comicbook/{comicBookId}")
	public Set<ComicBook> addComicBookToUser(@PathVariable int userId, @PathVariable int comicBookId){
		// Finds a persisted comic
		ComicBook comicBook = this.comicBookRepository.findById(comicBookId).orElseThrow(
				() -> new ResourceNotFoundException("ComicBook", comicBookId)
				);

		// Finds a user and adds the given comic to the User's set.
		return this.userRepository.findById(userId).map((user) -> {
			user.getComicBook().add(comicBook);
			return this.userRepository.save(user).getComicBook(); 
		}).orElseThrow(() -> new ResourceNotFoundException("User", userId));
	}


	/**
	 * GetMapping finds all the user who read a particular genre of comic book.
	 * 
	 * @param genre
	 * @return
	 */
	@GetMapping("/genre/{genre}")
	public  ResponseEntity<List<User>> getUsersByComicBooksGenre(@PathVariable("genre") String genre){
		
		 List<User> user = userRepository.findByComicBooksGenre(genre);
		 if(user.isEmpty())
			 throw new ResourceNotFoundException("User", genre);
		 else
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
		}

}
