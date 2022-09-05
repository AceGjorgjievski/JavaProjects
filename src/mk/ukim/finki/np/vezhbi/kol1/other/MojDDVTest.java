package mk.ukim.finki.np.vezhbi.kol1.other;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


enum Type {
    A,B,V
}

class AmountNotAllowedException extends Exception{
    public AmountNotAllowedException(int price) {
        super(String.format("Receipt with amount %d is not allowed to be scanned",price));
    }
}

class Item {
    private int price;
    private Type type;

    public Item(int price, Type type) {
        this.price = price;
        this.type = type;
    }

    public Item(int price){
        this.price = price;
        this.type = null;
    }

    public double getTaxReturn(){
        if(type.equals(Type.A)) return this.price*0.18*0.15;
        else if(type.equals(Type.B)) return this.price*0.05*0.15;
        return 0;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", type=" + type +
                '}';
    }
}

class FiscalReceip {
    private long idItem;
    private List<Item> itemList;

    public FiscalReceip() {
        this.idItem = -1;
        this.itemList = new ArrayList<>();
    }

    public FiscalReceip(long idItem, List<Item> itemList) {
        this.idItem = idItem;
        this.itemList = itemList;
    }

    //ID item_price1 item_tax_type1 item_price2 item_tax_type2
    //12334 1789 А 1238 B 1222 V 111 V
    public static FiscalReceip createFiscalReceip(String inputLine) throws AmountNotAllowedException {
        String [] parts = inputLine.split("\\s++");
        long indexItem = Long.parseLong(parts[0]);

        List<Item> newList = new ArrayList<>();
        Arrays.asList(parts)
                .stream()
                .skip(1)
                .forEach(i -> {
                    if(Character.isDigit(i.charAt(0))) {
                        newList.add(new Item(Integer.parseInt(i)));
                    } else {
                        newList.get(newList.size()-1).setType(Type.valueOf(i));
                    }
                });

        if(totalAmount(newList) > 30000) throw new AmountNotAllowedException(totalAmount(newList));

        return new FiscalReceip(indexItem,newList);
    }

    public static int totalAmount(List<Item> items) {
        return items.stream()
                .mapToInt(i -> i.getPrice())
                .sum();
    }

    public long getIdItem() {
        return idItem;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public double sumTaxReturns() {
        return this.getItemList().stream()
                .mapToDouble(Item::getTaxReturn).sum();
    }

    public int sumAmount() {
        return this.getItemList()
                .stream().mapToInt(i -> i.getPrice()).sum();
    }

    @Override
    public String toString() {
        return String.format("%d %d %.2f",getIdItem(),sumAmount(),sumTaxReturns());
    }
}

class MojDDV {
    private List<FiscalReceip> fiscalReceipList;

    public MojDDV() {
        this.fiscalReceipList = new ArrayList<>();
    }

    public void readRecords(InputStream in) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        this.fiscalReceipList = bf.lines()
                .map(i -> {
                    try {
                        return FiscalReceip.createFiscalReceip(i);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());

        this.fiscalReceipList = fiscalReceipList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }


    public void printTaxReturns(PrintStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        this.fiscalReceipList.stream()
                .forEach(i -> printWriter.println(i.toString()));
        printWriter.flush();
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