package mk.ukim.finki.np.vezhbi.za_juni.tel_imenik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Contact {
    private String name;
    private List<String> numbers;

    public Contact(String name, String... phonenumber) throws InvalidNameException, MaximumSizeExceddedException, InvalidNumberException {
        if(!this.checkValidName(name)) throw new InvalidNameException(name);
        this.name = name;
        this.numbers = new ArrayList<>(5);
        if(phonenumber.length > 5) throw new MaximumSizeExceddedException();
        if(!this.checkValidNumber(phonenumber)) throw new InvalidNumberException();

        Collections.addAll(this.numbers, phonenumber);
    }

    private boolean checkValidName(String name) {
        if(name.length() < 4 || name.length() > 10) return false;
        for(int i=0; i<name.length(); i++) {
            if(!Character.isLetterOrDigit(i)) return false;
        }
        return true;
    }

    private boolean checkValidNumber(String ... numbers) {
        for(int i=0; i<numbers.length; i++) {
            for(int j=0; j<numbers[i].length(); j++) {
                if(!Character.isDigit(j)) return false;
            }
            if(numbers[i].length() > 9 || !this.validNumberPrefix(numbers[i])) return false;
        }
        return true;
    }

    private boolean validNumberPrefix(String number) {
        if(number.startsWith("070") ||
                number.startsWith("071") ||
                number.startsWith("072") ||
                number.startsWith("075") ||
                number.startsWith("076") ||
                number.startsWith("077") ||
                number.startsWith("078") ) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public String [] getNumbers() {
        return (String[]) numbers.stream()
                .sorted(Comparator.naturalOrder())
                .toArray();
    }

    public void addNumber(String phoneNumber) throws MaximumSizeExceddedException, InvalidNumberException {
        if(phoneNumber.length() > 5) throw new MaximumSizeExceddedException();
        if(!this.checkValidNumber(phoneNumber)) throw new InvalidNumberException();

        this.numbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name + "\n");
        String [] sortedNumbers = this.getNumbers();
        sb.append("[");
        for(int i=0; i<sortedNumbers.length; i++) {
            if(i+1 == sortedNumbers.length) sb.append(sortedNumbers[i] + "]");
            else sb.append(sortedNumbers[i] + ",");
        }
        return sb.toString();
    }

    public static Contact valueOf(String s) {
        return null;
    }

    public boolean hasContactFromPrefix(String prefix) {
        return this.numbers.stream()
                .anyMatch(i -> i.startsWith(prefix));
    }
}
