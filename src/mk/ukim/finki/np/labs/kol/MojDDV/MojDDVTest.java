package mk.ukim.finki.np.labs.kol.MojDDV;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;



enum Tax{
    A,B,V
}

class AmountNotAllowedException extends Exception {
    public AmountNotAllowedException(int value) {
        super(String.format("Receipt with amount %d is not allowed to be scanned",value));
    }
}

class Item {
    private int price;
    private Tax tax;

    public Item(int price, Tax tax) {
        this.price = price;
        this.tax = tax;
    }

    public Item(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public double calculateTaxPerItem(){
        if(tax.equals(Tax.A)) return 0.18* getPrice();
        else if(tax.equals(Tax.B)) return 0.05* getPrice();
        else return 0;
    }
}

class Receip implements Comparable<Receip>{
    private long id;
    private List<Item> list;

    public Receip() {
        this.list = new ArrayList<>();
    }

    public Receip(long id, List<Item> list) {
        this.id = id;
        this.list = list;
    }

    public long getId() {
        return id;
    }

    // 12334 1789 А 1238 B 1222 V 111 V

    public static Receip createReceipt(String line) throws AmountNotAllowedException {
        String [] parts = line.split("\\s+");
        long id = Long.parseLong(parts[0]);

        List<Item> lista = new ArrayList<>();

        Arrays.stream(parts)
                .skip(1)
                .forEach(i -> {
                    if(Character.isDigit(i.charAt(0))){
                        lista.add(new Item(Integer.parseInt(i)));
                    } else {
                        lista.get(lista.size() - 1).setTax(Tax.valueOf(i));
                    }
                });

        if(totalAmount(lista) > 30000){
            throw new AmountNotAllowedException(totalAmount(lista));
        }
        return new Receip(id,lista);
    }

    public static int totalAmount(List<Item> items){
        return items.stream()
                .mapToInt(i -> i.getPrice())
                .sum();
    }
    public int totalAmount(){
        return list.stream()
                .mapToInt(i -> i.getPrice())
                .sum();
    }

    public double totalTaxes(){
        return list.stream()
                .mapToDouble(i -> i.calculateTaxPerItem()).sum();
    }

    @Override
    public String toString() {
        return String.format("%ld %d %.2f",id,totalAmount(),totalAmount());
    }

    @Override
    public int compareTo(Receip other) {
        return Comparator.comparing(Receip::totalTaxes)
                .thenComparing(Receip::totalAmount)
                .compare(this,other);
    }
}

class MojDDV {
    private List<Receip> list;

    public MojDDV() {
        this.list = new ArrayList<>();
    }

    public void readRecords(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        list = bufferedReader.lines()
                .map(i -> {
                    try {
                        return Receip.createReceipt(i);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .collect(Collectors.toList());

        list = list.stream().filter(i -> Objects.nonNull(i)).collect(Collectors.toList());
    }

    public void printTaxReturns(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        list.stream()
                .sorted().
                forEach(i -> printWriter.println(list.toString()));

        printWriter.flush();
    }

    public void printStatistics(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
        doubleSummaryStatistics = list.stream()
                .mapToDouble(Receip::totalTaxes).summaryStatistics();
        printWriter.println(String.format("min: %.2f \n max: %.2f \n sum: %.2f\n count: %d\naverage: %.2f",
                doubleSummaryStatistics.getMin(),doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getSum(),doubleSummaryStatistics.getCount(),doubleSummaryStatistics.getAverage()));

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

//        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
//        mojDDV.printStatistics(System.out);

    }
}