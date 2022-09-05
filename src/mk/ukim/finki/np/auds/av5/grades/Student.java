package mk.ukim.finki.np.auds.av5.grades;

public class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;
    private int exam1;
    private int exam2;
    private int exam3;
    private char grade;

    public Student(String firstName, String lastName, int exam1, int exam2, int exam3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
        setGrade();
    }

    public static Student createStudent(String line){
        String [] parts = line.split(":");
        return new Student(parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]));
    }

    public char getGrade(){
        return grade;
    }

    public double calculatePoints(){
        return (exam1*0.25 + exam2*0.3 + exam3*0.45);
    }

    public void setGrade(){
        double total = calculatePoints();
        if(total >=90) this.grade = 'A';
        else if(total >=80) this.grade = 'B';
        else if(total >=70) this.grade = 'C';
        else if(total >=60) this.grade = 'D';
        else if(total >=50) this.grade = 'E';
        else this.grade = 'F';
    }

    @Override
    public String toString() {
        return String.format("%s %s %c",lastName,firstName,grade);
    }

    public String detailPrint(){
        return String.format("%s %s %d %d %d %.2f %c",
                lastName,firstName,exam1,exam2,exam3,calculatePoints(),grade);
    }

    @Override
    public int compareTo(Student other) {
        return Character.compare(this.grade,other.grade);
    }
}