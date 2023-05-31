package mk.ukim.finki.sqt.labs.lab_5;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ConferenceTest {


    @Test
    public void calculatePriceForOneStudent() {
        Conference conference = new Conference(1);
        Student student = new Student("Ace", "Gjorgjievski", Course.EMT, 22);
        conference.addAttendeeToConference(student);
        double price = conference.calculateTotalPricePaid();
        assertEquals((1 - Conference.EMT_DISCOUNT) * Conference.TICKET_PRICE, price, 0);
    }

    @Test
    public void calculatePriceForTwoStudents() {
        Conference conference = new Conference(2);
        Student student1 = new Student("Name1", "Surname1", Course.EMT, 22);
        Student student2 = new Student("Name2", "Surname2", Course.WEB, 22);
        conference.addAttendeeToConference(student1);
        conference.addAttendeeToConference(student2);
        double price = conference.calculateTotalPricePaid();
        double emt_price = (1 - Conference.EMT_DISCOUNT) * Conference.TICKET_PRICE;
        double web_price = (1 - Conference.WEB_DISCOUNT) * Conference.TICKET_PRICE;
        assertEquals(emt_price + web_price, price, 0);
    }

    @Test
    public void calculatePriceForThreeStudents() {
        Conference conference = new Conference(3);
        Student student1 = new Student("Name1", "Surname1", Course.EMT, 22);
        Student student2 = new Student("Name2", "Surname2", Course.WEB, 22);
        Student student3 = new Student("Name3", "Surname3", Course.OS, 22);
        conference.addAttendeeToConference(student1);
        conference.addAttendeeToConference(student2);
        conference.addAttendeeToConference(student3);
        double price = conference.calculateTotalPricePaid();
        double emt_price = (1 - Conference.EMT_DISCOUNT) * Conference.TICKET_PRICE;
        double web_price = (1 - Conference.WEB_DISCOUNT) * Conference.TICKET_PRICE;
        double os_price = Conference.TICKET_PRICE;
        assertEquals(emt_price + web_price + os_price, price, 0);
    }

    @Test
    public void calculatePriceForThreeStudentsWithLessCapacity() {
        Conference conference = new Conference(0);
        Student student1 = new Student("Name1", "Surname1", Course.EMT, 22);
        Student student2 = new Student("Name2", "Surname2", Course.WEB, 22);
        Student student3 = new Student("Name3", "Surname3", Course.OS, 22);
        conference.addAttendeeToConference(student1);
        conference.addAttendeeToConference(student2);
        conference.addAttendeeToConference(student3);
        double price = conference.calculateTotalPricePaid();
        double emt_price = (1 - Conference.EMT_DISCOUNT) * Conference.TICKET_PRICE;
        double web_price = (1 - Conference.WEB_DISCOUNT) * Conference.TICKET_PRICE;
        double os_price = Conference.TICKET_PRICE;
        assertEquals(emt_price + web_price + os_price, price, 0);
    }

    @Test
    public void testForTripleCapacityForAttendeesSize() {
        Conference conference = new Conference(1);
        Random random = new Random();
        for(int i=0; i<10000; i++) {
            int indexForPickingRandomCourse = random.nextInt(Course.values().length);
            Student student = new Student(
                    "Name"+(i+1),
                    "Surname"+(i+1),
                    Course.values()[indexForPickingRandomCourse],
                    22);
            conference.addAttendeeToConference(student);
        }
        assertEquals(6561, conference.getAttendees().size(),0);
    }

    @Test
    public void testForTripleCapacityForCapacity() {
        Conference conference = new Conference(1);
        Random random = new Random();
        for(int i=0; i<10000; i++) {
            int indexForPickingRandomCourse = random.nextInt(Course.values().length);
            Student student = new Student(
                    "Name"+(i+1),
                    "Surname"+(i+1),
                    Course.values()[indexForPickingRandomCourse],
                    22);
            conference.addAttendeeToConference(student);
        }
        assertEquals(6561, conference.getCapacity(),0);
    }
}