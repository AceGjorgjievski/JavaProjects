package InterviewQuestions.one_inside.task_1;

public abstract class MenuItem {
    protected String name;
    protected String description;
    protected double price;
    protected double taxPercentage;


    public MenuItem(String name, String description, double price, double taxPercentage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.taxPercentage = taxPercentage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public abstract double getTaxPercentage();

}
