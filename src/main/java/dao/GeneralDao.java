package dao;

import entities.Cuisine;
import entities.Dessert;
import entities.Drink;
import entities.MainCourse;

import java.util.List;

public interface GeneralDao {

    List<MainCourse> getMainCoursesForCuisine(Cuisine cuisine);

    List<Dessert> getDessertsForCuisine(Cuisine cuisine);

    List<Drink> getDrinks();
}
