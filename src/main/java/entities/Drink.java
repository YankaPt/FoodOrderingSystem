package entities;

import java.math.BigDecimal;

public class Drink extends Food {

    private boolean isIceCubeIncluded;
    private boolean isLemonIncluded;

    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    public boolean isIceCubeIncluded() {
        return isIceCubeIncluded;
    }

    public void setIceCubeIncluded(boolean iceCubeIncluded) {
        isIceCubeIncluded = iceCubeIncluded;
    }

    public boolean isLemonIncluded() {
        return isLemonIncluded;
    }

    public void setLemonIncluded(boolean lemonIncluded) {
        isLemonIncluded = lemonIncluded;
    }
}
