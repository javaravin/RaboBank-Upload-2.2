package com.rabobank.controller;

import com.rabobank.model.FileData;
import com.rabobank.service.FileService;
import com.rabobank.util.ResponseBuilder;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileProcessController {

    private static final Logger logger = LogManager.getLogger(FileProcessController.class);
    private static final String LOG_KEY = "FileProcessAdapter:";
    private static final String SUCCESS = "file_success";

    private static final String WELCOME = "welcome";
    private static final String WELCOME_PATH = "/";

    private static final String UPLOAD = "upload";
    private static final String UPLOAD_PATH = "/upload";

    private static final String FILE_STATUS = "status";
    private static final String FILE_UPLOAD_PATH = "/fileUpload";
    private static final String MESSAGE = "message";

    private static final String SEARCH = "search";
    private static final String SEARCH_PATH = "/search";

    private FileService fileService;
    private ResponseBuilder responseBuilder;


    @Autowired
    public FileProcessController(FileService fileService, ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
        this.fileService = fileService;
    }

    /**
     * This method will land welcome page to user
     *
     * @return welcome view
     */
    @GetMapping(WELCOME_PATH)
    public ModelAndView welcome() {
        logger.info(LOG_KEY + "File process application has started");
        return new ModelAndView(WELCOME);
    }

    /**
     * This method will land the upload page to user
     *
     * @return upload page
     */
    @GetMapping(UPLOAD)
    public ModelAndView showUpload() {
        logger.info(LOG_KEY + "File process upload page has opened");

        return new ModelAndView(UPLOAD_PATH);
    }

    /**
     * This method will handle the file submission process and will return success if valid file received from input
     *
     * @return will return status page and as well message to user either success or fail
     */
    @PostMapping(FILE_UPLOAD_PATH)
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file) {
        logger.info(LOG_KEY + "File process has stated");

        fileService.fileUploadProcess(file);
        logger.info(LOG_KEY + "File process has end");

        return new ModelAndView(FILE_STATUS, MESSAGE,
                responseBuilder.buildSuccessResponse(SUCCESS));
    }

    /**
     * This method will land the search page to user
     *
     * @return a search page
     */
    @GetMapping(SEARCH_PATH)
    public ModelAndView getFileData() {
        logger.info(LOG_KEY + "Search process has stated");
        return new ModelAndView(SEARCH);
    }

    /**
     * This method will return the results based on user given input.
     *
     * @param issueCount issues count which has in file
     * @return List of Issues to particular user
     */
    @GetMapping("/search/{issueCount}")
    public ModelAndView getFileData(@PathVariable("issueCount") int issueCount) {
        List<FileData> fileData = fileService.getFileDataByIssueCount(issueCount);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("response", "ok");
        modelAndView.addObject("fileData", fileData);
        modelAndView.setViewName(SEARCH);
        return modelAndView;
    }
}
