package com.springboot.blog.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Long fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s not found with %s:'%s'",resourceName,fieldName,fieldValue));
        this.resourceName =resourceName;
        this.fieldName =fieldName;
        this.fieldValue =fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Long getFieldValue() {
        return fieldValue;
    }
}
