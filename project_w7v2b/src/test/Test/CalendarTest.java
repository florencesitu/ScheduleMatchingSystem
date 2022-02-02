package Test;

import exceptions.IncorrectUserInputException;
import model.Calendar;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {
    private Calendar calendar;

    @BeforeEach
    public void setUp() {
        calendar = new Calendar();
    }

    @Test
    public void testAddEvent() throws IncorrectUserInputException {
        calendar.addEvent("Monday", "Lunch",1,2);
        calendar.addEvent("Tuesday", "Lunch",1,2);
        calendar.addEvent("Wednesday", "Lunch",1,2);
        calendar.addEvent("Thursday", "Lunch",1,2);
        calendar.addEvent("Friday", "Lunch",1,2);
        calendar.addEvent("Saturday", "Lunch",1,2);
        calendar.addEvent("Sunday", "Lunch",1,2);
        assertEquals("Monday              Lunch               1 ~ 2\n" +
                "Tuesday             Lunch               1 ~ 2\n" +
                "Wednesday           Lunch               1 ~ 2\n" +
                "Thursday            Lunch               1 ~ 2\n" +
                "Friday              Lunch               1 ~ 2\n" +
                "Saturday            Lunch               1 ~ 2\n" +
                "Sunday              Lunch               1 ~ 2\n", calendar.getCalendar());
    }

//    @Test
//    public void testAddUser() {
//        User u = new User("Amy");
//        calendar.addUser(u);
//        String userName  = calendar.userList.get(0).getName();
//        assertEquals("Amy",userName);
//    }

    @Test
    public void testToString() {
        String test = calendar.toString();
        assertEquals("Calendar{weekdays=[], userList=[]}",test);
    }

}
