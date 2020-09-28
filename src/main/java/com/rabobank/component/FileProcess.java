package com.rabobank.component;

import com.rabobank.model.FileData;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * @author Ravi Naganaboyina
 * @FileProcess class will have reusable method for handling business requirement and also will help to switch over
 * modules.
 */
@Component
public class FileProcess {

    private List<FileData> internalMemoryFile;

    public FileProcess(List<FileData> internalMemoryFile) {
        this.internalMemoryFile = internalMemoryFile;
    }

    /**
     * This method will set the fileData into Repository.
     *
     * @param listFileData it represents the fileData of user
     */
    public void setFileIntoMemory(List<FileData> listFileData) {
        //Currently i putted into internal list instead of DB.
        internalMemoryFile.addAll(listFileData);
    }

    /**
     * This method will used retrieve the issue count form Repository
     *
     * @param issueCount it represents the rabobank file user issue count
     * @return List of @FileData which contains user information by given issueCount
     */
    public List<FileData> getFileData(int issueCount) {

        return internalMemoryFile.stream()
                .filter(filData -> filData.getIssueCount() == issueCount).collect(Collectors.toList());

    }

    /**
     * this method will get total records of user information by List
     *
     * @return List of @FileData which contains user information
     */
    public List<FileData> getFileData() {
        return internalMemoryFile;

    }
}
