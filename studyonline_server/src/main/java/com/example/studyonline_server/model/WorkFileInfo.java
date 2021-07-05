package com.example.studyonline_server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class WorkFileInfo {

    private int id;
    private String name;
    private String type;
    private String url;
}
