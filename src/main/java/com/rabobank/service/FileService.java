package com.rabobank.service;

import com.rabobank.model.FileData;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileService will read the and store the data into @FileData
 */
public interface FileService {

    /**
     * this method will read the data from input and will populate into the FileData object for storing DB
     *
     * @param multipartFile it represents input request
     */

    public void fileUploadProcess(MultipartFile multipartFile);

    /**
     * This method will get the FileData information  by using input request
     *
     * @param issueCount it represents the issue records
     * @return FileData object
     */
    public List<FileData> getFileDataByIssueCount(int issueCount);

    /**
     * this method will return entaire file data
     *
     * @return List of FileData
     */
    public List<FileData> getFileData();
}
