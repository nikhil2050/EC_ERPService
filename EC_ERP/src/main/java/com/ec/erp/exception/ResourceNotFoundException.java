package com.ec.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * /* resource not found exception class 
 * 
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4132291896506210756L;
	/**
	 * 
	 * @param resourceName
	 * @param fieldName
	 * @param fieldValue
	 */
	public ResourceNotFoundException(final String resourceName, final String fieldName,final Object fieldValue) // exception method
	{
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));

	}
	public ResourceNotFoundException(final String message)  
	{
		super(message);
	}
	
}