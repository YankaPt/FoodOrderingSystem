package utils;

import entities.Drink;
import entities.Food;
import entities.Order;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static constants.Constants.CURRENT_LOCALE;
import static constants.Constants.DESSERT_IS;
import static constants.Constants.DRINK_IS;
import static constants.Constants.EMPTY_ORDER;
import static constants.Constants.FOOD_SEPARATOR;
import static constants.Constants.MAIN_COURSE_IS;
import static constants.Constants.TOTAL_PRICE_IS;
import static constants.Constants.WITH_ICE_CUBES;
import static constants.Constants.WITH_LEMON;

public final class FormatUtil {

    private FormatUtil() {
    }

    public static String formatFood(Food food) {
        return food.getName() + FOOD_SEPARATOR + formatPrice(food.getPrice());
    }

    public static String formatDrink(Drink drink) {
        String result = drink.getName();
        if (drink.isIceCubeIncluded()) {
            result += WITH_ICE_CUBES;
        }
        if (drink.isLemonIncluded()) {
            result += WITH_LEMON;
        }
        result += FOOD_SEPARATOR + formatPrice(drink.getPrice());
        return result;
    }

    public static String formatOrder(Order order) {
        String formattedOrder = StringUtils.EMPTY;
        if (order.getMainCourse() != null) {
            formattedOrder += MAIN_COURSE_IS + formatFood(order.getMainCourse()) + "\n";
        }
        if (order.getDessert() != null) {
            formattedOrder += DESSERT_IS + formatFood(order.getDessert()) + "\n";
        }
        if (order.getDrink() != null) {
            formattedOrder += DRINK_IS + formatDrink(order.getDrink()) + "\n";
        }
        if (StringUtils.isNotBlank(formattedOrder)) {
            formattedOrder += TOTAL_PRICE_IS + formatPrice(calculateTotalPrice(order));
        } else {
            formattedOrder = EMPTY_ORDER;
        }
        return formattedOrder;
    }

    public static BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (order.getMainCourse() != null) {
            totalPrice = totalPrice.add(order.getMainCourse().getPrice());
        }
        if (order.getDessert() != null) {
            totalPrice = totalPrice.add(order.getDessert().getPrice());
        }
        if (order.getDrink() != null) {
            totalPrice = totalPrice.add(order.getDrink().getPrice());
        }
        return totalPrice;
    }

    private static String formatPrice(BigDecimal price) {
        return NumberFormat.getCurrencyInstance(CURRENT_LOCALE).format(price);
    }
}
