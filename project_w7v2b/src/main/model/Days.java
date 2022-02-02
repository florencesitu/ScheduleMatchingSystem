package model;

public class Days extends Weekday {

    public Days(String dayName, String eventName, int startingTime, int endingTime) {
        super(dayName, eventName, startingTime, endingTime);
    }

    //effects: returns the result of the comparison between this and o
    @Override
    public int compareTo(Weekday o) {
        return this.comparedTo(o);
    }
}
