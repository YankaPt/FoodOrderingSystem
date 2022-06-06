package utils;

import dao.DefaultGeneralDao;
import entities.Cuisine;
import entities.Dessert;
import entities.Drink;
import entities.Food;
import entities.MainCourse;
import entities.Order;
import entities.OrderStatus;
import one.util.streamex.EntryStream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import static constants.Constants.BYE;
import static constants.Constants.CURRENT_LOCALE;
import static constants.Constants.DESSERT_CUISINE_SUGGESTION;
import static constants.Constants.DESSERT_LIST;
import static constants.Constants.DRINK_LIST;
import static constants.Constants.DRINK_SUGGESTION;
import static constants.Constants.EMPTY_ORDER;
import static constants.Constants.ENTRY_SEPARATOR;
import static constants.Constants.EXIT;
import static constants.Constants.GREETING;
import static constants.Constants.ICE_CUBES_SUGGESTION;
import static constants.Constants.INVALID_INPUT;
import static constants.Constants.LEMON_SUGGESTION;
import static constants.Constants.LUNCH_SUGGESTION;
import static constants.Constants.MAIN_COURSE_CUISINE_SUGGESTION;
import static constants.Constants.MAIN_COURSE_LIST;
import static constants.Constants.NO;
import static constants.Constants.ORDER_INFO;
import static constants.Constants.ORDER_PLACED;
import static constants.Constants.SHORT_NO;
import static constants.Constants.SHORT_YES;
import static constants.Constants.WHICH_ONE_DO_YOU_WANT_FULL;
import static constants.Constants.WHICH_ONE_DO_YOU_WANT_SHORT;
import static constants.Constants.YES;

public class DefaultOperationStrategy implements OperationStrategy {

    @Override
    public void startProcess() {
        Scanner scanner = new Scanner(System.in);
        DefaultGeneralDao generalDao = new DefaultGeneralDao();
        System.out.println(GREETING);
        Order currentOrder = new Order();
        Context context = new Context(scanner, generalDao, currentOrder);
        suggestLunch(context);
    }

    private void suggestLunch(Context context) {
        System.out.println(LUNCH_SUGGESTION);
        switch (context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE)) {
            case YES, SHORT_YES -> chooseCuisine(context, FoodType.MAIN_COURSE);
            case NO, SHORT_NO -> suggestDrink(context);
            case EXIT -> System.out.println(BYE);
            default -> {
                System.out.println(INVALID_INPUT);
                suggestLunch(context);
            }
        }
    }

    private void chooseCuisine(Context context, FoodType foodType) {
        switch (foodType) {
            case MAIN_COURSE -> System.out.println(MAIN_COURSE_CUISINE_SUGGESTION);
            case DESSERT -> System.out.println(DESSERT_CUISINE_SUGGESTION);
        }
        Arrays.stream(Cuisine.values()).forEach(System.out::println);
        System.out.println(WHICH_ONE_DO_YOU_WANT_SHORT);
        String cuisine = context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE);
        try {
            Cuisine chosenCuisine = Cuisine.valueOf(cuisine.toUpperCase(CURRENT_LOCALE));
            showFoodList(context, chosenCuisine, foodType);
        } catch (IllegalArgumentException exception) {
            System.out.println(INVALID_INPUT);
            chooseCuisine(context, foodType);
        }
    }

    private void showFoodList(Context context, Cuisine cuisine, FoodType foodType) {
        Map<Integer, ? extends Food> foodMap = Collections.emptyMap();
        switch (foodType) {
            case MAIN_COURSE -> {
                foodMap = EntryStream.of(context.getStorage().getMainCoursesForCuisine(cuisine)).toMap();
                System.out.println(String.format(MAIN_COURSE_LIST, cuisine.name().toLowerCase(CURRENT_LOCALE)));
            }
            case DESSERT -> {
                foodMap = EntryStream.of(context.getStorage().getDessertsForCuisine(cuisine)).toMap();
                System.out.println(String.format(DESSERT_LIST, cuisine.name().toLowerCase(CURRENT_LOCALE)));
            }
            case DRINK -> {
                foodMap = EntryStream.of(context.getStorage().getDrinks()).toMap();
                System.out.println(String.format(DRINK_LIST));
            }
        }
        System.out.println(foodMap.entrySet().stream()
                .map(this::formatFoodEntry)
                .collect(Collectors.joining("\n")));
        switch (foodType) {
            case MAIN_COURSE -> chooseFood(context, foodMap, FoodType.MAIN_COURSE);
            case DESSERT -> chooseFood(context, foodMap, FoodType.DESSERT);
            case DRINK -> chooseFood(context, foodMap, FoodType.DRINK);
        }
    }

    private String formatFoodEntry(Map.Entry<Integer, ? extends Food> foodEntry) {
        return foodEntry.getKey() + ENTRY_SEPARATOR + FormatUtil.formatFood(foodEntry.getValue());
    }

    private void chooseFood(Context context, Map<Integer, ? extends Food> foodMap, FoodType foodType) {
        System.out.println(WHICH_ONE_DO_YOU_WANT_FULL);
        String chosenName = context.getScanner().nextLine();
        Optional<? extends Food> chosenFood = parseChosenFood(foodMap, chosenName);
        if (chosenFood.isEmpty()) {
            System.out.println(INVALID_INPUT);
            chooseFood(context, foodMap, foodType);
        } else {
            switch (foodType) {
                case MAIN_COURSE -> {
                    context.getOrder().setMainCourse((MainCourse) chosenFood.get());
                    chooseCuisine(context, FoodType.DESSERT);
                }
                case DESSERT -> {
                    context.getOrder().setDessert((Dessert) chosenFood.get());
                    suggestDrink(context);
                }
                case DRINK -> {
                    context.getOrder().setDrink((Drink) chosenFood.get());
                    addIceCube(context, (Drink) chosenFood.get());
                }
            }
        }
    }

    private Optional<? extends Food> parseChosenFood(Map<Integer, ? extends Food> food, String chosenName) {
        return food.entrySet().stream()
                .filter(entry -> entry.getKey().toString().equalsIgnoreCase(chosenName)
                        || entry.getValue().getName().equalsIgnoreCase(chosenName))
                .map(Map.Entry::getValue)
                .findAny();
    }

    private void suggestDrink(Context context) {
        System.out.println(DRINK_SUGGESTION);
        switch (context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE)) {
            case YES, SHORT_YES -> showFoodList(context, null, FoodType.DRINK);
            case NO, SHORT_NO -> finalCheckout(context);
            default -> {
                System.out.println(INVALID_INPUT);
                suggestDrink(context);
            }
        }
    }

    private void addIceCube(Context context, Drink drink) {
        System.out.println(ICE_CUBES_SUGGESTION);
        switch (context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE)) {
            case YES, SHORT_YES -> {
                drink.setIceCubeIncluded(true);
                addLemon(context, drink);
            }
            case NO, SHORT_NO -> {
                drink.setIceCubeIncluded(false);
                addLemon(context, drink);
            }
            default -> {
                System.out.println(INVALID_INPUT);
                addIceCube(context, drink);
            }
        }
    }

    private void addLemon(Context context, Drink drink) {
        System.out.println(LEMON_SUGGESTION);
        switch (context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE)) {
            case YES, SHORT_YES -> {
                drink.setLemonIncluded(true);
                finalCheckout(context);
            }
            case NO, SHORT_NO -> {
                drink.setLemonIncluded(false);
                finalCheckout(context);
            }
            default -> {
                System.out.println(INVALID_INPUT);
                addLemon(context, drink);
            }
        }
    }

    private void finalCheckout(Context context) {
        String formattedOrder = FormatUtil.formatOrder(context.getOrder());
        if (formattedOrder.equals(EMPTY_ORDER)) {
            System.out.println(formattedOrder);
            suggestLunch(context);
        } else {
            System.out.println(String.format(ORDER_INFO, formattedOrder));
            switch (context.getScanner().nextLine().toLowerCase(CURRENT_LOCALE)) {
                case YES, SHORT_YES -> placeOrder(context);
                case NO, SHORT_NO -> startProcess();
                default -> {
                    System.out.println(INVALID_INPUT);
                    finalCheckout(context);
                }
            }
        }
    }

    private void placeOrder(Context context) {
        context.getOrder().setId(Math.round(Math.random() * 100));
        context.getOrder().setStatus(OrderStatus.PLACED);
        System.out.println(String.format(ORDER_PLACED, context.getOrder().getId()));
    }

    private enum FoodType {
        MAIN_COURSE,
        DESSERT,
        DRINK
    }
}
