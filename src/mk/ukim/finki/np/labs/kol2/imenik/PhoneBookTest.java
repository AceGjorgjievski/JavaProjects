package mk.ukim.finki.np.labs.kol2.imenik;


import java.util.*;

class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: [%s]",number));
    }
}

class Contact implements Comparable<Contact>{
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Contact o) {
        int res = this.name.compareTo(o.name);
        if(res == 0) {
            return this.number.compareTo(o.number);
        }
        return res;
    }

    @Override
    public String toString() {
        return String.format("%s %d",name,number);
    }
}

class PhoneBook {
    private Set<String> stringSet;
    private Map<String, Set<Contact>> substringMap;

    public PhoneBook() {
        stringSet = new HashSet<>();
        substringMap = new HashMap<>();
    }

    private List<String> subNumbers(String number) {
        //077 123
        List<String> temp = new ArrayList<>();
        for(int i=3; i<=number.length(); i++) {
            for(int j=0; j<=number.length()-i; j++) {
                temp.add(number.substring(j,i+j));
            }
        }
        return temp;
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if(stringSet.contains(number)) {
            throw new DuplicateNumberException(number);
        } else {
            stringSet.add(number);
            Contact c = new Contact(name,number);
            List<String> subNumbers = subNumbers(number);
            for(String s : subNumbers) {
                substringMap.putIfAbsent(s,new TreeSet<>());
                substringMap.get(s).add(c);
            }
        }

    }

    public void contactsByNumber(String number) {
        Set<Contact> contacts = substringMap.get(number);
        if(contacts == null) {
            System.out.println("NOT FOUND");
            return;
        }
        this.substringMap.get(number)
                .forEach(i -> System.out.println(i.toString()));
    }

    public void contactsByName(String name) {
    }
}

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
    }

}