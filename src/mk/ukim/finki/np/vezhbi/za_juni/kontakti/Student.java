package mk.ukim.finki.np.vezhbi.za_juni.kontakti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private List<Contact> contacts;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.contacts = new ArrayList<>();
    }

    public void addEmailContact(String date, String email) {
        this.contacts.add(new EmailContact(date, email));
    }

    public void addPhoneContact(String date, String phone) {
        this.contacts.add(new PhoneContact(date, phone));
    }

    public Contact [] getEmailContacts() {
        return this.contacts.stream()
                .filter(i -> i.getType().equals("Email"))
                .toArray(Contact[]::new);
    }

    public Contact [] getPhoneContacts() {
        return this.contacts.stream()
                .filter(i -> i.getType().equals("Phone"))
                .toArray(Contact[]::new);
    }

    public String getFullName() {
        return String.format("%s %s",this.firstName.toUpperCase(), this.lastName.toUpperCase());
    }

    public Contact getLatestContact() {
        return this.contacts.stream()
                .reduce((c1, c2) -> c1.isNewerThan(c2) ? c1 : c2)
                .get();
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public long getIndex() {
        return index;
    }

    public int getNumberOfContacts(){
        return this.contacts.size();
    }

    @Override
    public String toString() {
        String phoneContacts = this.contacts.stream().map(i -> ((PhoneContact)i).toString()).collect(Collectors.joining(", "));
        String emailContacts = this.contacts.stream().map(i -> ((EmailContact)i).toString()).collect(Collectors.joining(", "));


        return String.format("{\"ime\":\"%s\", \"prezime\":\"%s\", \"vozrast\":%s, \"grad\":\"%s\", \"indeks\":%s, \"telefonskiKontakti\":[%s], \"emailKontakti\":[%s]}",
                firstName, lastName, age, city, index, phoneContacts, emailContacts);

    }
}
