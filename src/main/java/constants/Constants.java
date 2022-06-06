package constants;

import java.util.Locale;

public final class Constants {

    private Constants() {
    }

    public static final String YES = "yes";
    public static final String SHORT_YES = "y";
    public static final String NO = "no";
    public static final String SHORT_NO = "n";
    public static final String GREETING = "Hello! Welcome to our Food Ordering System";
    public static final String EXIT = "exit";
    public static final String LUNCH_SUGGESTION = "Do you want order your lunch?[Yes/No/Exit]";
    public static final String DRINK_SUGGESTION = "Do you want add drink to your order?[Yes/No]";
    public static final String MAIN_COURSE_CUISINE_SUGGESTION = "From which cuisine do you want to choose your main course?";
    public static final String DESSERT_CUISINE_SUGGESTION = "From which cuisine do you want to choose your dessert?";
    public static final String MAIN_COURSE_LIST = "For %s cuisine we have such main courses:";
    public static final String DESSERT_LIST = "For %s cuisine we have such desserts:";
    public static final String DRINK_LIST = "We have such drinks:";
    public static final String WHICH_ONE_DO_YOU_WANT_SHORT = "Which one do you want?";
    public static final String WHICH_ONE_DO_YOU_WANT_FULL = "Which one do you want? Please type name or number.";
    public static final String ICE_CUBES_SUGGESTION = "Do you want add ice cubes to your drink?[Yes/No]";
    public static final String LEMON_SUGGESTION = "Do you want add lemon to your drink?[Yes/No]";
    public static final String ORDER_INFO = """
            Your order is: 
            %s 
            Does is OK?[Yes]
            To cancel order type "No"[No]""";
    public static final String ORDER_PLACED = "Your order was successfully placed! Your number is %s. Have a nice day!";
    public static final String BYE = "Bye!";
    public static final String EMPTY_ORDER = "Your order is empty";


    public static final String MAIN_COURSE_IS = "Main course is ";
    public static final String DESSERT_IS = "Dessert is ";
    public static final String DRINK_IS = "Drink is ";
    public static final String TOTAL_PRICE_IS = "Total price is ";
    public static final Locale CURRENT_LOCALE = new Locale("pl", "PL");
    public static final String WITH_ICE_CUBES = " with ice cubes";
    public static final String WITH_LEMON = " with lemon";
    public static final String FOOD_SEPARATOR = ", ";
    public static final String ENTRY_SEPARATOR = ") ";

    public static final String INVALID_INPUT = "Sorry, your input is invalid. Please try again.";
}
