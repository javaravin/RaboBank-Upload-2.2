package com.rabobank.component;

import com.rabobank.exception.FileProcessException;
import com.rabobank.model.FileData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ravi Naganaboyina
 * @Validation class will handle the validation of input request and business checks
 */

@Component
public class Validation {

    private static final Logger logger = LogManager.getLogger(Validation.class);

    private static final String LOG_KEY = "Validation:";
    private static final String VALIDATION_ERROR = "002-file_empty_error";
    private static final String FILE_FORMAT = "003-file_format_error";

    public void validateFileIsEmpty(MultipartFile file) {

        if (file.isEmpty()) {
            logger.error(LOG_KEY + "File should not be empty");
            throw new FileProcessException(VALIDATION_ERROR);
        }
    }

    /**
     * This method will validate the fileData all fields have in correct values or not. if not will raise the exception
     * otherwise will continue next record validation.
     *
     * @param fileData list of fileData
     */
    public void validationFileData(List<FileData> fileData) {
        fileData.stream().forEach(data -> {

            String message = fileFieldValidation(data);
            String name = data.getFirstName() + data.getSurName();

            if (!StringUtils.isEmpty(message)) {
                String logMessage = LOG_KEY + "FileData has been failed due to " + message + "for " + name;
                logger.error(logMessage);
                throw new FileProcessException(FILE_FORMAT);
            }
        });
    }

    /**
     * this method will validate the fileData all fields have in correct values or not. if not will raise the exception
     * otherwise will continue next record validation.
     *
     * @param fileData user file object
     * @return a String which contains error message.
     */
    private String fileFieldValidation(FileData fileData) {

        String message = "";
        String firstName = fileData.getFirstName();
        if (!StringUtils.isEmpty(firstName) && !Pattern.matches("[a-zA-Z]+", firstName)) {
            message += "FirstName: should not be empty and should  be a alphabet \n";

        }
        String surname = fileData.getSurName();
        if (!StringUtils.isEmpty(surname) && !Pattern.matches("[a-zA-Z]+", surname)) {
            message = message + "SurName: should not be empty and should  be a alphabet \n";
        }

        int count = fileData.getIssueCount();
        if (count <= 0) {
            message = message + "IssueCount: count should be a  <=0 \n";
        }
        String dob = fileData.getDateOfBirth();
        message = message + dateIsValid(dob);

        return message;
    }

    private String dateIsValid(String dob) {
        try {
            LocalDate.parse(dob, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            return "DateOfBirth: Should be a ISO_DATE_TIME format ";
        }
        return "";
    }
}
