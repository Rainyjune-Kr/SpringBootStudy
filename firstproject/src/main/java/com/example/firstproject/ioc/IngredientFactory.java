package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고 IoC Container에 등록함.
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu)
        {
            case "돈까스":
                return new PorkEvo("한돈 등심");
            case "스테이크":
                return new BeefEvo("한우 꽃등심");
            case "크리스피 치킨":
                return new Chicken("국내산 10호 닭");
            default:
                return null;
        }
    }
}
