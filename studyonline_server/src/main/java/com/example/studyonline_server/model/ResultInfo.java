package com.example.studyonline_server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResultInfo<T>{

    private String msg;
    private T data;
    private boolean success;
}
