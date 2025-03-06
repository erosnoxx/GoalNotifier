package com.erosnox.seeurun.presentation.middleware;

import com.erosnox.seeurun.application.exceptions.BadRequestException;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.exceptions.UnauthorizedException;
import com.erosnox.seeurun.presentation.schemas.common.ErrorSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerMiddleware {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorSchema> handleConflictException(ConflictException ex) {
        return errorMessage(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorSchema> handleBadRequestException(Exception ex) {
        return errorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorSchema> handleNotFoundException(NotFoundException ex) {
        return errorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorSchema> handleUnauthorizedException(UnauthorizedException ex) {
        return errorMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorSchema> handleInternalServerError(Exception ex) {
        return errorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    private ResponseEntity<ErrorSchema> errorMessage(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorSchema(status.value(), message));
    }
}
