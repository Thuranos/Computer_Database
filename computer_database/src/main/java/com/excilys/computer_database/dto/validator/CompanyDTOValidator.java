package com.excilys.computer_database.dto.validator;

import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.exception.IntegrityException;

public interface CompanyDTOValidator {

	public static void validate(CompanyDTO dto) throws IntegrityException{
		
		if(dto == null){
			throw new IntegrityException("The company is null.");
		}
		
		if(dto.getId() < 1){
			throw new IntegrityException("The company's id is not valid.");
		}
	}
}
