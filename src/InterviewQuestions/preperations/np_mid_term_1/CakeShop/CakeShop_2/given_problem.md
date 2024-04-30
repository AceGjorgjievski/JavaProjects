Да се дефинира класа `CakeShopApplication` во која ќе се чуваат информации за сите тековни нарачки на торти и пити.
Дополнително, во класата се чува и информација за најмалиот број на производи кои може да се нарачаат во рамки на една нарачка.

За класата да се дефинира:

- `CakeShopApplication(int minOrderItems)` - конструктор

- `void readCakeOrders(InputStream inputStream)` - метод кој од влезен поток на податоци ќе прочита информации за повеќе нарачки.
  Во секој еден ред се наоѓа информација за една нарачка во формат: `orderId item1Name item1Price item2Name item2Price .... itemNName itemNPrice`,
  каде што `orderId` е ID на нарачката, а по неа следуваат паровите нарачан производ и цена. Дополнително, првата буква од името на производот
  го дава неговиот вид `(C = cake, P = pie)`.
    - Потребно е да се спречи додавање на нарачка која има помалку производи од минимално дозволените.
      Истото треба да се направи со фрлање на исклучок од тип `InvalidOrderException`. Справете се со исклучокот така што нема да се
      попречи вчитувањето на останатите нарачки, а ќе се испечати порака во формат: `The order with id [orderId] has less items than the minimum allowed.`

- `void printAllOrders(OutputStream outputStream)` - метод кој на излезен поток ќе ги испечати информациите за сите нарачки.
  Нарачките треба да бидат сортирани во опаѓачки редослед според сумата на сите нарачани продукти во рамки на нарачката.
  Притоа, потребно е да се внимава, бидејќи има покачување на цената на сите пити, за 50 денари.
  Форматот за печатење на нарачка е: `orderId totalOrderItems totalPies totalCakes totalAmount`.

Start code:
```java
package mk.ukim.finki.av6.monday.starter.task2;

public class CakeShopApplicationTest2 {
    public static void main(String[] args) {
        CakeShopApplication cakeShopApplication = new CakeShopApplication(4);

        System.out.println("--- READING FROM INPUT STREAM ---");
        cakeShopApplication.readCakeOrders(System.in);

        System.out.println("--- PRINTING TO OUTPUT STREAM ---");
        cakeShopApplication.printAllOrders(System.out);
    }
}
```

<b>Test case 1:</b>
#### Input:</br>
```
13 C37 1106 P27 1469 P41 764 P4 394 C19 607 P38 237 P43 673 C14 362
10 C23 931 P10 155 C48 1312 P2 1219 C2 1115 C2 1193 C19 901
35 P24 257 C23 744 C18 1277 P49 696 P41 177 P17 1373 C39 693 C30 561 C6 945
```
#### Output:</br>
```
--- READING FROM INPUT STREAM ---
--- PRINTING TO OUTPUT STREAM ---
10 7 2 5 6926
35 9 4 5 6923
13 8 5 3 5862
```
<b>Test case 2:</b>
#### Input:</br>
```
26
48 C34 755 C5 1149 P32 397 P17 1373 C37 948 P4 738 C49 831 C29 130 C39 1035
41 P44 337 C32 409 C24 601 C22 951 P34 827 P34 737 P5 1459
1 C42 116 P2 131
```

#### Output:</br>
```

--- READING FROM INPUT STREAM ---
The order with id 26 has less items than the minimum allowed.
The order with id 1 has less items than the minimum allowed.
--- PRINTING TO OUTPUT STREAM ---
48 9 3 6 7506
41 7 4 3 5521
```