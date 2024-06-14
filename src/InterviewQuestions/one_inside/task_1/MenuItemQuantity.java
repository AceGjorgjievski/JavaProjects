package InterviewQuestions.one_inside.task_1;


public class MenuItemQuantity {
    private MenuItem menuItem;
    private int quantity;

    public MenuItemQuantity(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.menuItem.getTaxPercentage() * quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: "+ this.menuItem.getName()+"\n");
        sb.append("\tDescription: " + this.menuItem.getDescription()+"\n");
        sb.append(String.format("\tTotalPrice: (%.2f x %d) = %.2f",
                this.menuItem.getTaxPercentage(),
                this.quantity,
                this.getTotalPrice())+"\n");

        return sb.toString();
    }
}
