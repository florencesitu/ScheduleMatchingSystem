package model;

public abstract class Weekday implements Comparable<Weekday> {
    protected String schedule = "";
    protected String eventName;
    protected int startingTime;
    protected int endingTime;
    protected String dayName;

    public Weekday(String dayName, String eventName, int startingTime, int endingTime) {
        this.dayName = dayName;
        this.eventName = eventName;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        setEvent(eventName, startingTime, endingTime);
    }

    public String getDayName() {
        return dayName;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public String getEvent() {
        return schedule;
    }

    //modifies: mondaySchedule
    //effects: updates the String mondaySchedule
    public void setEvent(String eventName, int startingTime, int endingTime) {
        schedule += String.format("%-19s %-19s %d ~ %d\n", dayName, eventName, startingTime, endingTime);
    }

    //effects: compares and returns the result of comparison between this and w2
    public int comparedTo(Weekday w2) {
        Integer c1 = this.startingTime;
        Integer c2 = w2.startingTime;
        return c1.compareTo(c2);
    }

}
