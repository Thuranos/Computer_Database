/**
 * 
 */
package com.excilys.computer_database.webapp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.service.CompanyService;
import com.excilys.computer_database.service.ComputerService;
import com.excilys.computer_database.webapp.dto.CompanyDTO;
import com.excilys.computer_database.webapp.dto.ComputerDTO;

@Controller
public class ComputerController extends ApplicationController{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    ComputerService computerService;

    @Autowired
    CompanyService companyService;
    
    @RequestMapping(method = RequestMethod.GET, value = COMPUTER + ADD)
    public String getAddComputer(ModelMap map){
        
        LOGGER.info("GET computer add");
        
        List<CompanyDTO> listCompanies = CompanyMapper.toDTO(companyService.getAll());
        
        map.addAttribute("companies", listCompanies);
        return JSP_ADD;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + ADD)
    public String postAddComputer(ModelMap map, @Valid @ModelAttribute("computerToAdd") ComputerDTO dto, BindingResult errors) {
        
        LOGGER.info("POST computer add: " + dto);
        
        if(errors.hasErrors()) {
            LOGGER.warn("Input has some errors");
            
            return JSP_ADD;
        } else {
            computerService.create(ComputerMapper.toComputer(dto));
            
            return REDIRECT + JSP_DASHBOARD;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = COMPUTER + EDIT)
    protected String getEditComputer(ModelMap map, HttpServletRequest request) {
        
        LOGGER.info("GET computer edit");

        List<CompanyDTO> listCompanies = CompanyMapper.toDTO(companyService.getAll());
        ComputerDTO dto = ComputerMapper.toDTO(computerService.get(Integer.parseInt(request.getParameter("computer"))));

        map.addAttribute("computer", dto);
        map.addAttribute("companies", listCompanies);
        
        return JSP_EDIT;
    }

    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + EDIT)
    protected String postEditComputer(ModelMap map, @Valid @ModelAttribute("computerToEdit") ComputerDTO dto, BindingResult errors) {
        
        LOGGER.info("POST computer edit: " + dto);
        
        if(errors.hasErrors()) {
            LOGGER.warn("Input has some errors");
            
            return JSP_EDIT;
        } else {
            computerService.update(ComputerMapper.toComputer(dto));
            
            return REDIRECT + JSP_DASHBOARD;
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + DELETE)
    protected String postDeleteComputer(ModelMap map, HttpServletRequest request) {
        
        LOGGER.info("POST computer delete");
        
        List<String> ids = Arrays.asList(request.getParameter("selection").split("\\s*,\\s*"));

        for (String id : ids) {
            computerService.delete(Integer.parseInt(id));
        }
        
        return REDIRECT + JSP_DASHBOARD;
    }
}