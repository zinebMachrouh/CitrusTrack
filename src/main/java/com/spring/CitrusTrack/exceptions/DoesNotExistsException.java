package com.spring.CitrusTrack.exceptions;

// Exception to be thrown when a resource does not exist

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DoesNotExistsException extends RuntimeException {
    public DoesNotExistsException(String message) {
        super(message);
    }
}
