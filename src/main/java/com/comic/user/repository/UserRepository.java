package com.comic.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comic.user.model.User;

/**
 * @author Ashish.manjhi
 * 
 *
 *This {@link UserRepository} interface is the Repository of {@link UserController} class.
 */
public interface UserRepository extends JpaRepository<User, Integer> { 

	/**These are some custom made RESTAPIs method used for performing 
	 * different search operation on book database.*/
	
	/*
	 * Finds all the Users who read a particular genre of comic books.
	 */
	List<User> findByComicBooksGenre(String genre);

	/*
	 * Finds all the Users who read a particular genre of comic books and of a selected age.
	 */
	List<User> findByComicBooksGenreAndAge(String genre, int age);


}
