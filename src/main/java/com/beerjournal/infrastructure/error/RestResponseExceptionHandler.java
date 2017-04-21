package com.beerjournal.infrastructure.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BeerJournalException.class})
    public ResponseEntity<ErrorInfo> handleClientErrorException(BeerJournalException bjException) {
        ErrorInfo errorInfo = bjException.getInfo();
        return new ResponseEntity<>(errorInfo, errorInfo.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleOtherException(Exception ex) {
        if (ex instanceof BeerJournalException) {
            return handleClientErrorException((BeerJournalException) ex);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}