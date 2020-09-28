package com.rabobank.exception;

/**
 * RaboBankException manages  the error messages.
 *
 * @author Ravi Naganaboyina
 */
public class RaboBankException extends RuntimeException {

    private final String messageKey;

    public RaboBankException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

}
