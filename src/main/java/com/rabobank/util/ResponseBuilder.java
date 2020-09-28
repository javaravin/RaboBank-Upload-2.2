package com.rabobank.util;

import com.rabobank.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * ResponseBuilder class builds the response returned by the Configuration Service.
 *
 * @author Ravi Naganaboyina
 */
@Component
public class ResponseBuilder {

    // the locale handler
    private LocaleHandler localeHandler;

    private Response response;

    private static final String DELI_METER = "-";

    @Value("${rabobank.globalErrorCode:001}")
    private String globalErrorCode;

    @Value("${rabobank.globalSuccess:000}")
    private String globalSuccess;

    public ResponseBuilder(Response response, LocaleHandler localeHandler) {
        this.localeHandler = localeHandler;
        this.response = response;
    }


    /**
     * Returns a Response for a successful response.
     *
     * @param message The message to be sent in the response.
     * @return the ResponseDTO for the success response.
     */
    public Response buildSuccessResponse(String message) {
        String code = globalSuccess;
        if (StringUtils.isEmpty(message) && !message.contains(DELI_METER)) {
            code = message.split(DELI_METER)[0];
            message = message.split(DELI_METER)[1];
        }
        response.setMessage(localeHandler.getLocalizedMessage(message));
        response.setCode(code);
        return response;
    }


    /**
     * Returns a ResponseDTO for a failure response.
     *
     * @param message The message to be sent in the response.
     * @return the ResponseDTO for the failure response.
     */
    public Response buildFailureResponse(String message) {
        if (!StringUtils.isEmpty(message)) {
            String code = globalErrorCode;
            if (message.contains(DELI_METER)) {
                code = message.split(DELI_METER)[0];
                message = message.split(DELI_METER)[1];
            }
            response.setMessage(localeHandler.getLocalizedMessage(message));
            response.setCode(code);
        }

        return response;
    }

}
