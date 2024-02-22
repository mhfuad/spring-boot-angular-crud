package com.fuad.springCrud.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private int status;
    private String error;

}
