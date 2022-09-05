package mk.ukim.finki.np.vezhbi.za_juni.tel_imenik;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PhoneBook {
    private List<Contact> contactList;
    public PhoneBook() {
        this.contactList = new ArrayList<>();
    }

    public void addContact(Contact other) throws MaximumSizeExceddedException, InvalidNameException {
        if(this.contactList.size() == 249) throw new MaximumSizeExceddedException();
        Contact contact = this.contactList.stream()
                .filter(i -> i.getName().equals(other.getName()))
                .findAny()
                .orElse(null);

        if(contact!= null) throw new InvalidNameException(contact.getName());

        this.contactList.add(other);
    }

    public Contact getContactForName(String name) {
        return this.contactList.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public int numberOfContacts(){
        return this.contactList.size();
    }

    public Contact [] getContacts() {
        return this.contactList.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .toArray(Contact[]::new);
    }

    public boolean removeContact(String name) {
        return this.contactList.removeIf(i -> i.getName().equals(name));
    }

    public static boolean saveAsTextFile(PhoneBook phoneBook, String path) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(path);
            pw.println(phoneBook.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static PhoneBook loadFromTextFile(String path) throws IOException, InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;
        PhoneBook book = new PhoneBook();

        while ((line = reader.readLine()) != null) {
            StringBuilder sb = new StringBuilder(line + "-");
            int num = Integer.parseInt(reader.readLine());
            for(int i=0; i<num; i++) {
                if(i == num-1) {
                    sb.append(reader.read());
                } else {
                    sb.append(reader.readLine()).append("_");
                }
            }
            Contact toAdd = new Contact(sb.toString());

            book.addContact(toAdd);
            reader.readLine();
        }
        return book;
    }

    public Contact [] getContactsForNumber(String number_prefix) {
        return this.contactList.stream()
                .filter(i -> i.hasContactFromPrefix(number_prefix))
                .toArray(Contact[]::new);


    }
}
