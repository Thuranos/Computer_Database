package com.excilys.computer_database.dto.validator;

import com.excilys.computer_database.dto.model.ComputerDTO;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author rlarroque
 */
@ContextConfiguration(locations = { "classpath:/test-binding-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerDtoValidator {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeClass
    public static void executeBeforeAllTest() {

    }

    @Ignore
    @Test
    public void validateComputerDto() {
        ComputerDTO dto = new ComputerDTO(1, "Dummy Computer", "01/01/2012", "06/04/2013", null, 0);
        Set<ConstraintViolation<ComputerDTO>> violations = validator.validate(dto);
        assertEquals(violations.size(), 0);
    }
}
