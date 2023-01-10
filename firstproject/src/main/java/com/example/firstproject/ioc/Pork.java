package com.example.firstproject.ioc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pork {
    private String name;

    public Pork(String name)
    {
        this.name = name;
    }
}
