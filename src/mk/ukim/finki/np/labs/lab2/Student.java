package mk.ukim.finki.np.labs.lab2;

import java.util.Arrays;
import java.util.Objects;

public class Student {
    private Contact [] contacts;
    private int emailContacts;
    private int phoneContacts;
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
    }

    public Student (Student s){
        firstName = s.firstName;
        lastName = s.lastName;
        city = s.city;
        age = s.age;
        index = s.index;
        emailContacts = s.emailContacts;
        phoneContacts = s.phoneContacts;
        contacts = Arrays.copyOf(s.contacts,s.contacts.length);
    }

    public void addEmailContact(String date, String email){
        Contact [] temp = new Contact[contacts.length+1];
        int i;
        for( i=0; i<contacts.length; i++){
            temp[i] = contacts[i];
        }
        temp[i] = new EmailContact(date,email);
        contacts = temp;
        emailContacts++;
    }

    public void addPhoneContact(String date, String phone){
        Contact [] temp = new Contact[contacts.length+1];
        int i;
        for(i=0; i<contacts.length; i++){
            temp[i] = contacts[i];
        }
        temp[i] = new PhoneContact(date,phone);
        contacts = temp;
        phoneContacts++;
    }

    public Contact [] getEmailContacts(){
        Contact [] temp = new Contact[emailContacts];
        int j=0;
        for(int i=0; i<contacts.length; i++){
            if(contacts[i].getType().equals("Email")){
                temp[j++] = new EmailContact((EmailContact) contacts[i]);
            }
        }
        return temp;
    }


    public Contact [] getPhoneContacts(){
        Contact [] temp = new Contact[phoneContacts];
        int j=0;
        for(int i=0; i<contacts.length; i++){
            if(contacts[i].getContact().equals("Phone")){
                temp[j++] = new PhoneContact((PhoneContact) contacts[i]);
            }
        }
        return temp;
    }

    public String getCity(){
        return city;
    }

    public String getFullName(){
        return String.format("%s %s",firstName,lastName);
    }

    public long getIndex(){
        return index;
    }

    public Contact getLatestContact(){
        Contact latest = contacts[0];
        for(int i=1; i<contacts.length; i++){
            if(contacts[i].isNewerThan(latest)){
                latest = contacts[i];
            }
        }
        if(latest.getContact().equals("Email")){
            latest = new EmailContact((EmailContact) latest);
        } else {
            latest = new PhoneContact((PhoneContact) latest);
        }
        return latest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return emailContacts == student.emailContacts && phoneContacts == student.phoneContacts && age == student.age && index == student.index && Arrays.equals(contacts, student.contacts) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(city, student.city);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(emailContacts, phoneContacts, firstName, lastName, city, age, index);
        result = 31 * result + Arrays.hashCode(contacts);
        return result;
    }

    public int numberOfContact() {
        return emailContacts + phoneContacts;
    }

    private static String withSign(String a) {
        return "\"" + a + "\"";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(withSign("ime")).append(":").append(withSign(firstName)).append(", ").append(withSign("prezime"));
        sb.append(":").append(withSign(lastName)).append(", ").append(withSign("vozrast")).append(":").append(age);
        sb.append(", ").append(withSign("grad")).append(":").append(withSign(city)).append(", ").append(withSign("indeks"));
        sb.append(":").append(index).append(", ").append(withSign("telefonskiKontakti")).append(":[");
        int counter =0;
        for (int i=0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Phone")) {
                sb.append(withSign(contacts[i].getContact()));
                ++counter;
                if (counter < phoneContacts)
                    sb.append(", ");
            }
            if (counter == phoneContacts){
                sb.append("]");
                break;
            }
        }
        sb.append(", ");
        sb.append(withSign("emailKontakti")).append(":[");
        counter =0;
        for (int i=0; i < contacts.length; ++i) {
            if (contacts[i].getType().equals("Email")) {
                sb.append(withSign(contacts[i].getContact()));
                ++counter;
                if (counter < emailContacts)
                    sb.append(", ");
            }
            if (counter == emailContacts) {
                sb.append("]}");
                break;
            }

        }
        return sb.toString();
    }
}
