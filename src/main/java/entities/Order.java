package entities;

public class Order {

    private long id;
    private MainCourse mainCourse;
    private Dessert dessert;
    private Drink drink;
    private OrderStatus status = OrderStatus.NEW;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MainCourse getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(MainCourse mainCourse) {
        this.mainCourse = mainCourse;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
