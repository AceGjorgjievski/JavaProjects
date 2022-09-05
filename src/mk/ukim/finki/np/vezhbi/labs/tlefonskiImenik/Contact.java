package mk.ukim.finki.np.vezhbi.labs.tlefonskiImenik;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Contact implements Comparable<Contact> {
    private String name;
    private List<String> phoneNumbers;

    public Contact(String name, String... phoneNumber) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        if(!checkValidName(name)) throw new InvalidNameException(name);
        this.name = name;

        for(String s : phoneNumber) {
            if(!chackValidNumber(s)) throw new InvalidNumberException(s);
        }
        this.phoneNumbers = new ArrayList<>();
        if(this.phoneNumbers.size() + phoneNumber.length > 5) throw new MaximumSizeExceddedException();

        this.phoneNumbers.addAll(Arrays.asList(phoneNumber));
    }

    public String getName() {
        return name;
    }

    public String [] getNumbers() {
        return this.phoneNumbers.toArray(String[]::new);
    }

    public boolean checkValidName(String name) {
        return name.length()>4 && name.length()<11 &&
                IntStream.range(0,name.length())
                        .allMatch(i -> Character.isLetterOrDigit(name.charAt(i)));
    }

    public boolean chackValidNumber(String number) {
        return number.length() == 9 &&
                number.startsWith("07") &&
                number.substring(2,3).matches("[0125678]") &&
                IntStream.range(2,number.length())
                        .anyMatch(i -> Character.isDigit(number.charAt(i)));
    }

    public void addNumber(String phonenumber) throws InvalidNumberException {
        if(!chackValidNumber(phonenumber)) throw new InvalidNumberException(phonenumber);
        this.phoneNumbers.add(phonenumber);
    }

    public static Contact valueOf(String s) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("\n");
        sb.append(phoneNumbers.size()).append("\n");
        return sb.toString() + Arrays.stream(this.getNumbers()).collect(Collectors.joining("\n")) + "\n";
    }

    @Override
    public int compareTo(Contact other) {
        return this.getName().compareTo(other.getName());
    }

}
