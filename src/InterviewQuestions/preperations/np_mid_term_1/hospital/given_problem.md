Во рамки на една болница се развива систем кој ќе дава податоци за пациентите. 
За секој пациент се чуваат информации за `скриен код (long)`,
`име (String)`, `години (int)`, `пол (enum)` и `име на матичен лекар (String)`.

За потребите на системот треба да се дефинира генерички интерфејс `PatientService` кој ќе има еден метод `ArrayList patientsInformation
(ArrayList patients, String filter)`. Методот како влезни аргументи ја добива листата на пациенти за која треба да даде информации, како и
`filter` кој се користи при извлекување на информациите. Интерфејсот `PatientService` ви е даден и за истиот треба да ги пополните само генеричките
параметри.

Дадена ви е класа `HospitalSystem` во која се чува листа од сите пациенти за кои болницата води евиденција.
За класата да се дефинираат соодветните генерички параметри, да се имплементира конструктор `HospitalSystem(ArrayList<?> patients)`,
како и да се комплетира методот `void printResults()`.

Во овој метод потребно е да креирате три конкретни сервиси кои даваат информации за пациентите (со помош на ламбда изрази):

1. Сервис кој ќе ги врати сите пациенти кои имаат матичен лекар со име `filter`.
2. Сервис кој ќе ги врати сите пациенти кои не се постари од 60 години и се од полот `filter`.
3. Сервис кој ќе ги врати сите пациенти со име `filter` сортирани во растечки редослед според нивниот скриен код.

Start code:
```java
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO: Remove all comments and implement the solution. All ??? signs require a generic to be put at that place.
 */

enum Gender {
    FEMALE,
    MALE
}

interface Patient {
    //todo: write methods definitions here
}

interface PatientService???{
        ArrayList???patientsInformation(ArrayList???patients,String filter);
        }

class HospitalSystem???{
//todo: add instance variable(s)

//todo: constructor

public void printResults(){

        //todo: define specific service implementations with lambda expressions
        PatientService???findAllWithDoctor=

        PatientService???countAllWithGenderAndAgeLessThanSixty=

        PatientService???findAllWithNameSorted=

        System.out.println("FIRST SERVICE INFORMATION");
        findAllWithDoctor.patientsInformation(this.patients,"Peter").forEach(System.out::println);

        System.out.println("SECOND SERVICE INFORMATION");
        countAllWithGenderAndAgeLessThanSixty.patientsInformation(this.patients,"FEMALE").forEach(System.out::println);

        System.out.println("THIRD SERVICE INFORMATION");
        findAllWithNameSorted.patientsInformation(this.patients,"Sarah").forEach(System.out::println);
        }
        }

class PatientFromDB1 implements Patient, Comparable<PatientFromDB1> {
    private long code;
    private String name;
    private int age;
    private Gender gender;
    private String doctor;

    public PatientFromDB1(long code, String name, int age, Gender gender, String doctor) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.doctor = doctor;
    }

    public static PatientFromDB1 createPatientFromDB1(String line) {
        String[] parts = line.split("\\s+");
        long code = Long.parseLong(parts[0]);
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        Gender gender = parts[3].equals("FEMALE") ? Gender.FEMALE : Gender.MALE;
        String doctor = parts[4];
        return new PatientFromDB1(code, name, age, gender, doctor);
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getGender() {
        return this.gender.toString();
    }

    @Override
    public String getDoctor() {
        return this.doctor;
    }

    @Override
    public String toString() {
        return "PatientFromDB1{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", doctor='" + doctor + '\'' +
                '}';
    }

    @Override
    public int compareTo(PatientFromDB1 o) {
        return Long.compare(this.code, o.code);
    }
}

class PatientFromDB2 implements Patient, Comparable<PatientFromDB2> {
    private String code;
    private String name;
    private int age;
    private Gender gender;
    private String doctor;
    private String symptom;

    public PatientFromDB2(String code, String name, int age, Gender gender, String doctor, String symptom) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.doctor = doctor;
        this.symptom = symptom;
    }

    public static PatientFromDB2 createPatientFromDB2(String line) {
        String[] parts = line.split("\\s+");
        String code = parts[0];
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        Gender gender = parts[3].equals("FEMALE") ? Gender.FEMALE : Gender.MALE;
        String doctor = parts[4];
        String symptom = parts[5];
        return new PatientFromDB2(code, name, age, gender, doctor, symptom);
    }

    @Override
    public long getCode() {
        return this.code.hashCode();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getGender() {
        return this.gender.toString();
    }

    @Override
    public String getDoctor() {
        return this.doctor;
    }

    @Override
    public String toString() {
        return "PatientFromDB2{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", doctor='" + doctor + '\'' +
                ", symptom='" + symptom + '\'' +
                '}';
    }

    @Override
    public int compareTo(PatientFromDB2 o) {
        return Long.compare(this.getCode(), o.getCode());
    }
}


public class HospitalSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int db1Count = Integer.parseInt(scanner.nextLine());

        ArrayList<PatientFromDB1> patientsFromDB1 = IntStream.range(0, db1Count)
                .mapToObj(i -> PatientFromDB1.createPatientFromDB1(scanner.nextLine()))
                .collect(Collectors.toCollection(ArrayList::new));

        int db2Count = Integer.parseInt(scanner.nextLine());

        ArrayList<PatientFromDB2> patientsFromDB2 = IntStream.range(0, db2Count)
                .mapToObj(i -> PatientFromDB2.createPatientFromDB2(scanner.nextLine()))
                .collect(Collectors.toCollection(ArrayList::new));

        HospitalSystem<PatientFromDB1> hospital1System = new HospitalSystem<>(patientsFromDB1);
        HospitalSystem<PatientFromDB2> hospital2System = new HospitalSystem<>(patientsFromDB2);

        System.out.println("--- FIRST HOSPITAL PATIENTS' INFORMATION ---");
        hospital1System.printResults();

        System.out.println("--- SECOND HOSPITAL PATIENTS' INFORMATION ---");
        hospital2System.printResults();
    }
}

```

<b>Test case 1:</b>
#### Input:</br>
```
6
3123 Sarah 41 FEMALE Pamela
1795 Sarah 23 FEMALE Sarah
1288 Peter 52 MALE Peter
3200 Sarah 88 FEMALE Mark
9042 Peter 26 MALE Jack
5804 Peter 73 MALE Jack
7
defghij Mark 76 MALE Emily Toothache
ghijklm Pamela 89 FEMALE Stefan Headache
mnopqrs Emily 40 FEMALE Peter Headache
cdefghi Peter 15 MALE Joe Headache
ijklmno Joe 27 MALE Sofia Toothache
abcdefg Sarah 95 FEMALE Sarah Fever
ghijklm Peter 2 MALE Emily Headache
```
#### Output:</br>
```
--- FIRST HOSPITAL PATIENTS' INFORMATION ---
FIRST SERVICE INFORMATION
PatientFromDB1{code=1288, name='Peter', age=52, gender=MALE, doctor='Peter'}
SECOND SERVICE INFORMATION
PatientFromDB1{code=3123, name='Sarah', age=41, gender=FEMALE, doctor='Pamela'}
PatientFromDB1{code=1795, name='Sarah', age=23, gender=FEMALE, doctor='Sarah'}
THIRD SERVICE INFORMATION
PatientFromDB1{code=1795, name='Sarah', age=23, gender=FEMALE, doctor='Sarah'}
PatientFromDB1{code=3123, name='Sarah', age=41, gender=FEMALE, doctor='Pamela'}
PatientFromDB1{code=3200, name='Sarah', age=88, gender=FEMALE, doctor='Mark'}
--- SECOND HOSPITAL PATIENTS' INFORMATION ---
FIRST SERVICE INFORMATION
PatientFromDB2{code='mnopqrs', name='Emily', age=40, gender=FEMALE, doctor='Peter', symptom='Headache'}
SECOND SERVICE INFORMATION
PatientFromDB2{code='mnopqrs', name='Emily', age=40, gender=FEMALE, doctor='Peter', symptom='Headache'}
THIRD SERVICE INFORMATION
PatientFromDB2{code='abcdefg', name='Sarah', age=95, gender=FEMALE, doctor='Sarah', symptom='Fever'}
```
<b>Test case 2:</b>
#### Input:</br>
```
20
9658 Stefan 90 MALE Peter
2209 Stefan 0 MALE Sofia
7563 Peter 15 MALE Joe
9586 Peter 67 MALE Mark
2703 Sarah 31 FEMALE Mark
398 Peter 19 MALE Peter
4421 Sofia 4 FEMALE Mark
7193 Sofia 63 FEMALE Sofia
3432 Jack 65 MALE Pamela
866 Jack 72 MALE Sarah
4022 Mark 71 MALE Jack
9588 Mark 43 MALE Mark
6577 Sofia 69 FEMALE Sofia
9635 Peter 17 MALE Peter
9230 Sarah 43 FEMALE Stefan
8169 Sarah 60 FEMALE Peter
9912 Sofia 66 FEMALE Peter
509 Stefan 26 MALE Peter
2599 Stefan 55 MALE Sarah
6926 Sarah 40 FEMALE Emily
25
hijklmn Sarah 27 FEMALE Ana Fever
jklmnop Pamela 6 FEMALE Emily Toothache
hijklmn Peter 97 MALE Sarah Headache
opqrstu Pamela 39 FEMALE Stefan Toothache
bcdefgh Peter 9 MALE Peter Toothache
ghijklm Emily 43 FEMALE Peter Fever
pqrstuv Pamela 1 FEMALE Sarah Fever
hijklmn Peter 11 MALE Peter Headache
klmnopq Peter 92 MALE Joe Toothache
klmnopq Sarah 92 FEMALE Peter Toothache
bcdefgh Peter 22 MALE Sarah Headache
jklmnop Jack 70 MALE Stefan Headache
hijklmn Sofia 65 FEMALE Joe Fever
hijklmn Mark 23 MALE Sofia Toothache
rstuvwx Jack 15 MALE Stefan Toothache
jklmnop Sofia 66 FEMALE Jack Headache
nopqrst Sarah 74 FEMALE Stefan Headache
abcdefg Stefan 54 MALE Emily Fever
ghijklm Jack 81 MALE Ana Headache
nopqrst Stefan 38 MALE Joe Headache
ghijklm Sarah 41 FEMALE Sarah Headache
pqrstuv Mark 51 MALE Peter Fever
ghijklm Pamela 6 FEMALE Pamela Headache
opqrstu Stefan 44 MALE Peter Fever
mnopqrs Mark 25 MALE Peter Toothache
```

#### Output:</br>
```
--- FIRST HOSPITAL PATIENTS' INFORMATION ---
FIRST SERVICE INFORMATION
PatientFromDB1{code=9658, name='Stefan', age=90, gender=MALE, doctor='Peter'}
PatientFromDB1{code=398, name='Peter', age=19, gender=MALE, doctor='Peter'}
PatientFromDB1{code=9635, name='Peter', age=17, gender=MALE, doctor='Peter'}
PatientFromDB1{code=8169, name='Sarah', age=60, gender=FEMALE, doctor='Peter'}
PatientFromDB1{code=9912, name='Sofia', age=66, gender=FEMALE, doctor='Peter'}
PatientFromDB1{code=509, name='Stefan', age=26, gender=MALE, doctor='Peter'}
SECOND SERVICE INFORMATION
PatientFromDB1{code=2703, name='Sarah', age=31, gender=FEMALE, doctor='Mark'}
PatientFromDB1{code=4421, name='Sofia', age=4, gender=FEMALE, doctor='Mark'}
PatientFromDB1{code=9230, name='Sarah', age=43, gender=FEMALE, doctor='Stefan'}
PatientFromDB1{code=6926, name='Sarah', age=40, gender=FEMALE, doctor='Emily'}
THIRD SERVICE INFORMATION
PatientFromDB1{code=2703, name='Sarah', age=31, gender=FEMALE, doctor='Mark'}
PatientFromDB1{code=6926, name='Sarah', age=40, gender=FEMALE, doctor='Emily'}
PatientFromDB1{code=8169, name='Sarah', age=60, gender=FEMALE, doctor='Peter'}
PatientFromDB1{code=9230, name='Sarah', age=43, gender=FEMALE, doctor='Stefan'}
--- SECOND HOSPITAL PATIENTS' INFORMATION ---
FIRST SERVICE INFORMATION
PatientFromDB2{code='bcdefgh', name='Peter', age=9, gender=MALE, doctor='Peter', symptom='Toothache'}
PatientFromDB2{code='ghijklm', name='Emily', age=43, gender=FEMALE, doctor='Peter', symptom='Fever'}
PatientFromDB2{code='hijklmn', name='Peter', age=11, gender=MALE, doctor='Peter', symptom='Headache'}
PatientFromDB2{code='klmnopq', name='Sarah', age=92, gender=FEMALE, doctor='Peter', symptom='Toothache'}
PatientFromDB2{code='pqrstuv', name='Mark', age=51, gender=MALE, doctor='Peter', symptom='Fever'}
PatientFromDB2{code='opqrstu', name='Stefan', age=44, gender=MALE, doctor='Peter', symptom='Fever'}
PatientFromDB2{code='mnopqrs', name='Mark', age=25, gender=MALE, doctor='Peter', symptom='Toothache'}
SECOND SERVICE INFORMATION
PatientFromDB2{code='hijklmn', name='Sarah', age=27, gender=FEMALE, doctor='Ana', symptom='Fever'}
PatientFromDB2{code='jklmnop', name='Pamela', age=6, gender=FEMALE, doctor='Emily', symptom='Toothache'}
PatientFromDB2{code='opqrstu', name='Pamela', age=39, gender=FEMALE, doctor='Stefan', symptom='Toothache'}
PatientFromDB2{code='ghijklm', name='Emily', age=43, gender=FEMALE, doctor='Peter', symptom='Fever'}
PatientFromDB2{code='pqrstuv', name='Pamela', age=1, gender=FEMALE, doctor='Sarah', symptom='Fever'}
PatientFromDB2{code='ghijklm', name='Sarah', age=41, gender=FEMALE, doctor='Sarah', symptom='Headache'}
PatientFromDB2{code='ghijklm', name='Pamela', age=6, gender=FEMALE, doctor='Pamela', symptom='Headache'}
THIRD SERVICE INFORMATION
PatientFromDB2{code='klmnopq', name='Sarah', age=92, gender=FEMALE, doctor='Peter', symptom='Toothache'}
PatientFromDB2{code='ghijklm', name='Sarah', age=41, gender=FEMALE, doctor='Sarah', symptom='Headache'}
PatientFromDB2{code='hijklmn', name='Sarah', age=27, gender=FEMALE, doctor='Ana', symptom='Fever'}
PatientFromDB2{code='nopqrst', name='Sarah', age=74, gender=FEMALE, doctor='Stefan', symptom='Headache'}
```
