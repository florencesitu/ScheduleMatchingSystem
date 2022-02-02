package model;

import exceptions.IncorrectUserInputException;
import exceptions.NoCalendarException;
import exceptions.NoSuchUserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class TimeMatchingSystem implements Loadable, Saveable {
//    public List<String> lines = new ArrayList<>();
    public static Map<String, User> allUsers;
    public static ArrayList<Triplet<String,Integer,Integer>> combinedBusy;
    private  static int start;
    private static int end;

    public TimeMatchingSystem() {
        allUsers = new HashMap<>();
        combinedBusy = new ArrayList<>();
    }

    //effects: if the string is not null returns a new ArrayList after splitting it when every \n appears,
    //else throw an IllegalAregumentException
    public ArrayList<String> splitOnSpace(String line) {
        if (line == null) {
            throw new IllegalArgumentException("line is null");
        }
        String[] splits = line.split("\n");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //modifies: linesFromFile
    //effects: return the previous data in file, throw exception if no such file is found
    public String load(String filename) throws IOException {
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(filename));
            String linesFromFile = "";
            for (String line : fileLines) {
                System.out.print(line + "\n");
                linesFromFile += line;
            }
            return linesFromFile;
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException();
        }
    }

    //modifies: linesFromFile
    //effects: return a new linesFromFile after data is added
    public String save(String filename, List<String> lines) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
        String linesInFile = "";
        for (String line : lines) {
            for (int i = 0; i < lines.size(); i++) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                System.out.println(partsOfLine.get(i) + "\n");
            }
            writer.println(line);
            linesInFile += line;
        }
        writer.close();
        return linesInFile;
    }

    //modifies: allUsers, user
    //effects: if allUsers contains the key u, add a new event in the user's schedule,
    //else throw a new Exception
    public static void eventEntry(String u, String day, String event, String start, String end)
            throws IncorrectUserInputException {
        int startingTime = Integer.parseInt(start);
        int endingTime = Integer.parseInt(end);
        if (allUsers.containsKey(u)) {
            User user = allUsers.get(u);
            user.addEvent(day, event, startingTime, endingTime);
        } else {
            throw new IncorrectUserInputException();
        }
    }

    //modifies: allUsers
    //effects: if user input is incorrect, throw a new exception
    //else, add new user into allUsers
    public static void addNewUser(String name) throws IncorrectUserInputException {
        System.out.println(name);
        if (Character.isDigit(name.charAt(0))) {
            throw new IncorrectUserInputException();
        } else {
            User user = new User(name);
            allUsers.put(name, user);
        }
    }

    //modifies: info
    //effects: returns an arraylist containing all user names
    public static ArrayList<String> printAllUserInfo() {
        ArrayList<String> info = new ArrayList<>();
        for (Map.Entry<String, User> entry : allUsers.entrySet()) {
            info.add(entry.getValue().getName());
        }
        return info;
    }

    //effects: if allUsers doesn't contain name, throw a new exception
    //if allUsers contains the name, and the calendar belong to that name is not null, return the fullschedule
    //belong to that name
    //else throw a new no calendar exception
    public static String displaySchedule(String name) throws NoCalendarException, NoSuchUserException {
        if (!(allUsers.containsKey(name))) {
            throw new NoSuchUserException();
        }
        if (!(allUsers.get(name).peCalendar.weekdays == null)) {
            String fullSchedule = "Day             Event Name          Time \n";
            for (int i = 0; i < allUsers.get(name).peCalendar.weekdays.size(); i++) {
                fullSchedule += allUsers.get(name).peCalendar.weekdays.get(i).getEvent();
            }
            return fullSchedule;
        } else {
            throw new NoCalendarException();
        }
    }

    //modifies: allUsers, u1, u2
    //effects: if both users exists in allUsers, call combineBusyTime and create a meeting schedule for both users
    //else throw a new incorrect user input exception
    public static void compareUsersOnADay(String u1, String u2, String day) throws IncorrectUserInputException {
        if (allUsers.containsKey(u1) && allUsers.containsKey(u2)) {
            ArrayList<Weekday> busyTimes1 = sortScheduleByDay(u1, day);
            ArrayList<Weekday> busyTimes2 = sortScheduleByDay(u2, day);
            Collections.sort(busyTimes1);
            Collections.sort(busyTimes2);
            if (!busyTimes1.isEmpty() && !busyTimes2.isEmpty()) {
                combineBusyTime(busyTimes1,busyTimes2);
            } else {
                checkForEmptyness(busyTimes1,busyTimes2);
            }
            Triplet t = createMeeting();
            allUsers.get(u1).peCalendar.addEvent((String)t.getFirst(),"Meeting",(int)t.getSecond(),(int)t.getThird());
            allUsers.get(u2).peCalendar.addEvent((String)t.getFirst(),"Meeting",(int)t.getSecond(),(int)t.getThird());
        } else {
            throw new IncorrectUserInputException();
        }
    }

    //modifies: choosenDayList
    //effects: return a new arraylist containing schedule only on chosen day
    public static ArrayList<Weekday> sortScheduleByDay(String u, String day) {
        ArrayList<Weekday> choosenDayList  = new ArrayList<>();
        for (int i = 0; i < allUsers.get(u).peCalendar.weekdays.size(); i++) {
            if (allUsers.get(u).peCalendar.weekdays.get(i).getDayName().equals(day)) {
                choosenDayList.add(allUsers.get(u).peCalendar.weekdays.get(i));
            }
        }
        return choosenDayList;
    }

    public static void checkForEmptyness(ArrayList<Weekday> bt1, ArrayList<Weekday> bt2) {
        if ((bt1.isEmpty()) && (bt2.isEmpty())) {
            combinedBusy.add(new Triplet<>("Monday",0, 0));
        } else if (bt1.isEmpty() && !bt2.isEmpty()) {
            for (int i  = 0; i < bt2.size(); i++) {
                String day2 = bt2.get(i).getDayName();
                int start2 = bt2.get(i).getStartingTime();
                int end2 = bt2.get(i).getStartingTime();
                combinedBusy.add(new Triplet<>(day2,start2,end2));
            }
        } else if (!bt1.isEmpty() && bt2.isEmpty()) {
            for (int i = 0; i < bt1.size(); i++) {
                String day1 = bt1.get(i).getDayName();
                int start1 = bt1.get(i).getStartingTime();
                int end1 = bt1.get(i).getStartingTime();
                combinedBusy.add(new Triplet<>(day1, start1, end1));
            }
        }
    }

    //modifies: combinedBusy
    //effects: run a loop through bt1 and bt2, compare the starting time and ending time
    //return a new Triplet object, and added into combinedBusy
    public static void combineBusyTime(ArrayList<Weekday> bt1, ArrayList<Weekday> bt2) {
        String day;
        for (int i = 0; i < bt1.size(); i++) {
            for (int j  = 0; j < bt2.size(); j++) {
                if (bt1.get(i).getStartingTime() < bt2.get(i).getStartingTime()) {
                    start = bt1.get(i).getStartingTime();
                    day = bt1.get(i).getDayName();
                } else {
                    start = bt2.get(i).getStartingTime();
                    day = bt2.get(i).getDayName();
                }
                if (bt1.get(i).getEndingTime() > bt2.get(i).getEndingTime()) {
                    end = bt1.get(i).getEndingTime();
                } else {
                    end = bt2.get(i).getEndingTime();
                }
                combinedBusy.add(new Triplet(day,start,end));
            }
        }
    }

    //modifies: combinedBusy
    //effects: append new schedule at the start and end of combinedBusy
    //run a loop through combinedBusy, find an empty slot, and create a new Triplet object based on the time of that
    //empty slot
    public static Triplet createMeeting() {
        Triplet meeting = null;
        //if statement: string is null or string
        String day = combinedBusy.get(0).getFirst();
        combinedBusy.add(new Triplet(day,1,7));
        combinedBusy.add(new Triplet(day,22,24));
        for (int i = 0; i < combinedBusy.size() - 1; i++) {
            if ((combinedBusy.get(i + 1).getSecond() - combinedBusy.get(i).getThird()) > 1) {
                String meetingDay = combinedBusy.get(i).getFirst();
                int startTime = combinedBusy.get(i).getThird();
                int endTime = combinedBusy.get(i).getThird() + 1;
                meeting = new Triplet(meetingDay,startTime,endTime);
            }
        }
        return meeting;
    }

}
