package com.example.firstproject.ioc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Beef {
    private String name;

    public Beef(String name)
    {
        this.name = name;
    }
}
