package observer;

import model.Weekday;

public interface CalendarObserver {
    void update(Weekday weekday);
}
