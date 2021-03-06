package com.excilys.computer_database.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computer_database.controller.utils.PageConstructor;
import com.excilys.computer_database.dto.CompanyDTO;
import com.excilys.computer_database.dto.ComputerDTO;
import com.excilys.computer_database.dto.PageDTO;
import com.excilys.computer_database.dto.PageParams;
import com.excilys.computer_database.mapper.CompanyMapper;
import com.excilys.computer_database.mapper.ComputerMapper;
import com.excilys.computer_database.mapper.PageMapper;
import com.excilys.computer_database.services.CompanyService;
import com.excilys.computer_database.services.ComputerService;
import com.excilys.computer_database.validator.dto.PageDTOValidator;

/**
 * Controller in charge of all computer related actions
 * @author rlarroque
 */
@Controller
public class ComputerController extends ApplicationController{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
    
    @Autowired
    CompanyMapper companyMapper;
    
    @Autowired
    ComputerMapper computerMapper;
    
    @Autowired 
    PageMapper pageMapper;

    @Autowired
    ComputerService computerService;

    @Autowired
    CompanyService companyService;

    @Autowired
    PageConstructor pageConstructor;
    
    @RequestMapping(method = RequestMethod.GET, value = DASHBOARD)
    public String getDashboard(@ModelAttribute("params") PageParams params, ModelMap map){

        LOGGER.info("[GET] Dashboard");

        PageDTO constructedPage = pageConstructor.construct(pageMapper.toDTO(params));
        PageDTOValidator.validate(constructedPage);
    
        map.addAttribute("page", constructedPage);
        
        return JSP_DASHBOARD;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = COMPUTER + ADD)
    public String getAddComputer(ModelMap map){
        
        LOGGER.info("[GET] computer add");
        
        List<CompanyDTO> listCompanies = companyMapper.toDTO(companyService.getAll());
        map.addAttribute("companies", listCompanies);

        return JSP_ADD;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + ADD)
    public String postAddComputer(@ModelAttribute("computerToAdd") @Valid ComputerDTO computerToAdd, BindingResult errors, Model model) {
        
        LOGGER.info("[POST] computer add: " + computerToAdd);
        
        if(errors.hasErrors()) {
            LOGGER.warn("Input has some errors");
            
            return JSP_ADD;
        } else {
            computerService.create(computerMapper.toComputer(computerToAdd));
            
            return REDIRECT + JSP_DASHBOARD;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = COMPUTER + EDIT)
    protected String getEditComputer(ModelMap map, HttpServletRequest request) {
        
        LOGGER.info("[GET] computer edit");

        List<CompanyDTO> listCompanies = companyMapper.toDTO(companyService.getAll());
        ComputerDTO dto = computerMapper.toDTO(computerService.get(Integer.parseInt(request.getParameter("computer"))));

        map.addAttribute("computer", dto);
        map.addAttribute("companies", listCompanies);
        
        return JSP_EDIT;
    }

    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + EDIT)
    protected String postEditComputer(@Valid @ModelAttribute("computerToEdit") ComputerDTO dto, BindingResult errors, Model model) {
        
        LOGGER.info("[POST] computer edit: " + dto);
        
        if(errors.hasErrors()) {
            LOGGER.warn("Input has some errors");
            
            return JSP_EDIT;
        } else {
            computerService.update(computerMapper.toComputer(dto));
            
            return REDIRECT + JSP_DASHBOARD;
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = COMPUTER + DELETE)
    protected String postDeleteComputer(HttpServletRequest request) {
        
        LOGGER.info("[POST] computer delete");

        String selection = request.getParameter("selection");
        selection = selection.replace("on,","");
        List<String> ids = Arrays.asList(selection.split("\\s*,\\s*"));

        if(ids != null) {

            for (String id : ids) {
                if(id != null && !"".equals(id)) {
                    computerService.delete(Integer.parseInt(id));
                }
            }
        }

        return REDIRECT + JSP_DASHBOARD;
    }
}
