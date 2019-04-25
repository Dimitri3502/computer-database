package com.excilys.cdb.mapper.dto;

import com.excilys.cdb.dto.CreateComputerDTO;
import com.excilys.cdb.dto.CreateComputerDTOUi;
import com.excilys.cdb.exception.MapperException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateComputerDTOUiToCreateComputerDTOMapper implements Mapper<CreateComputerDTOUi, CreateComputerDTO> {
    private static final String NULL = "null";
    private static CreateComputerDTOUiToCreateComputerDTOMapper instance;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CreateComputerDTOUiToCreateComputerDTOMapper() {
    }

    private static LocalDate parseDate(String date) {
        if (date.equals(NULL)) {
            return null;
        }
        return LocalDate.parse(date);
    }

    private static Long parseId(String id) {
        if (id.equals(NULL)) {
            return null;
        } else {
            return Long.valueOf(id);
        }
    }

    public static CreateComputerDTOUiToCreateComputerDTOMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CreateComputerDTOUiToCreateComputerDTOMapper();
        }
        return instance;
    }

    @Override
    public CreateComputerDTO map(CreateComputerDTOUi createComputerDTOUi) {
        try {
            final CreateComputerDTO createComputerDTO = new CreateComputerDTO();
            createComputerDTO.setName(createComputerDTOUi.getName());
            createComputerDTO.setMannufacturerId(parseId(createComputerDTOUi.getMannufacturerId()));
            createComputerDTO.setIntroduced(parseDate(createComputerDTOUi.getIntroduced()));
            createComputerDTO.setDiscontinued(parseDate(createComputerDTOUi.getDiscontinued()));
            return createComputerDTO;
        } catch (NumberFormatException | DateTimeParseException e) {
            logger.warn("map(" + createComputerDTOUi + ")", e);
            throw new MapperException(e);
        }
    }

}
