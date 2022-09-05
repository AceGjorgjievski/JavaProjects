package mk.ukim.finki.np.labs.lab3.TelImenik;

import javax.naming.InvalidNameException;
import java.util.Arrays;

public class Contact {
    private String name;
    private String[] phonenumbers;

    public Contact(String name, String... phonenumber) throws InvalidNameException, MaximumSizeExceddedException, InvalidNumberException {
        if ((name.length() <= 4 || name.length() > 10)) {
            throw new InvalidNameException();
        }

        if (phonenumber.length > 5) throw new MaximumSizeExceddedException();

        for (int i = 0; i < phonenumber.length; i++) {
            if (!(Character.isDigit(name.charAt(i)) &&
                    Character.isAlphabetic(name.charAt(i)))) {
                throw new InvalidNumberException();
            }
        }

        for (int i = 0; i < phonenumber.length; i++) {
            if(phonenumber != null && phonenumber.length == 5) throw new MaximumSizeExceddedException();
            if((phonenumber[i].length() != 9)) throw new InvalidNumberException();
            if(phonenumber[i].length() == 9){
                if(!(phonenumber[i].startsWith("070") || phonenumber[i].startsWith("071") ||
                        phonenumber[i].startsWith("072") || phonenumber[i].startsWith("075") ||
                        phonenumber[i].startsWith("076") || phonenumber[i].startsWith("077") ||
                        phonenumber[i].startsWith("078"))){
                    throw new MaximumSizeExceddedException();
                }
            }

            for(int j=0; j<phonenumber[i].length(); j++){
                if(!(Character.isDigit(phonenumber[i].charAt(j)))){
                    throw new InvalidNumberException();
                }
            }
        }


        this.name = name;
        Arrays.copyOf(phonenumber,phonenumber.length);
    }

    public String getName() {
        return name;
    }

    public String[] getPhonenumber() {
        String [] temp = Arrays.copyOf(phonenumbers,phonenumbers.length);
        Arrays.sort(temp);
        return temp;
    }

    public void addNumber(String phonenumber) throws MaximumSizeExceddedException, InvalidNumberException {
        if(phonenumber != null && phonenumber.length() == 5) throw new MaximumSizeExceddedException();
        if((phonenumber.length() != 9)) throw new InvalidNumberException();
        if(phonenumber.length() == 9){
            if(!(phonenumber.startsWith("070") || phonenumber.startsWith("071") ||
                    phonenumber.startsWith("072") || phonenumber.startsWith("075") ||
                    phonenumber.startsWith("076") || phonenumber.startsWith("077") ||
                    phonenumber.startsWith("078"))){
                throw new MaximumSizeExceddedException();
            }
        }
        for(int j=0; j<phonenumber.length(); j++){
            if(!(Character.isDigit(phonenumber.charAt(j)))){
                throw new InvalidNumberException();
            }
        }

        String [] temp = new String[phonenumbers.length+1];
        for(int i=0; i< phonenumbers.length; i++){
            temp[i] = phonenumbers[i];
        }
        temp[phonenumbers.length] = phonenumber;

        phonenumbers = temp;
    }

    public static Contact valueOf(String s) throws InvalidFormatException {
        String [] line = s.split("\n");
        String name = line[0];
        String [] numbers = new String[Integer.parseInt(line[1])];

        for(int i=0; i<numbers.length; i++){
            numbers[i] = line[i+2];
        }

        Contact contact = null;

        try {
            contact = new Contact(name,numbers);
        } catch (Exception e){
            throw new InvalidFormatException();
        }

        return contact;
    }


    public long[] getNumbers() {
        return null;
    }
}
