package observer;

import model.Weekday;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<CalendarObserver> observers = new ArrayList<>();

    public void addObserver(CalendarObserver calendarObserver) {
        if (!observers.contains(calendarObserver)) {
            observers.add(calendarObserver);
        }
    }

    public void notifyObservers(Weekday weekday) {
        for (CalendarObserver observer : observers) {
            observer.update(weekday);
        }
    }


}
