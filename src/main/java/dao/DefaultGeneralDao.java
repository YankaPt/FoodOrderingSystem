package dao;

import entities.Cuisine;
import entities.Dessert;
import entities.Drink;
import entities.MainCourse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class DefaultGeneralDao implements GeneralDao {

    private List<MainCourse> mainCourses;
    private List<Dessert> desserts;
    private List<Drink> drinks;

    public DefaultGeneralDao() {
        setUpDemoData();
    }

    private void setUpDemoData() {
        mainCourses = List.of(
                new MainCourse("zurek", BigDecimal.valueOf(6), Cuisine.POLISH),
                new MainCourse("bigos", BigDecimal.valueOf(11.5), Cuisine.POLISH),
                new MainCourse("pierogi", BigDecimal.valueOf(7.2), Cuisine.POLISH),
                new MainCourse("enchiladas", BigDecimal.valueOf(14.8), Cuisine.MEXICAN),
                new MainCourse("tacos", BigDecimal.valueOf(8), Cuisine.MEXICAN),
                new MainCourse("tostadas", BigDecimal.valueOf(10.8), Cuisine.MEXICAN),
                new MainCourse("carbonara", BigDecimal.valueOf(13.6), Cuisine.ITALIAN),
                new MainCourse("risotto", BigDecimal.valueOf(10.9), Cuisine.ITALIAN),
                new MainCourse("lasagna", BigDecimal.valueOf(15.2), Cuisine.ITALIAN));

        desserts = List.of(
                new Dessert("obwarzanek", BigDecimal.valueOf(2.5), Cuisine.POLISH),
                new Dessert("racuchy", BigDecimal.valueOf(6.3), Cuisine.POLISH),
                new Dessert("makowiec", BigDecimal.valueOf(9.3), Cuisine.POLISH),
                new Dessert("elote", BigDecimal.valueOf(6.5), Cuisine.MEXICAN),
                new Dessert("churros", BigDecimal.valueOf(7.9), Cuisine.MEXICAN),
                new Dessert("sopapillas ", BigDecimal.valueOf(6.4), Cuisine.MEXICAN),
                new Dessert("pizzelle", BigDecimal.valueOf(7), Cuisine.ITALIAN),
                new Dessert("tiramisu", BigDecimal.valueOf(9.8), Cuisine.ITALIAN),
                new Dessert("zeppole", BigDecimal.valueOf(6.8), Cuisine.ITALIAN));

        drinks = List.of(
                new Drink("espresso", BigDecimal.valueOf(4.50)),
                new Drink("americano", BigDecimal.valueOf(4.50)),
                new Drink("latte", BigDecimal.valueOf(4.50)),
                new Drink("cappuccino", BigDecimal.valueOf(4.50)),
                new Drink("flat white", BigDecimal.valueOf(4.50)),
                new Drink("irish coffee", BigDecimal.valueOf(4.50)),
                new Drink("herbal tea", BigDecimal.valueOf(4.50)),
                new Drink("green tea", BigDecimal.valueOf(4.50)),
                new Drink("white tea", BigDecimal.valueOf(4.50)),
                new Drink("yellow tea", BigDecimal.valueOf(4.50)),
                new Drink("blue tea", BigDecimal.valueOf(4.50)),
                new Drink("red tea", BigDecimal.valueOf(4.50)),
                new Drink("black tea", BigDecimal.valueOf(4.50)));
    }

    @Override
    public List<MainCourse> getMainCoursesForCuisine(Cuisine cuisine) {
        return mainCourses.stream()
                .filter(mainCourse -> cuisine.equals(mainCourse.getCuisine()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dessert> getDessertsForCuisine(Cuisine cuisine) {
        return desserts.stream()
                .filter(dessert -> cuisine.equals(dessert.getCuisine()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setMainCoursesSource(List<MainCourse> mainCourses) {
        this.mainCourses = mainCourses;
    }

    public void setDessertsSource(List<Dessert> desserts) {
        this.desserts = desserts;
    }

    public void setDrinksSource(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
