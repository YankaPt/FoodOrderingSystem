package dao;

import entities.Cuisine;
import entities.Dessert;
import entities.Drink;
import entities.MainCourse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultGeneralDaoTest {

    private static final Cuisine TESTED_CUISINE = Cuisine.POLISH;
    private static final Cuisine ANOTHER_CUISINE = Cuisine.ITALIAN;

    @Mock
    private MainCourse polishMainCourse;
    @Mock
    private MainCourse italianMainCourse;
    @Mock
    private Dessert polishDessert;
    @Mock
    private Dessert italianDessert;
    @Mock
    private Drink drink;

    private DefaultGeneralDao generalDao = new DefaultGeneralDao();

    @Before
    public void setUp() {
        generalDao.setMainCoursesSource(List.of(polishMainCourse, italianMainCourse));
        generalDao.setDessertsSource(List.of(polishDessert, italianDessert));
        generalDao.setDrinksSource(List.of(drink));

        when(polishMainCourse.getCuisine()).thenReturn(TESTED_CUISINE);
        when(italianMainCourse.getCuisine()).thenReturn(ANOTHER_CUISINE);
        when(polishDessert.getCuisine()).thenReturn(TESTED_CUISINE);
        when(italianDessert.getCuisine()).thenReturn(ANOTHER_CUISINE);
    }

    @Test
    public void givenCuisineAndDataSourceForMainCourses_WhenGetMainCoursesForCuisine_ThenReturnMainCoursesForCuisine() {
        List<MainCourse> result = generalDao.getMainCoursesForCuisine(TESTED_CUISINE);

        assertEquals(List.of(polishMainCourse), result);
    }

    @Test
    public void givenCuisineAndDataSourceForDesserts_WhenGetDessertsForCuisine_ThenReturnDessertsForCuisine() {
        List<Dessert> result = generalDao.getDessertsForCuisine(TESTED_CUISINE);

        assertEquals(List.of(polishDessert), result);
    }

    @Test
    public void givenDataSourceForDrinks_WhenGetDessertsForCuisine_ThenReturnDessertsForCuisine() {
        List<Drink> result = generalDao.getDrinks();

        assertEquals(List.of(drink), result);
    }
}