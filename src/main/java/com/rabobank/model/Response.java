/**
 *
 */
package com.rabobank.model;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Response class represents the data returned by all response operations.
 *
 * @author Ravi Naganaboyina
 *
 */
@Component
@Data
public class Response {


    private String message;

    private String code;

}
