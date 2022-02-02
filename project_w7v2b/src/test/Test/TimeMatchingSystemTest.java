package Test;
//import exceptions.UserExceedingException;
import exceptions.IncorrectUserInputException;
import exceptions.NoCalendarException;
import exceptions.NoSuchUserException;
import model.TimeMatchingSystem;
import model.Triplet;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

        import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
        import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeMatchingSystemTest {
    private TimeMatchingSystem tms;

    @BeforeEach
    public void setUp() {
        tms = new TimeMatchingSystem();
    }

    @Test
    public void testLoadSuccess() {
        try {
            String stringInFile = tms.load("Untitled.txt");
            assertEquals("Bye", stringInFile);
        } catch (FileNotFoundException e) {
            fail("Should not have exception.");
        } catch (IOException e) {
            fail("Should not have exception.");
        }
    }

    @Test
    public void testLoadFileNotFound() throws IOException {
        try {
            tms.load("Hello.txt");
        } catch (FileNotFoundException e) {
            //expected
        } catch (IOException e) {
            fail("Should not have exception.");
        }
    }

    @Test
    public void testSaveSuccess() throws IOException {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Bye");
            tms.save("Untitled.txt", lines);
            List<String> fileLines = Files.readAllLines(Paths.get("Untitled.txt"));
            String fromFile = "";
            for (String line : fileLines) {
                fromFile += line;
            }
            assertEquals("Bye", fromFile);
        } catch (FileNotFoundException e) {
            fail("Should not have exception.");
        }
    }

    @Test
    public void testSplitOnSpaceFail() {
        try {
            String testString = null;
            tms.splitOnSpace(testString);
            fail("Should not reach this line.");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    @Test
    public void testEventEntry() throws IncorrectUserInputException {
        User u = new User("Flo");
        tms.allUsers.put("Flo",u);
        tms.eventEntry("Flo","Monday","Lunch","13","14");
        assertEquals("Monday              Lunch               13 ~ 14\n",
                tms.allUsers.get("Flo").peCalendar.weekdays.get(0).getEvent());
    }

    @Test
    public void testEventEntryNoSuchUser() {
        try {
            tms.eventEntry("Fo", "Monday", "Lunch", "13", "14");
            fail("Should not reach this line.");
        } catch (IncorrectUserInputException e) {
            //expected
        }
    }

    @Test
    public void testAddNewUser() throws IncorrectUserInputException {
        tms.addNewUser("Flo");
        assertTrue(tms.allUsers.containsKey("Flo"));
    }

    @Test
    public void testAddNewUserIncorrectInput() {
        try {
            tms.addNewUser("123");
            fail("Should not reach this line.");
        } catch (IncorrectUserInputException e) {
            //expected
        }
    }

    @Test
    public void testPrintAllUserInfo() {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        ArrayList<String> info = tms.printAllUserInfo();
        ArrayList<String> test = new ArrayList<>();
        test.add("Flo");
        test.add("Fo");
        assertEquals(test, info);
    }

    @Test
    public void testDisplaySchedule() throws IncorrectUserInputException, NoCalendarException, NoSuchUserException {
        User u = new User("Fo");
        tms.allUsers.put("Fo",u);
        tms.eventEntry("Fo", "Monday", "Lunch", "13", "14");
        tms.eventEntry("Fo", "Tuesday", "Lunch", "13", "14");
        assertEquals("Day             Event Name          Time \n" +
                "Monday              Lunch               13 ~ 14\n" +
                "Tuesday             Lunch               13 ~ 14\n", tms.displaySchedule("Fo"));
    }

    @Test
    public void testComparedUsersOnAday() throws IncorrectUserInputException {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        tms.eventEntry("Fo", "Monday", "Lunch", "13", "14");
        tms.eventEntry("Flo", "Monday", "Study", "16", "17");
        String meeting = "Monday              Meeting             7 ~ 8\n";
        tms.compareUsersOnADay("Fo","Flo","Monday");
        assertEquals(meeting, tms.allUsers.get("Fo").peCalendar.weekdays.get(1).getEvent());
    }

    @Test
    public void testComparedUsersOnAday2() throws IncorrectUserInputException {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        tms.eventEntry("Fo", "Monday", "Lunch", "16", "17");
        tms.eventEntry("Flo", "Monday", "Study", "12", "13");
        String meeting = "Monday              Meeting             7 ~ 8\n";
        tms.compareUsersOnADay("Fo","Flo","Monday");
        assertEquals(meeting, tms.allUsers.get("Fo").peCalendar.weekdays.get(1).getEvent());
    }

    @Test
    public void testComparedUserOnADayNoSuchUser() {
        try {
            tms.eventEntry("Fo", "Monday", "Lunch", "13", "14");
            tms.eventEntry("Flo", "Monday", "Study", "16", "17");
            tms.compareUsersOnADay("Fo","Flo","Monday");
            fail("Should not reach this line.");
        } catch (IncorrectUserInputException e) {
           //expected
        }
    }

    @Test
    public void testComparedUser1EmptySchedule() throws IncorrectUserInputException {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        tms.eventEntry("Fo", "Monday", "Lunch", "13", "14");
        String meeting = "Monday              Meeting             7 ~ 8\n";
        tms.compareUsersOnADay("Fo","Flo","Monday");
        assertEquals(meeting, tms.allUsers.get("Fo").peCalendar.weekdays.get(1).getEvent());
    }

    @Test
    public void testComparedUser2EmptySchedule() throws IncorrectUserInputException {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        tms.eventEntry("Flo", "Monday", "Lunch", "13", "14");
        String meeting = "Monday              Meeting             7 ~ 8\n";
        tms.compareUsersOnADay("Fo","Flo","Monday");
        assertEquals(meeting, tms.allUsers.get("Flo").peCalendar.weekdays.get(1).getEvent());
    }

    @Test
    public void testComparedTwoEmptySchedule() throws IncorrectUserInputException {
        User u1 = new User("Fo");
        User u2 = new User("Flo");
        tms.allUsers.put("Fo",u1);
        tms.allUsers.put("Flo",u2);
        String meeting = "Monday              Meeting             7 ~ 8\n";
        tms.compareUsersOnADay("Fo","Flo","Monday");
        assertEquals(meeting, tms.allUsers.get("Fo").peCalendar.weekdays.get(0).getEvent());
        assertEquals(meeting, tms.allUsers.get("Flo").peCalendar.weekdays.get(0).getEvent());
    }

}
