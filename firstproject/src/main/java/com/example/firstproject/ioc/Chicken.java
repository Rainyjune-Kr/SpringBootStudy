package com.example.firstproject.ioc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chicken extends Ingredient {
    public Chicken(String name) {
        super(name);
    }
}
