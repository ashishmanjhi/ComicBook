package com.comic.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comic.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByComicBooksGenre(String genre);




}
