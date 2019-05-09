package com.excilys.cdb.validator;

import com.excilys.cdb.dto.UpdateComputerDTO;
import com.excilys.cdb.exception.ValidationException;
import com.excilys.cdb.service.ComputerService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class UpdateComputerValidator implements Validator<UpdateComputerDTO> {
    private final ComputerValidatorUtils computerValidatorUtils;

    public UpdateComputerValidator(ComputerValidatorUtils computerValidatorUtils) {
        this.computerValidatorUtils = computerValidatorUtils;
    }

    private void checkId(String id) {
        try {
            final long i = Long.parseLong(id);
            if (!ComputerService.getInstance().exist(i)) {
                throw new ValidationException("id", "L'id n'exist pas.");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("id", "L'id est mal écrit.");
        }
    }

    @Override
    public void check(UpdateComputerDTO toValidate) {
        Objects.requireNonNull(toValidate);
        computerValidatorUtils.checkName(toValidate.getName());
        final LocalDate introduced = computerValidatorUtils.checkIntroduced(toValidate.getIntroduced());
        final LocalDate discontinued = computerValidatorUtils.checkDiscontinued(toValidate.getDiscontinued());
        computerValidatorUtils.checkIntroducedIsBeforeDiscontinued(introduced, discontinued);
        computerValidatorUtils.checkMannufacturerId(toValidate.getMannufacturerId());
        checkId(toValidate.getId());
    }
}
