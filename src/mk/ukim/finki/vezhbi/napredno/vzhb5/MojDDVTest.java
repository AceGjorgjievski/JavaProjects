package mk.ukim.finki.vezhbi.napredno.vzhb5;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum TaxType {
    A,B,V
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
}

class FiscalReceipt {
    private List<Item> items;
    private int id;

    public FiscalReceipt(List<Item> items, int id) {
        this.items = items;
        this.id = id;
    }

    public static FiscalReceipt create(String input) throws Exception {
        String [] parts = input.split("\\s++");
        int localId = Integer.parseInt(parts[0]);

        List<Item> temp = new ArrayList<>();

        Arrays.stream(parts)
                .forEach(i -> {
                    if(Character.isDigit(i.charAt(0))) {
                        temp.add(new Item(Integer.parseInt(i)));
                    } else {
                        temp.get(temp.size()-1).setTaxType(TaxType.valueOf(i));
                    }
                });

        int totalAmount = calculate(temp);

        if(totalAmount > 30000) throw new Exception();

        return new FiscalReceipt(temp,localId);
    }

    private static int calculate(List<Item> temp) {
        return temp.stream().mapToInt(i -> i.getPrice()).sum();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setId(int id) {
        this.id = id;
    }
}



class MojDDV {
    private List<FiscalReceipt> fiscalReceiptList;


    public void readRecords(InputStream in){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        this.fiscalReceiptList = br.lines()
                .map(i -> {
                    FiscalReceipt fiscalReceipt = null;
                    try {
                        fiscalReceipt = FiscalReceipt.create(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                    return fiscalReceipt;
                }).collect(Collectors.toList());

        this.fiscalReceiptList = this.fiscalReceiptList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void printTaxReturns(PrintStream out) {

    }
}

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}