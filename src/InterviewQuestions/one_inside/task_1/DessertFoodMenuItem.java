package InterviewQuestions.one_inside.task_1;

import InterviewQuestions.one_inside.task_1.interfaces.FoodMenuItemInterface;

public class DessertFoodMenuItem extends FoodMenuItem implements FoodMenuItemInterface {
    public DessertFoodMenuItem(String name, String description, double price) {
        super(name, description, price, TaxPercentage.FOOD_ITEM_TAX);
    }

    @Override
    public double getPriceWithFoodMenuItemTax() {
        return this.price * TaxPercentage.FOOD_ITEM_TAX;
    }

    @Override
    public FoodMenuItemCategory getFoodMenuItemCategory() {
        return FoodMenuItemCategory.DESERT;
    }

    @Override
    public double getTaxPercentage() {
        return getPriceWithFoodMenuItemTax();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + this.name);
        sb.append("Description: " + this.description);
        sb.append("Price with tax: " + this.getPriceWithFoodMenuItemTax());


        return sb.toString();
    }
}
