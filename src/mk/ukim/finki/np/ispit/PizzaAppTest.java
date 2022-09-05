package mk.ukim.finki.np.ispit;


import java.util.*;

class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super(String.format("This user already exists!"));
    }
}

class User {
    private String email;
    private String name;
    private String phonenumber;

    public User(String email, String name, String phonenumber) {
        this.email = email;
        this.name = name;
        this.phonenumber = phonenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(phonenumber, user.phonenumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, phonenumber);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    //stefan.andonov@gmail.com Stefan +38976123123
    @Override
    public String toString() {
        return String.format("%s %s %s",
                email,
                name,
                phonenumber);
    }
}

class PizzaApp{
    private Map<Integer,User> userList;
    public static int ID = 1;

    public PizzaApp() {
        this.userList = new HashMap<>();
    }

    public void registerUser (String email,String name, String phoneNumber) throws UserAlreadyExistsException {
        if(userList.containsKey(ID)) {
            throw new UserAlreadyExistsException();
        } else if(userList.get(ID).getEmail().equals(email)) {
            throw new UserAlreadyExistsException();
        }
        this.userList.put(ID++,new User(email,name,phoneNumber));
    }

    public void makeOrder(String email, String pizzaName, float pizzaPrice) {

    }

    void printRevenueByPizza() {

    }

    void printMostFrequentUserForPizza() {

    }
}

public class PizzaAppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PizzaApp pizzaApp = new PizzaApp();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            String method = parts[0];

            if (method.equalsIgnoreCase("registerUser")) { //email, name, phone
                String email = parts[1];
                String name = parts[2];
                String phone = parts[3];
                try {
                    pizzaApp.registerUser(email, name, phone);
                } catch (UserAlreadyExistsException e) {
                    e.printStackTrace();
                }
            } else if (method.equalsIgnoreCase("makeOrder")) { //email, pizzaName, price
                String email = parts[1];
                String pizzaName = parts[2];
                float price = Float.parseFloat(parts[3]);
                pizzaApp.makeOrder(email, pizzaName, price);
            } else if (method.equalsIgnoreCase("printRevenueByPizza")) {
                System.out.println("Print revenue by pizza");
                pizzaApp.printRevenueByPizza();
            } else { //printMostFrequentUserForPizza
                System.out.println("Print most frequent user(s) by pizza");

                pizzaApp.printMostFrequentUserForPizza();
            }
        }
    }
}