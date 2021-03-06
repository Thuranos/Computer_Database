package com.excilys.computer_database.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.dao.impl.CompanyDAOImpl;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.services.impl.CompanyServiceImpl;

@ContextConfiguration("classpath:/test-service-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompanyService {

    @Autowired
    private CompanyServiceImpl companyService;
    
    @Autowired
    private CompanyDAOImpl mockCompanyDao;

    /**
     * Before.
     */
    @Before
    public void executeBeforeTests() {
        Mockito.when(mockCompanyDao.getAll()).thenReturn(new ArrayList<>());
        Mockito.when(mockCompanyDao.get(Matchers.anyInt())).thenReturn(new Company("Dummy Company"));
        Mockito.when(mockCompanyDao.get(Matchers.anyString())).thenReturn(new Company("Dummy Company"));
    }

    /**
     * Test.
     */
    @Test
    public void testGetCompanies() {
        assertEquals(0, companyService.getAll().size());
    }
    
    /**
     * Test.
     */
    @Test
    public void testGetCompanyWithId() {
        Company company = companyService.get(1);
        assertEquals("Dummy Company", company.getName());
    }

    /**
     * Test.
     */
    @Test
    public void testGetCompanyWithName() {
        Company company = companyService.get("Dummy Company");
        assertEquals("Dummy Company", company.getName());
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetCompanyNullName() {
        companyService.get(null);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetCompanyEmptyName() {
        companyService.get("");
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetCompanyInvalidID() {
        companyService.get(-1);
    }
}
