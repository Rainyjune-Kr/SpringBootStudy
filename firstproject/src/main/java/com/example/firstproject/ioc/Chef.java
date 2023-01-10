package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    private IngredientFactory ingredientFactory;
    public String cook(String menu) {
        String resultValue = null;

        //Pork pork = new Pork("한돈 등심");
        //resultValue = pork.getName() + "으로 만든 " + menu;

        Beef beef = new Beef("한우 꽃등심");
        resultValue = beef.getName() + "으로 만든 " + menu;

        return resultValue;
    }

    public String cookEvo(String menu) {
        String resultValue = null;

        Ingredient ingredient = ingredientFactory.get(menu);
        resultValue = ingredient.getName() + "으로 만든 " + menu;

        return resultValue;
    }

    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }


}
