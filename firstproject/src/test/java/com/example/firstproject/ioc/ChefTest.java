package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ScheduledFuture;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {
    @Autowired
    IngredientFactory factory;

    @Autowired
    Chef chefAuto;

    @Test
    void Cook_Dongas() {
//        Chef chef = new Chef();
//        String menu = "돈까스";
//
//        String food = chef.cook(menu);
//
//        String expected = "한돈 등심으로 만든 돈까스";
//
//        assertEquals(expected, food);
//        System.out.println(food);
    }

    @Test
    void Cook_Dongas_evo() {
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);

        String food = chef.cookEvo("돈까스");

        String expected = "한돈 등심으로 만든 돈까스";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void Cook_Steak() {
//        Chef chef = new Chef();
//        String menu = "스테이크";
//
//        String food = chef.cook(menu);
//
//        String expected = "한우 꽃등심으로 만든 스테이크";
//
//        assertEquals(expected, food);
//        System.out.println(food);
    }

    @Test
    void Cook_Steak_evo() {
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);

        String food = chef.cookEvo("스테이크");

        String expected = "한우 꽃등심으로 만든 스테이크";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void Cook_Crispy_Chicken() {
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);

        String food = chef.cookEvo("크리스피 치킨");

        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void Cook_Steak_Ioc() {
        Chef chef = new Chef(factory);

        String food = chef.cookEvo("스테이크");

        String expected = "한우 꽃등심으로 만든 스테이크";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void Cook_Crispy_Chicken_Ioc() {
        String food = chefAuto.cookEvo("크리스피 치킨");

        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        assertEquals(expected, food);
        System.out.println(food);
    }
}