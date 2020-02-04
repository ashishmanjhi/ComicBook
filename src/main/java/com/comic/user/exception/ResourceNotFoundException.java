package com.comic.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/**
 * @author Ashish
 * 
 * This {@link ResourceNotFoundException} class is used for handling exceptions.
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5846657019425937407L;

	/**
	 * Default Constructor 
	 */
	public ResourceNotFoundException() { }

    /**
     * Parameterized Constructor
     * @param entity (ComicBook or User)
     * @param id (ComicBook or User)
     */
    public ResourceNotFoundException(String entity, int id) {
        super(entity + " id " + id + " not found");
    }
    
    /**
     * Parameterized Constructor
     * @param entity User
     * @param comic book genre 
     */
    public ResourceNotFoundException(String entity, String genre) {
        super(entity + " with genre " + genre + " not found");
    }
    
    /**
     * Parameterized Constructor
     * @param entity User
     * @param comic book genre 
     * @param user age
     */
    public ResourceNotFoundException(String entity, String genre,int age) {
        super(entity + " with genre " + genre + " and user age " + age + " not found");
    }
    
    
}