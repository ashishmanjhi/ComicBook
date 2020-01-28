package com.comic.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comic.user.dao.ComicBookRepository;
import com.comic.user.dao.UserRepository;
import com.comic.user.model.ComicBook;
import com.comic.user.model.User;

@RestController
public class Controller {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ComicBookRepository comicbookRepository;
	
	
	@GetMapping("/getComic")
	public List<ComicBook> getAllComic(){
		return comicbookRepository.findAll();
		
	}
	
	@GetMapping("/getUser")
	public List<User> getAllUser(){
		return userRepository.findAll();
		
	}
	
	@PostMapping("/comic")
	public ComicBook createComicBook(@Valid @RequestBody ComicBook comicBook) {
		return comicbookRepository.save(comicBook);
	}
	
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
}
