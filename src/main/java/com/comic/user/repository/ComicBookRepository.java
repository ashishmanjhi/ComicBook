package com.comic.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comic.user.model.ComicBook;

/**
 * @author Ashish.manjhi
 *
 *
 *This {@link ComicBookRepository} interface is the Repository of {@link ComicBookController} class.
 */
public interface ComicBookRepository extends JpaRepository<ComicBook, Integer> {

}
