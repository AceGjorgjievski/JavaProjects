package mk.ukim.finki.np.vezhbi.labs.tlefonskiImenik;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneBook {
    private List<Contact> contactList;

    public PhoneBook() {
        contactList = new ArrayList<>();
    }

    public void addContact(Contact other) throws MaximumSizeExceddedException, InvalidNameException {
        if(this.contactList.size() > 250) throw new MaximumSizeExceddedException();
        if(this.contactList.stream().anyMatch(i -> i.getName().equals(other.getName()))) throw new InvalidNameException(other.getName());
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
        List<Contact> temp = new ArrayList<>();
        temp.addAll(contactList);
        return temp.stream().sorted().toArray(Contact[]::new);
    }

    public boolean removeContact(String name) {
        Contact contactToBeDeleted = getContactForName(name);
        if(contactToBeDeleted != null) return false;
        this.contactList.removeIf(i -> i.getName().equals(contactToBeDeleted.getName()));
        return true;
    }

    @Override
    public String toString() {
        return Arrays.stream(this.getContacts())
                .sorted()
                .map(Contact::toString)
                .collect(Collectors.joining("\n")) + "\n";
    }

    public static boolean saveAsTextFile(PhoneBook phonebook,String path) {
        ObjectOutputStream objectOutputStream = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(phonebook);
                objectOutputStream.flush();
                objectOutputStream.close();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static PhoneBook loadFromTextFile(String path) {
        ObjectInputStream objectInputStream = null;
        PhoneBook phoneBook = null;
        try {
            File file = new File(path);
            if(file.exists()) {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                try {
                    phoneBook = (PhoneBook) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phoneBook;
    }

    public Contact [] getContactsForNumber(String number_prefix) {
        return this.contactList.stream()
                .filter(contact -> (
                        IntStream.range(0,contact.getNumbers().length)
                                .anyMatch(i -> contact.getNumbers()[i].startsWith(number_prefix))
                        )).toArray(Contact[]::new);
    }
}
