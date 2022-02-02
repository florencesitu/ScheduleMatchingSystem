package Test;

import model.Calendar;
import model.User;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Sam");
    }

    @Test
    public void testGetName() {
        assertEquals("Sam", user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("Amy");
        assertEquals("Amy", user.getName());
    }

    @Test
    public void testGetUserID() {
        assertEquals(1, user.getUserID());
    }

    @Test
    public void testSetUserID() {
        user.setUserID();
        assertEquals(2, user.getUserID());
    }

    @Test
    public void testAddEvent() {
        user.addEvent("Monday", "Lunch", 1, 2);
        user.peCalendar.getCalendar();
    }

    @Test
    public void testAddUserSuccess() {
        User u = new User("Amy");
        assertTrue(user.addUser(u));
    }

    @Test
    public void testAddUserFail() {
        for (int i = 0; i < 10; i++) {
            user.addUser(user);
        }
        assertFalse(user.addUser(user));
    }

    //fix bug here
//    @Test
//    public void testAddCalendar() {
//        Calendar c = new Calendar();
//        c.addEvent("Monday", "Lunch", 1, 2);
//        user.addCalendar(c);
//        String test = user.peCalendar.getCalendar();
//        assertEquals("", test);
//    }

//    @Test
//    public void testUpdate() {
//        user.update();
//    }

    @Test
    public void testCalendarIsNull() {
        User newUser = new User("Rainy");
        newUser.peCalendar = null;
        assertTrue(newUser.calendarIsNull());
    }

}
