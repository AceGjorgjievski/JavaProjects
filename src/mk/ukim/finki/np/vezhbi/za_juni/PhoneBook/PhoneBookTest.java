package mk.ukim.finki.np.vezhbi.za_juni.PhoneBook;

import java.util.*;

class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s",number));
    }
}

class Contact{
    String name;
    String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%s %s",name, phone);
    }
}

class PhoneBook {
    Map<String, Set<Contact>> nameNumberMap;
    Set<String> allPhoneNumbers;
    final Comparator<Contact> namePhoneComparator = Comparator.comparing(Contact::getName)
                    .thenComparing(Contact::getPhone);
    Map<String, Set<Contact>> contactsByName;


    PhoneBook() {
        nameNumberMap = new HashMap<>();
        allPhoneNumbers = new HashSet<>();
        contactsByName = new HashMap<>();
    }


    public void addContact(String name, String phone) throws DuplicateNumberException {
        if(allPhoneNumbers.contains(phone)) throw new DuplicateNumberException(phone);
        else {
            allPhoneNumbers.add(phone);
            Contact c = new Contact(name, phone);
            List<String> getSubString = getSubstrings(phone);
            for(String s : getSubString) {
                nameNumberMap.putIfAbsent(s, new TreeSet<>(namePhoneComparator));
                nameNumberMap.get(s).add(c);
            }

            contactsByName.putIfAbsent(name, new TreeSet<>(namePhoneComparator));
            contactsByName.get(name).add(c);
        }
    }

    public void contactsByNumber(String number) {
        Set<Contact> contacts = nameNumberMap.get(number);
        if(contacts == null) {
            System.out.println("NOT FOUND");
        } else {
            contacts.stream()
                    .forEach(i -> System.out.println(i));
        }

    }

    private List<String> getSubstrings(String phone) {
        List<String> result = new ArrayList<>();
        for(int len = 3; len<=phone.length(); len++) {
            for(int i=0; i<=phone.length()-len; i++) {
                result.add(phone.substring(i, i+len));
            }
        }
        return result;
    }

    public void contactsByName(String name) {
        Set<Contact> temp = contactsByName.get(name);
        if(temp == null) {
            System.out.println("not found");
        } else {
            temp.stream()
                    .forEach(i -> System.out.println(i));
        }
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

// Вашиот код овде

