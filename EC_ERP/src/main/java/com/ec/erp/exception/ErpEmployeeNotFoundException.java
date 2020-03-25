package com.ec.erp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ErpEmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErpEmployeeNotFoundException() {
		super("ErpEmplyee does not exist");
	}
}
