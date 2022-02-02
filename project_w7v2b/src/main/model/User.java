package model;

import observer.CalendarObserver;

public class User implements CalendarObserver {
    private String name;
    private int userID = 0;
    private static final int maxCount = 10;
    private User[] users = new User[maxCount];
    private int usersCount = 0;
    public Calendar peCalendar;

    public User(String name) {
        setUserID();
        setName(name);
        createPersonalCalendar();
    }

    //effects: return the value of name
    public String getName() {
        return name;
    }

    //modifies: this
    //effects: updates this.name to name
    public void setName(String name) {
        this.name = name;
    }

    //effects: return the value of userID
    public int getUserID() {
        return userID;
    }

    //modifies: this
    //effects: updates the userID to its original value + 1
    public void setUserID() {
        this.userID++;
    }

    //modifies: users
    //effects: add new user if usersCount is less than maxCount and return true
    //else return false
    public boolean addUser(User user) {
        if (usersCount < maxCount) {
            users[usersCount++] = user;
            return true;
        } else {
            System.out.println("System is full. Can't add more users.");
            return false;
        }
    }

    public void createPersonalCalendar() {
        this.peCalendar = new Calendar();
    }

    //effects: return true if peCalendar is null, else return false.
    public boolean calendarIsNull() {
        if (this.peCalendar == null) {
            return true;
        }
        return false;
    }

    //modifies: peCalendar
    //effects: add new event into peCalendar
    public void addEvent(String dayChosen, String eventName1, int startingTime1, int endingTime1) {
        this.peCalendar.addEvent(dayChosen, eventName1, startingTime1, endingTime1);
    }

    //effects: notifies user of a new update
    @Override
    public void update(Weekday weekday) {
        System.out.println("Someone has added event into Calendar " + weekday);
    }

}
