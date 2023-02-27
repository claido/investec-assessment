package com.investec.assessment.exception;

import java.util.Map;

public class ValidationFailedException extends RuntimeException {

    private Map<String, String> exceptionsMap;

    public ValidationFailedException(final Map<String, String> exceptionMap, final String errorMessage) {
        super(errorMessage);
        this.exceptionsMap = exceptionMap;
    }

    public Map<String, String> getExceptionsMap() {
        return exceptionsMap;
    }
}
