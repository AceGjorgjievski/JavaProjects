package InterviewQuestions.preperations.np_mid_term_1.DDV;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class AmountNotAllowedException extends Exception {
        public AmountNotAllowedException(int totalSum) {
                super(String.format("Receipt with amount %d is not allowed to be scanned", totalSum));
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

        public void setTaxType(String taxType) {
                if(taxType.equals("A")) {
                        this.taxType = TaxType.A;
                } else if(taxType.equals("B")) {
                        this.taxType = TaxType.B;
                } else {
                        this.taxType = TaxType.V;
                }
        }

        public float getCalculatedTax(){
                if(this.taxType.equals(TaxType.A)) {
                        return this.price * 0.18f;
                } else if(this.taxType.equals(TaxType.B)) {
                        return this.price * 0.05f;
                } else {
                        return 0;
                }
        }
}

class Receipt implements Comparable<Receipt>{
        private int id;
        private List<Item> items;

        public Receipt() {
                this.id = -1;
                this.items = new ArrayList<>();
        }

        public Receipt(int id, List<Item> items) {
                this.id = id;
                this.items = items;
        }

        public static Receipt createReceipt(String line) throws AmountNotAllowedException {
                String [] parts = line.split("\\s++");
                int receiptId = Integer.parseInt(parts[0]);
                List<Item> receiptItems = new ArrayList<>();

                Arrays.stream(parts)
                        .skip(1)
                        .forEach(part -> {
                                if(Character.isDigit(part.charAt(0))) {
                                        receiptItems.add(new Item(Integer.parseInt(part)));
                                } else {
                                        receiptItems.get(receiptItems.size() - 1).setTaxType(part);
                                }
                        });

                int totalSum = calculateTotalPrice(receiptItems);
                if(totalSum > 30000)
                        throw new AmountNotAllowedException(totalSum);

                return new Receipt(receiptId, receiptItems);
        }

        public List<Item> getItems() {
                return items;
        }

        public static int calculateTotalPrice(List<Item> items) {
                return items.stream()
                        .mapToInt(item -> item.getPrice())
                        .sum();
        }

        @Override
        public String toString() {
                return String.format("%10d\t%10d\t%5.5f", id, totalAmount(), taxReturns());
        }

        private int totalAmount() {
                return this.items.stream().mapToInt(item -> item.getPrice()).sum();
        }

        public double taxReturns() {
                return this.items.stream()
                        .mapToDouble(item -> item.getCalculatedTax())
                        .sum();
        }

        @Override
        public int compareTo(Receipt other) {
                return Comparator.comparing(Receipt::taxReturns)
                        .thenComparing(Receipt::totalAmount)
                        .compare(this, other);
        }
}

class MojDDV {
        private List<Receipt> receiptList;

        public void readRecords(InputStream in) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                this.receiptList = br.lines()
                        .map(line -> {
                                try {
                                        return Receipt.createReceipt(line);
                                } catch (AmountNotAllowedException e) {
                                        System.out.println(e.getMessage());
                                        return null;
                                }
                        })
                        .collect(Collectors.toList());
                this.receiptList = this.receiptList.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        }

        public void printSorted(PrintStream out) {
                PrintWriter pw = new PrintWriter(out);
                this.receiptList.stream().sorted().forEach(receipt -> pw.println(receipt.toString()));
                pw.flush();

        }

        public void printStatistics(PrintStream out) {
                PrintWriter pw = new PrintWriter(out);
                DoubleSummaryStatistics summaryStatistics = this.receiptList
                        .stream().mapToDouble(Receipt::taxReturns).summaryStatistics();

                pw.println(String.format("min: %.2f, max: %.2f, sum: %.2f, count: %d, average: %.2f",
                        summaryStatistics.getMin(),
                        summaryStatistics.getMax(),
                        summaryStatistics.getSum(),
                        summaryStatistics.getCount(),
                        summaryStatistics.getAverage()
                        ));
                pw.flush();
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
        mojDDV.printStatistics(System.out);

    }
}
