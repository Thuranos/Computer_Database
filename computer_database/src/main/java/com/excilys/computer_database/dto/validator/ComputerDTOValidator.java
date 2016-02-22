package com.excilys.computer_database.dto.validator;

import java.time.LocalDate;
import java.util.List;

import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.exception.IntegrityException;

/**
 * Interface with static methods used to validate the integrity of a computerDTO
 * @author rlarroque
 */
public interface ComputerDTOValidator {

	/**
	 * Validate the integrity of a computerDTO
	 * @param dto computerDTO to validate
	 * @throws IntegrityException thrown if the integrity is not respected
	 */
	public static void validate(ComputerDTO dto) throws IntegrityException {

		if (dto == null) {
			throw new IntegrityException("The computer is null.");
		}

		if (dto.getName() == null || "".equals(dto.getName())) {
			throw new IntegrityException("The computer's name is null.");
		}

		if (dto.getDiscontinuedDate() != null && dto.getIntroducedDate() == null) {
			throw new IntegrityException("Discontinued date cannot exist if there is no introducing date for computer " + dto.getName());
		}
		
		//check if discontinued date isn't before introduced one
		if (dto.getDiscontinuedDate() != null && dto.getIntroducedDate() != null
				&& !"".equals(dto.getDiscontinuedDate()) && !"".equals(dto.getIntroducedDate())
				&& LocalDate.parse(dto.getDiscontinuedDate()).isBefore(LocalDate.parse(dto.getIntroducedDate()))) {

			throw new IntegrityException("Discontinued date cannot be earlier than introducing datefor computer " + dto.getName());
		}
	}
	
	/**
	 * Validate the integrity of a list of computerDTOs
	 * @param list list to validate
	 * @throws IntegrityException thrown if the integrity is not respected
	 */
	public static void validate(List<ComputerDTO> list) throws IntegrityException {
		for (ComputerDTO dto : list) {
			validate(dto);
		}
	}
}
