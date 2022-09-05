package mk.ukim.finki.np.labs.lab3.TelImenik;

import javax.naming.InvalidNameException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class PhoneBook {
    private Contact [] contacts;

    public PhoneBook() {
        contacts = new Contact[0];
    }

    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if(contacts.length == 250){
            throw new MaximumSizeExceddedException();
        }
        for(int i=0; i< contacts.length; i++){
            if(!(contacts[i].getName().equals(contact.getName()))){
                throw new InvalidNameException();
            }
        }

        Contact [] temp = new Contact[contacts.length+1];
        for(int i=0; i< contacts.length; i++){
            temp[i] = contacts[i];
        }
        temp[contacts.length] = contact;

        contacts = temp;
    }

    public Contact getContactForName(String name){
        for(int i=0; i< contacts.length; i++){
            if(contacts[i].getName().equals(name)){
                return contacts[i];
            }
        }
        return null;
    }

    public boolean removeContact(String name){
        Contact [] temp = new Contact[contacts.length-1];

        boolean flag = false;
        int j=0;

        for(int i=0; i<contacts.length; i++){
            if(!(contacts[i].getName().equals(name))){
                temp[j++] = contacts[i];
            } else {
                flag = true;
            }
        }

        contacts = temp;
        return flag;
    }

    public static boolean saveAsTextFile(PhoneBook phonebook,String path){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(path));
            printWriter.print(phonebook.toString());
            printWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PhoneBook loadFromTextFile(String path){
        return null;
    }

    public Contact [] getContactForNumber(String number_prefix) throws InvalidNameException, MaximumSizeExceddedException {

        PhoneBook phoneBook = new PhoneBook();

        for(int i=0; i< contacts.length; i++){
            String [] numbers = contacts[i].getPhonenumber();
            for(int j=0; j<numbers.length; j++){
                if(numbers[j].startsWith(number_prefix)){
                    phoneBook.addContact(contacts[i]);
                    break;
                }
            }
        }

        Arrays.sort(phoneBook.contacts);

        return phoneBook.contacts;

    }

    public boolean numberOfContacts() {
        return false;
    }

    public boolean[] getContacts() {
        return null;
    }

    public long[] getContactsForNumber(String nextLine) {
        return null;
    }
}
