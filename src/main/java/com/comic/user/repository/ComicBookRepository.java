package com.comic.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comic.user.model.ComicBook;

public interface ComicBookRepository extends JpaRepository<ComicBook, Integer> {

}
