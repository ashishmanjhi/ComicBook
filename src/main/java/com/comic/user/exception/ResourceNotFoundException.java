package com.comic.user.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5846657019425937407L;

	public ResourceNotFoundException() { }

    public ResourceNotFoundException(String entity, int id) {
        super(entity + " id " + id + " not found");
    }
    
}