package com.rabobank.exception;

/**
 * @FileProcessException is extends the @RaboBankException for handling errors.
 */
public class FileProcessException extends RaboBankException {

    private final String messageKey;

    public FileProcessException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

}
