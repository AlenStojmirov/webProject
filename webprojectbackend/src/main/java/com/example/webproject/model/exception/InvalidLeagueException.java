package com.example.webproject.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidLeagueException extends RuntimeException {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class InvalidClubException extends RuntimeException{
    }
}
