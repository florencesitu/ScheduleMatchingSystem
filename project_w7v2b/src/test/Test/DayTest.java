package Test;

import model.Days;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Days day1;
    private Days day2;

    @BeforeEach
    public void setUp() {
        day1 = new Days("Monday","Lunch",13,14);
    }

    @Test
    public void testAppendDaySchedule() {
        day1.setEvent("Breakfast",10,12);
        String day1Schedule = "Monday              Lunch               13 ~ 14\n" +
                "Monday              Breakfast           10 ~ 12\n";
        assertEquals(day1Schedule,day1.getEvent());
    }

    @Test
    public void testDaySchedule() {
        day1.setEvent("Study",10,12);
        String day1Schedule = "Monday              Lunch               13 ~ 14\n" +
                "Monday              Study               10 ~ 12\n";
        assertEquals(day1Schedule,day1.getEvent());
    }

    @Test
    public void testGetDayName() {
        assertEquals("Monday",day1.getDayName());
    }

    @Test
    public void testGetStartingTime() {
        day1.setStartingTime(1);
        assertEquals(1,day1.getStartingTime());
    }

    @Test
    public void testGetEndingTime() {
        day1.setEndingTime(2);
        assertEquals(2,day1.getEndingTime());
    }

    @Test
    public void testComparedTo() {
        day2 = new Days("Monday","Breakfast",8,9);
        assertEquals(1,day1.compareTo(day2));
    }
}
