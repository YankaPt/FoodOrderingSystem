package entities;

import java.math.BigDecimal;

public class Dessert extends Meal {

    public Dessert(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price, cuisine);
    }
}
