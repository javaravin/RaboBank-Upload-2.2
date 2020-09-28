package com.rabobank.model;

import lombok.Data;

@Data
public class FileData {

    /**
     * End user first name
     */
    private String firstName;

    /**
     * End user surName
     */
    private String surName;

    /**
     * It represents the user specific issue count
     */
    private int issueCount;

    /**
     * End user DOB
     */
    private String dateOfBirth;
}
