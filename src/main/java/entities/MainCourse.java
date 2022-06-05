package entities;

import java.math.BigDecimal;

public class MainCourse extends Meal {

    public MainCourse(String name, BigDecimal price, Cuisine cuisine) {
        super(name, price, cuisine);
    }
}
