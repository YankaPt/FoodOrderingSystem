import utils.DefaultOperationStrategy;
import utils.OperationStrategy;

public class FoodOrderingSystem {

    public static void main(String... args) {
        OperationStrategy strategy = new DefaultOperationStrategy();
        strategy.startProcess();
    }
}
