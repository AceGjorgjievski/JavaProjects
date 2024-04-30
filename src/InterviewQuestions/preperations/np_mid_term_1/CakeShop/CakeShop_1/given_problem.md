Да се дефинира класа `CakeShopApplication` во која ќе се чуваат информации за сите тековни нарачки на торти и пити.

За класата да се дефинира:

- `CakeShopApplication()` - конструктор

- `int readCakeOrders(InputStream inputStream)` - метод кој од влезен поток на податоци ќе прочита информации за повеќе нарачки.
  Во секој еден ред се наоѓа информација за една нарачка во формат: `orderId item1Name item1Price item2Name item2Price .... itemNName itemNPrice`,
  каде што `orderId` е ID на нарачката, а по неа следуваат паровите нарачан производ и цена. Методот треба да врати цел број кој означува колку точно
  производи се нарачани во рамки на сите нарачки кои се успешно прочитани.

- `void printLongestOrder(OutputStream outputStream)` - метод кој на излезен поток ќе ја испечати нарачката со најголем број на нарачани продукти,
  во формат: `orderId totalOrderItems`, каде `totalOrderItems` е бројот на сите нарачани продукти.

Start code:
```java
public class CakeShopApplicationTest {
  public static void main(String[] args) {
    CakeShopApplication cakeShopApplication = new CakeShopApplication();

    System.out.println("--- READING FROM INPUT STREAM ---");
    System.out.println(cakeShopApplication.readCakeOrders(System.in));

    System.out.println("--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---");
    cakeShopApplication.printLongestOrder(System.out);
  }
}
```

<b>Test case 1:</b>
#### Input:</br>
```
41 P26 215 C26 1400 P37 489 P18 243 C40 215
23 C33 1 C23 148 P44 968 C34 1173
```
#### Output:</br>
```
--- READING FROM INPUT STREAM ---
9
--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---
41 5
```
<b>Test case 2:</b>
#### Input:</br>
```
26 P30 1109 P11 499 P28 1280 C34 1360 C1 449 P16 1304 C5 699 P38 223 C37 1469 C24 630 C32 721 P11 679 P25 1166
0 P29 552 C24 1287 C46 1232 P29 297 C27 984 C13 711 P21 555 C19 1109 C31 431 P37 807 P36 1443 C22 1192 C44 622 C9 878
10 C4 528 P49 556 P35 638
40 C13 27 P20 302 P43 445 P48 787 C22 1215
```
#### Output:</br>
```
--- READING FROM INPUT STREAM ---
35
--- PRINTING LARGEST ORDER TO OUTPUT STREAM ---
0 14
```