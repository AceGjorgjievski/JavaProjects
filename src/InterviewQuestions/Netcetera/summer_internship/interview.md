# Interview Preparation Notes

## Project Overview

- Напочеток, ме прашаа да одберам некој проект што сум го работел, да споделам како сум го направил и сл. (во мојот случај беше е-продавница)

## Detailed Questions

### Архитектура и Дизајн
- слоевита архитектура
- dependency injection
- како Spring ги наоѓа анотациите во самата апликација
- @SpringBootApplication, @ComponentScan, @Bean
- Spring IOC container
- DispatcherServlet

### Релации во JPA
- да дадам пример за некоја @OneToMany/@ManyToOne релација и каде се чува надворешниот клуч кога ќе се креираат табелите (во која табела)
- кога имаме @ManyToMany релација и се креира трета табела (надворешни клучеви од самите ентитети), кој од клучевите е примарен? (двата)
- дали може во @ManyToMany релација при креирање на трета табела да има вештaчки примарен клуч

### REST и HTTP
- да објаснам за HTTP, структура на HTTP
- како може да се заштити барање, HTTPS
- ако имаме апликација за облеки, како ќе изгледа барањето ако даден корисник сака да побара машка облека, каде ќе се прати таа информација? (преку query param e.g. GET `http://localhost:8080/clothes?type=male`)

### ООП Концепти
- како може една класа да биде абстрактна (со додавање на клучниот збор abstract e.g. `abstract class Animal`)
- ако имаме класа А која што е абтрактна и има еден метод што е абстрактен, имаме класа B што наследува од А, што треба да се направи за да не се имплементира тој абстрактен метод? (и В класата да стане абстрактна)
- ако имаме абстрактна класа А што има само еден статичен метод `public static void staticMethod()`, имаме класа В што наследува од класа А, дали може да се препокрие овој статичен метод? (не бидејќи статичните методи припаѓаат на ниво на самата класа, а не на некоја инстанца од објект)
- SOLID принципи

### Структури на Податоци
- кои структури познавам, да набројам некои?
- што е разлика помеѓу низа и листа?
- комплексност за пронаоѓање на елемент во низа/листа
- разлика помеѓу stack и queue
- што претставува set и што е различно од листа?
- што претставува мапа
- какви видови на мапи постојат
- отворени / затворени кофички / колзии
- hashCode()
- што претставува TreeMap<> и што е уникатно за неа (сортирани според клучот и мора override на compareTo методот во „клуч“ класата („клуч“ класата мора implements Comparable<Class>))

### Задача

```java
/*
String input = "bananas"
String text = "ana"

true/false дали го има 'ana' во 'ananas', 
односно дали го има text во input
 */

*/
static boolean matching(String input, String text) {
    int textLength = text.length();
    int counter = 0;
    //bananas
    //ana
    for(int i=0; i<input.lenght(); i++) {
        if(input.charAt(i) == text.charAt(counter)) {
            counter++;
        } else {
            counter = 0;
        }
        if(textLength == counter) {
            return true;
        }
    }
    return false;
}


```
### Корисни линкови за подготовка

Некои дефиниции/забелешки што имам собирано низ студиите:
- https://github.com/AceGjorgjievski/JavaProjects/blob/master/src/mk/ukim/finki/np/README.md
- https://github.com/AceGjorgjievski/Web-Programming-FCSE/blob/master/wp-definicii.txt
- https://github.com/AceGjorgjievski/EMT-labs/blob/main/Books/emt.md


SOLID Design Principles:
- https://www.youtube.com/watch?v=HoA6aZPR5K0 (p.s. овој канал на јутјуб е многу добар)

Spring boot questions:
- https://www.youtube.com/watch?v=CXTiwkZVoZI
- https://www.youtube.com/watch?v=DiBbF37XArY
- https://www.youtube.com/watch?v=1wqmc1EqHOY

DispatcherServlet
- https://www.youtube.com/watch?v=SaKiouvtZ6o

Spring Beans and Spring Container
- https://www.youtube.com/watch?v=aS9SQITRocc

