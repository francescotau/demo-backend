package org.example.exception;

import io.smallrye.graphql.api.ErrorCode;


@ErrorCode("graph-ql-exception")
public class CustomGraphQLException extends RuntimeException {

    public CustomGraphQLException(String message) {
        super(message);
    }
}