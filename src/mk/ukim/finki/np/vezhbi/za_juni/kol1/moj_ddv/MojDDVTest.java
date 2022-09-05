package mk.ukim.finki.np.vezhbi.za_juni.kol1.moj_ddv;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class AmountNotAllowedException extends Exception {
    public AmountNotAllowedException(int amount) {
        super(String.format("Receipt with amount %d is not allowed to be scanned",amount));
    }
}

enum TaxType {
    A, B, V
}

class Item {
    private int price;
    private TaxType taxType;

    public Item(int price) {
        this.price = price;
        this.taxType = null;
    }

    public Item(int price, TaxType taxType) {
        this.price = price;
        this.taxType = taxType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public double getTaxReturn() {
        return this.taxType == TaxType.A ? this.price*0.18 :
                this.taxType == TaxType.B ? this.price*0.05 :
                        0;
    }

    @Override
    public String toString() {
        return String.format("%d %.5f",this.price, this.getTaxReturn());
    }
}

class Receipt implements Comparable<Receipt>{
    private int id;
    private List<Item> items;

    public Receipt() {
        this.items = new ArrayList<>();
        this.id = -1;
    }

    public Receipt(int id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    //12334 1789 А 1238 B 1222 V 111 V
    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String [] parts = line.split("\\s++");
        int itemId = Integer.parseInt(parts[0]);
        List<Item> temp = new ArrayList<>();

        Arrays.stream(parts)
                .skip(1)
                .forEach(i -> {
                    if(Character.isDigit(i.charAt(0))) {
                        temp.add(new Item(Integer.parseInt(i)));
                    } else {
                        temp.get(temp.size()-1).setTaxType(TaxType.valueOf(i));
                    }
                });

        int totalPrice = totalAmount(temp);
        if(totalPrice > 30000) throw new AmountNotAllowedException(totalPrice);

        return new Receipt(itemId, temp);
    }

    public static int totalAmount(List<Item> items) {
        return items.stream().mapToInt(i -> i.getPrice()).sum();
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public int totalItemSum(){
        return this.items.stream().mapToInt(i -> i.getPrice()).sum();
    }

    public double totalTaxSum() {
        return this.items.stream().mapToDouble(i -> i.getTaxReturn()).sum();
    }


    @Override
    public String toString() {
        return String.format("%d\t%d\t%.5f",
                this.id,
                this.totalItemSum(),
                this.totalTaxSum());
    }

    @Override
    public int compareTo(Receipt other) {
        return Comparator.comparing(Receipt::totalTaxSum)
                .thenComparing(Receipt::totalItemSum)
                .compare(this, other);
    }
}


class MojDDV {
    private List<Receipt> receipts;
    public Comparator comparator = Comparator.comparing(Receipt::totalTaxSum)
            .thenComparing(Receipt::totalItemSum);

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        this.receipts = reader.lines()
                .map(i -> {
                    Receipt newReceipt = null;
                    try {
                         newReceipt = Receipt.createReceipt(i);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                    return newReceipt;
                }).collect(Collectors.toList());

        this.receipts = receipts.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void printSorted(PrintStream out) {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        this.receipts.stream().sorted().forEach(i -> pw.println(i.toString()));
        pw.flush();
    }

    public void printStatistics(PrintStream out) {
    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING SORTED RECORDS BY TAX RETURNS TO OUTPUT STREAM ===");
        mojDDV.printSorted(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        //mojDDV.printStatistics(System.out);

    }
}