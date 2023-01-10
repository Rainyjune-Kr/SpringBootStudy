package com.example.firstproject.ioc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }
}
