package com.rabobank.service;

import com.rabobank.component.FileProcess;
import com.rabobank.component.Validation;
import com.rabobank.exception.FileProcessException;
import com.rabobank.exception.RaboBankException;
import com.rabobank.model.FileData;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private Validation validation;
    private FileProcess fileProcess;
    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

    private static final String LOG_KEY = "FileService:";
    private static final String FILE_FORMAT = "003-file_format_error";

    @Autowired
    public FileServiceImpl(Validation validation, FileProcess fileProcess) {
        this.validation = validation;
        this.fileProcess = fileProcess;
    }


    @Override
    public void fileUploadProcess(MultipartFile file) {

        //validation of input request
        validation.validateFileIsEmpty(file);
        String fileName = file.getOriginalFilename();
        List<FileData> fileData = null;
        try {
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // skip the header of the csv
            fileData = br.lines().skip(1)
                    .map(this::setFileData).collect(Collectors.toList());
            br.close();


        } catch (Exception e) {
            logger.error(LOG_KEY + "Parsing has been failed for this file:" + fileName);
            throw new FileProcessException(FILE_FORMAT);
        }
        if (fileData != null) {
            validation.validationFileData(fileData);
        }
        fileProcess.setFileIntoMemory(fileData);
    }

    @Override
    public List<FileData> getFileDataByIssueCount(int issueCount) {
        return fileProcess.getFileData(issueCount);
    }

    @Override
    public List<FileData> getFileData() {
        return fileProcess.getFileData();
    }

    private FileData setFileData(String data) {

        String[] file = data.split(",");
        if (file.length != 4) {
            logger.error(LOG_KEY + " Invalid file format");
            throw new RaboBankException(FILE_FORMAT);
        }
        FileData fileData = new FileData();
        String firstName = StringUtils.isEmpty(file[0]) ? "" : file[0].replaceAll("\"", "");
        fileData.setFirstName(firstName);
        String surName = StringUtils.isEmpty(file[1]) ? "" : file[1].replaceAll("\"", "");
        fileData.setSurName(surName);
        fileData.setIssueCount(StringUtils.isEmpty(file[2]) ? 0 : Integer.parseInt(file[2]));
        String dob = StringUtils.isEmpty(file[3]) ? "" : file[3].replaceAll("\"", "");
        fileData.setDateOfBirth(dob);
        return fileData;
    }
}
