package mk.ukim.finki.np.labs.lab2;

import java.util.Arrays;

public class Faculty {
    private String name;
    private Student [] students;
    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }


    public int countStudentsFromCity(String cityName){
        int counter = 0;
        for(int i=0; i<students.length; i++){
            if(students[i].getCity().equals(cityName)){
                counter++;
            }
        }
        return counter;
    }

    public Student getStudent(long index){
        return Arrays.stream(this.students)
                .filter(student -> student.getIndex() == index)
                .findFirst().orElse(null);
    }

    public double getAverageNumberOfContacts(){
        int sum = 0;
        for(int i=0; i<students.length; i++){
            sum+=students[i].numberOfContact();
        }
        return (1.0* sum/students.length);
    }

    public Student getStudentWithMostContacts(){
        int ind = 0;
        for(int i=1; i<students.length; i++){
            if(students[i].numberOfContact() > students[ind].numberOfContact()){
                ind = i;
            } else if(students[i].numberOfContact() == students[ind].numberOfContact()){
                if(students[i].getIndex() > students[ind].getIndex()){
                    ind = i;
                }
            }
        }
        return new Student(students[ind]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"fakultet\":\"").append(name).append("\", \"studenti\":[");
        for (int i=0; i<students.length; ++i){
            sb.append(students[i].toString());
            if (i+1 != students.length)
                sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
