package entities;

import java.math.BigDecimal;

public abstract class Meal extends Food {

    private Cuisine cuisine;

    public Meal(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price);
        this.cuisine = cuisine;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
