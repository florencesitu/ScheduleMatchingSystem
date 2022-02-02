package model;

import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends Subject {

    public ArrayList<Weekday> weekdays = new ArrayList<>();
    public List<User> userList;

    public Calendar() {
        userList = new ArrayList<>();
    }

    //modifies: weekdays
    //effects: adds m into weekdays Arraylist
    public void addEvent(String dayChosen, String eventName1, int startingTime1, int endingTime1) {
        Weekday m = new Days(dayChosen, eventName1, startingTime1, endingTime1);
        weekdays.add(m);
    }

    //effects: returns a full calendar for user
    public String getCalendar() {
        String fullCalendar = "";
        for (int i = 0; i < weekdays.size(); i++) {
            fullCalendar += weekdays.get(i).getEvent();
        }
        return fullCalendar;
    }

    //effects: returns this in a string form
    @Override
    public String toString() {
        return "Calendar{" + "weekdays=" + weekdays + ", userList=" + userList + '}';
    }
}
