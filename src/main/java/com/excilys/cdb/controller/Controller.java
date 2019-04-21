package com.excilys.cdb.controller;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.dto.CreateComputerDTO;
import com.excilys.cdb.dto.UpdateComputerDTO;
import com.excilys.cdb.exception.ControllerException;
import com.excilys.cdb.exception.ValidatorException;
import com.excilys.cdb.mapper.dto.*;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.validator.CreateComputerValidator;
import com.excilys.cdb.validator.UpdateComputerValidator;
import com.excilys.cdb.validator.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Controller {
    private static Controller instance;
    private final CompanyService companyService = CompanyService.getInstance();
    private final Mapper<Company, CompanyDTO> companyToCompanyDTO = CompanyToCompanyDTOMapper.getInstance();
    private final Mapper<Computer, ComputerDTO> computerToComputerDTO = ComputerToComputerDTOMapper.getInstance();
    private final ComputerService computerService = ComputerService.getInstance();

    private Controller() {
    }

    public static Controller getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Controller();
        }
        return instance;
    }

    public List<CompanyDTO> getCompanies(long offset, long limit) {
        try {
            return companyService.findAll(offset, limit).stream().map(companyToCompanyDTO::map).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public long numberOfCompanies() {
        try {
            return companyService.count();
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public List<ComputerDTO> getComputers(long offset, long limit) {
        try {
            return computerService.findAll(offset, limit).stream().map(computerToComputerDTO::map).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public long numberOfComputers() {
        try {
            return computerService.count();
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public Optional<ComputerDTO> getComputerById(long id) {
        try {
            return computerService.findById(id).map(computerToComputerDTO::map);
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public void deleteComputer(long id) {
        try {
            computerService.delete(id);
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public void createComputer(CreateComputerDTO dto) {
        try {
            final Validator.Result result = CreateComputerValidator.getInstance().check(dto);
            if (result.isValid()) {
                computerService.create(CreateComputerDTOToComputerMapper.getInstance().map(dto));
            } else {
                throw new ValidatorException(result);
            }
        } catch (ValidatorException e) {
            throw new ControllerException(e.getMessage());
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }

    public void updateComputer(UpdateComputerDTO dto) {
        try {
            final Validator.Result result = UpdateComputerValidator.getInstance().check(dto);
            if (result.isValid()) {
                computerService.update(UpdateComputerDTOToComputerMapper.getInstance().map(dto));
            } else {
                throw new ValidatorException(result);
            }
        } catch (ValidatorException e) {
            throw new ControllerException(e.getMessage());
        } catch (RuntimeException e) {
            throw new ControllerException();
        }
    }
}
