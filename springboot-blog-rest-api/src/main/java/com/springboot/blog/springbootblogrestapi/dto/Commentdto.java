package com.springboot.blog.springbootblogrestapi.dto;

import lombok.Data;

@Data
public class Commentdto {
    private Long id;
    private String name;
    private String email;
    private String body;

}
