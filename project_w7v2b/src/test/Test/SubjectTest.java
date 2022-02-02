package Test;

import model.Weekday;
import observer.CalendarObserver;
import observer.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubjectTest {

    private Subject subject;
    private CalendarObserver calendarObserver;

    @BeforeEach
    public void setUp() {
        subject = new Subject();
    }

//    @Test
//    public void testAddObserver() {
//        subject.addObserver(calendarObserver);
//        subject.notifyObservers();
//    }

}
