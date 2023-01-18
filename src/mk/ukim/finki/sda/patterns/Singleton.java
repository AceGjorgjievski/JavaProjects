package mk.ukim.finki.sda.patterns;


import java.util.HashMap;

class Logger {
    private StringBuilder sb;
    private static Logger instance;
    private static HashMap<String, String> map = new HashMap<>();

    private Logger() {
        sb = new StringBuilder();
    }

    public static Logger getInstance() {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void addLog(String log) {
        this.sb.append(log).append("\n");
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}

class BaseSingleton {
    // private constructor will not work
    protected BaseSingleton() { }

    private static BaseSingleton instance;
    public static BaseSingleton getInstance()
    {
        if (instance == null) {
            instance = new BaseSingleton();
        }
        return instance;
    }

    //Some state variables
    protected int someInt;

    //Function is marked as virtual so that it can be overidden
    public void DoSomething() {
        someInt = 1;
    }
}


class SubClassSingleton extends BaseSingleton
{
    private SubClassSingleton() {

    }

    public void DoSomething()
    {
        someInt = 2;
    }

    public void NewFunction() {
        //new functionality here
    }
}
public class Singleton {

    public static void main(String[] args) {

        BaseSingleton.getInstance().DoSomething();

        SubClassSingleton.getInstance().DoSomething();

//        SubClassSingleton.getInstance().



//        Logger l1 = Logger.getInstance();
//        Logger l2 = Logger.getInstance();
//
//        l1.addLog("Test 1");
//        l2.addLog("Test 2");
//
//        System.out.println(l1);
//        System.out.println(l2);

    }
}


