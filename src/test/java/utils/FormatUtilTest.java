package utils;

import entities.Dessert;
import entities.Drink;
import entities.MainCourse;
import entities.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FormatUtilTest {

    private static final String MAIN_COURSE_NAME = "mainCourse";
    private static final String DESSERT_NAME = "dessert";
    private static final String DRINK_NAME = "drink";
    private static final String FORMATTED_PRICE_VALUE = "1,00\u00a0zł";
    private static final BigDecimal PRICE = BigDecimal.ONE;

    @Mock
    private MainCourse mainCourse;
    @Mock
    private Dessert dessert;
    @Mock
    private Drink drink;
    @Mock
    private Order order;

    @Before
    public void setUp() {
        when(mainCourse.getName()).thenReturn(MAIN_COURSE_NAME);
        when(mainCourse.getPrice()).thenReturn(PRICE);
        when(dessert.getName()).thenReturn(DESSERT_NAME);
        when(dessert.getPrice()).thenReturn(PRICE);
        when(drink.getName()).thenReturn(DRINK_NAME);
        when(drink.getPrice()).thenReturn(PRICE);
        when(drink.isIceCubeIncluded()).thenReturn(true);
        when(drink.isLemonIncluded()).thenReturn(false);
    }

    @Test
    public void givenMainCourse_WhenFormatFood_ThenReturnFormattedFoodValue() {
        String result = FormatUtil.formatFood(mainCourse);

        assertEquals(MAIN_COURSE_NAME + ", " + FORMATTED_PRICE_VALUE, result);
    }

    @Test
    public void givenDrink_WhenFormatDrink_ThenReturnFormattedDrinkValue() {
        String result = FormatUtil.formatDrink(drink);

        assertEquals(DRINK_NAME + " with ice cubes"+ ", " + FORMATTED_PRICE_VALUE, result);
    }

    @Test
    public void givenFullOrder_WhenFormatOrder_ThenReturnFormattedFullOrderValue() {
        when(order.getMainCourse()).thenReturn(mainCourse);
        when(order.getDessert()).thenReturn(dessert);
        when(order.getDrink()).thenReturn(drink);

        String result = FormatUtil.formatOrder(order);

        assertEquals("""
                Main course is mainCourse, 1,00\u00a0zł
                Dessert is dessert, 1,00\u00a0zł
                Drink is drink with ice cubes, 1,00\u00a0zł
                Total price is 3,00\u00a0zł""", result);
    }

    @Test
    public void givenPartialOrder_WhenFormatOrder_ThenReturnFormattedPartialOrderValue() {
        when(order.getDrink()).thenReturn(drink);

        String result = FormatUtil.formatOrder(order);

        assertEquals("""
                Drink is drink with ice cubes, 1,00\u00a0zł
                Total price is 1,00\u00a0zł""", result);
    }
}