Да се имплементира класа `MojDDV` која што од влезен тек ќе чита информации за скенирани фискални сметки од страна на еден корисник на
истоимената апликација. Податоците за фискалните сметки се во следниот формат:
`ID item_price1 item_tax_type1 item_price2 item_tax_type2 … item_price-n item_tax_type-n`
На пример: `12334 1789 А 1238 B 1222 V 111 V`

Постојат три типа на данок на додадена вредност и тоа:
- А (18% од вредноста)
- B (5% од вредноста)
- V (0% од вредноста)

Да се имплементираат методите:
- `void readRecords (InputStream inputStream)` - метод којшто ги чита од влезен тек податоците за фискалните сметки.
Доколку е скенирана фискална сметка со износ поголем од 30000 денари потребно е да се фрли исклучок од тип `AmountNotAllowedException`.
Дефинирајте каде ќе се фрла исклучокот, и каде ќе биде фатен, на начин што оваа функција, ќе може да ги прочита сите фискални коишто се скенирани.
Исклучокот треба да испечати порака “`Receipt with amount 31545 is not allowed to be scanned`”.

- `void printSorted (OutputStream outputStream)` - метод којшто на излезен тек ги печати сите скенирани фискални сметки во формат
“`ID SUM_OF_AMOUNTS TAX_RETURN`”, сортирани според повратокот на ДДВ. Доколку повратокот е ист, се сортираат според сумата на купените артикли.

- `void printStatistics (OutputStream outputStream)` - метод којшто на излезен тек печати статистики за повратокот на ДДВ од сите скенирани фискални
сметки во формат min: MIN max: MAX sum: SUM count: COUNT average: AVERAGE

Start code: 
```java
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
```

<b>Test case 1:</b>
#### Input:</br>
```
70876 1585 B 1585 V 247 V 1391 B 1391 V 1649 B 1649 V 548 B 548 V 640 B 640 V 1309 B 1309 V 1486 V 2093 V 106 V 2001 V 361 V
198710 1306 A 1306 B 1306 V 432 V 1222 V 1851 V 390 V 111 A 111 B 111 V 991 V 1611 A 1611 B 1611 V
140819 709 A 709 B 709 V 1628 A 1628 B 1628 V 680 V
584886 411 A 411 B 411 V 699 B 699 V 1571 V 1307 B 1307 V
588253 1528 B 1528 V 1744 B 1744 V 1033 V 412 B 412 V 1221 A 1221 B 1221 V 328 A 328 B 328 V 1301 A 1301 B 1301 V 1778 A 1778 B 1778 V 1651 A 1651 B 1651 V 1937 V 1521 V 2068 B 2068 V
970452 1703 V 1796 B 1796 V 1221 V 885 A 885 B 885 V 183 V 788 B 788 V 1753 B 1753 V 456 V 926 V 1898 V 410 B 410 V 824 B 824 V
51307 2002 B 2002 V 1776 V 2097 B 2097 V 1128 A 1128 B 1128 V 151 A 151 B 151 V
570505 1090 A 1090 B 1090 V 1121 B 1121 V 971 B 971 V 1260 A 1260 B 1260 V 443 V
987793 1436 V 1648 B 1648 V 1197 V 992 B 992 V 528 A 528 B 528 V 739 A 739 B 739 V 750 B 750 V
752690 1626 B 1626 V 1785 A 1785 B 1785 V 1938 V 1733 V 1137 B 1137 V 1832 A 1832 B 1832 V
```
#### Output:</br>
```
===READING RECORDS FROM INPUT STREAM===
Receipt with amount 34832 is not allowed to be scanned
===PRINTING SORTED RECORDS BY TAX RETURNS TO OUTPUT STREAM ===
    584886	      6816	194.83000
     70876	     20538	356.10000
    987793	     13214	460.91000
    970452	     20184	482.10000
     51307	     13811	499.12000
    140819	      7691	537.51000
    570505	     11677	645.10000
    198710	     13970	696.44000
    752690	     20048	970.06000
===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===
min:	194.830
max:	970.060
sum:	4842.170
count:	9
avg:	538.019
```
<b>Test case 2:</b>
#### Input:</br>

```
809156 3346 A 3346 B 3346 V 703 A 703 B 703 V 805 A 805 B 805 V 4521 V 4727 B 4727 V
858522 4909 V 3501 B 3501 V 3771 B 3771 V 4387 V 221 V
883267 1856 B 1856 V 3455 B 3455 V 1627 B 1627 V 4715 A 4715 B 4715 V
418949 4546 B 4546 V 4436 V 3394 V 2023 V 1242 V 1065 B 1065 V
682377 3187 V 3650 B 3650 V 2964 V 1202 B 1202 V 4999 A 4999 B 4999 V
667647 3625 A 3625 B 3625 V 3940 B 3940 V
24379 3946 V 1384 A 1384 B 1384 V 2345 A 2345 B 2345 V 2842 A 2842 B 2842 V 558 B 558 V
585903 4683 V 4066 B 4066 V 4752 A 4752 B 4752 V 3353 A 3353 B 3353 V
137463 4995 A 4995 B 4995 V
525586 379 B 379 V 2522 B 2522 V 3801 V 2882 B 2882 V 1403 A 1403 B 1403 V 3971 A 3971 B 3971 V 3573 V 4216 A 4216 B 4216 V
677280 960 V 1973 A 1973 B 1973 V 3167 B 3167 V 3401 V 979 B 979 V 1929 B 1929 V 4869 A 4869 B 4869 V 4852 A 4852 B 4852 V 122 A 122 B 122 V
997526 470 B 470 V 3820 V
508555 250 V 971 V 4030 A 4030 B 4030 V 3256 V 2672 B 2672 V 1452 B 1452 V 4207 A 4207 B 4207 V 2827 A 2827 B 2827 V 1451 A 1451 B 1451 V
453172 2785 V 3928 V 3156 V 4450 V
180121 2743 V 1590 V 700 V
477609 4512 A 4512 B 4512 V 2631 B 2631 V 2396 B 2396 V 4847 A 4847 B 4847 V 239 B 239 V 3430 V 2835 V
422895 399 V 2380 A 2380 B 2380 V 874 B 874 V 2417 V
888960 2013 V 4117 A 4117 B 4117 V 4819 V 3124 V 4630 V 4818 V 3998 B 3998 V 4603 B 4603 V
39972 2976 V
476283 1932 A 1932 B 1932 V 3478 A 3478 B 3478 V
840433 4743 V
738959 4851 B 4851 V 915 V 2212 B 2212 V 1367 A 1367 B 1367 V 3408 V 1418 B 1418 V
71713 4893 V 2855 V 1389 V 3291 A 3291 B 3291 V 2435 V 3454 A 3454 B 3454 V 2255 B 2255 V 2472 V 3720 V 548 B 548 V
910673 1214 B 1214 V 4054 B 4054 V 1419 A 1419 B 1419 V 624 A 624 B 624 V 1550 V 2359 V 820 A 820 B 820 V 4013 A 4013 B 4013 V
888755 1879 A 1879 B 1879 V 2905 B 2905 V 2955 V 1067 A 1067 B 1067 V 2214 A 2214 B 2214 V
581312 2488 B 2488 V 1069 V 3021 A 3021 B 3021 V 2450 B 2450 V 1024 A 1024 B 1024 V 4096 B 4096 V 3214 B 3214 V 3907 V
440516 2759 B 2759 V
816354 2009 B 2009 V 3732 V 1769 V 4539 V 1006 A 1006 B 1006 V 3726 B 3726 V
818947 2383 V 2928 B 2928 V 2911 V 2833 B 2833 V 837 A 837 B 837 V 2230 B 2230 V 3590 V 1556 V 3505 B 3505 V
574037 4469 A 4469 B 4469 V
253868 5010 V 2567 A 2567 B 2567 V 1153 V 500 A 500 B 500 V 2201 B 2201 V 3930 B 3930 V 4703 V 4923 A 4923 B 4923 V 4489 A 4489 B 4489 V
363916 207 B 207 V 2313 B 2313 V 1126 B 1126 V 618 A 618 B 618 V 314 B 314 V 2860 V 5016 B 5016 V 4460 B 4460 V 4293 V 2892 V
78363 3573 A 3573 B 3573 V 542 A 542 B 542 V 2476 B 2476 V 3086 B 3086 V 1148 B 1148 V 2083 V
231892 4841 A 4841 B 4841 V 2300 B 2300 V 4080 B 4080 V 1384 V 728 B 728 V 4247 V
695326 473 V 820 B 820 V 1867 A 1867 B 1867 V 3295 A 3295 B 3295 V
704311 887 A 887 B 887 V 2431 V 3919 V 2016 B 2016 V 1638 A 1638 B 1638 V
748154 2953 B 2953 V 3680 A 3680 B 3680 V 4304 B 4304 V 3757 A 3757 B 3757 V 2418 V
902558 4493 V
539837 1846 A 1846 B 1846 V 4730 B 4730 V
713375 1006 B 1006 V 1534 V 3419 V 3384 V 3413 B 3413 V 3235 B 3235 V
353694 3505 B 3505 V 1514 B 1514 V 761 V 4756 V 726 B 726 V 4835 B 4835 V 1503 A 1503 B 1503 V 4878 A 4878 B 4878 V
248492 4486 B 4486 V 3979 V 4461 V 3956 B 3956 V 4117 A 4117 B 4117 V 3697 V
694001 4946 A 4946 B 4946 V
573704 3578 A 3578 B 3578 V 5017 V 2354 V 1773 B 1773 V 3814 A 3814 B 3814 V 1265 B 1265 V 2311 B 2311 V 1555 A 1555 B 1555 V 2876 B 2876 V
430965 2230 B 2230 V 2230 V 895 A 895 B 895 V 2051 B 2051 V 3861 V 4841 B 4841 V 4064 V 3820 V 1182 A 1182 B 1182 V
650738 4376 A 4376 B 4376 V 4294 B 4294 V
22985 3561 B 3561 V 2429 V 3671 B 3671 V 1689 B 1689 V 485 B 485 V 401 B 401 V
139374 1559 A 1559 B 1559 V 4915 V 2424 B 2424 V 744 B 744 V 3973 A 3973 B 3973 V 3392 V 3696 B 3696 V 4898 V 2559 V 660 A 660 B 660 V
300057 1056 A 1056 B 1056 V 3066 A 3066 B 3066 V 3806 V 523 V 1143 V 4123 A 4123 B 4123 V 5052 A 5052 B 5052 V
238116 4321 B 4321 V 1852 A 1852 B 1852 V 3874 V 4289 A 4289 B 4289 V 1056 A 1056 B 1056 V 169 B 169 V 815 V
335691 2215 A 2215 B 2215 V 3724 A 3724 B 3724 V 3917 V 4113 B 4113 V 702 V
```

#### Output:</br>
```
===READING RECORDS FROM INPUT STREAM===
Receipt with amount 30852 is not allowed to be scanned
Receipt with amount 37130 is not allowed to be scanned
Receipt with amount 47710 is not allowed to be scanned
Receipt with amount 51959 is not allowed to be scanned
Receipt with amount 50270 is not allowed to be scanned
Receipt with amount 44874 is not allowed to be scanned
Receipt with amount 48957 is not allowed to be scanned
Receipt with amount 43605 is not allowed to be scanned
Receipt with amount 35073 is not allowed to be scanned
Receipt with amount 41607 is not allowed to be scanned
Receipt with amount 35943 is not allowed to be scanned
Receipt with amount 60565 is not allowed to be scanned
Receipt with amount 38771 is not allowed to be scanned
Receipt with amount 34370 is not allowed to be scanned
Receipt with amount 39243 is not allowed to be scanned
Receipt with amount 45820 is not allowed to be scanned
Receipt with amount 41372 is not allowed to be scanned
Receipt with amount 50662 is not allowed to be scanned
Receipt with amount 38450 is not allowed to be scanned
Receipt with amount 48068 is not allowed to be scanned
Receipt with amount 45363 is not allowed to be scanned
Receipt with amount 35260 is not allowed to be scanned
Receipt with amount 30662 is not allowed to be scanned
===PRINTING SORTED RECORDS BY TAX RETURNS TO OUTPUT STREAM ===
     39972	      2976	0.00000
    902558	      4493	0.00000
    840433	      4743	0.00000
    180121	      5033	0.00000
    453172	     14319	0.00000
    997526	      4760	23.50000
    440516	      5518	137.95000
    418949	     22317	280.55000
    858522	     24061	363.60000
    713375	     23645	382.70000
     22985	     22043	490.35000
    816354	     24528	518.13000
    422895	     11704	591.10000
    539837	     14998	661.08000
    704311	     17957	681.55000
    738959	     25386	738.46000
    574037	     13407	1027.87000
    667647	     18755	1030.75000
    694001	     14838	1137.58000
    137463	     14985	1148.85000
    650738	     21716	1221.18000
    695326	     17599	1228.26000
    476283	     16230	1244.30000
     78363	     27848	1281.95000
    888755	     24245	1332.05000
    809156	     28537	1352.77000
    883267	     28021	1431.35000
     24379	     24775	1539.23000
===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===
min:	0.000
max:	1539.230
sum:	19845.110
count:	28
avg:	708.754
```
