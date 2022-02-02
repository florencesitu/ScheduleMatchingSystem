package ui.tools;

import exceptions.IncorrectUserInputException;
import model.TimeMatchingSystem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddNewSchedule implements ActionListener {
    private JFrame frame;
    private JPanel pane;
    private String name;
    private String day;
    private String eventName;
    private String startTime;
    private String endTime;
    private int option;

    private JTextField f1;
    private JTextField f2;
    private JTextField f3;
    private JTextField f4;
    private JTextField f5;

    TimeMatchingSystem tms;

    public AddNewSchedule(TimeMatchingSystem tms) {
        frame = new JFrame("Add another user");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setBackground(Color.PINK);

        this.tms = tms;

        createPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    //modifies: pane
    //effects: create a new pane based on information given
    //create 4 field, and call addButton method to create button
    public void createPane(Container pane) {
        pane.setLayout(new GridLayout(7, 7, 2, 2));
        f1 = new JTextField(5);
        f2 = new JTextField(5);
        f3 = new JTextField(5);
        f4 = new JTextField(5);
        f5 = new JTextField(5);
        pane.add(new JLabel("User name:"));
        pane.add(f1);
        pane.add(new JLabel("Day to enter(Monday to Friday):"));
        pane.add(f2);
        pane.add(new JLabel("Event name:"));
        pane.add(f3);
        pane.add(new JLabel("Starting Time:"));
        pane.add(f4);
        pane.add(new JLabel("Ending Time:"));
        pane.add(f5);
        addButton(pane);
    }

    //modifies: pane
    //effects: create 2 new buttons
    public void addButton(Container pane) {
        JButton btn1 = new JButton("Yes");
        btn1.setActionCommand("yes");
        btn1.addActionListener(this);
        pane.add(btn1);
        JButton btn2 = new JButton("No");
        btn2.setActionCommand("no");
        btn2.addActionListener(this);
        pane.add(btn2);
    }

    //effects: listen to button click, if actionCommand is "yes", call addNewUser method and add a new user into tms
    //catch an exception if user input is incorrect
    //else simply close the frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("yes")) {
            playSound("click.wav");
            name = f1.getText();
            day = f2.getText();
            eventName = f3.getText();
            startTime = f4.getText();
            endTime = f5.getText();
            addSchedule();
            frame.dispose();
        } else {
            playSound("click.wav");
            frame.dispose();
        }
    }

    //modifies: tms
    //effects: call eventEntry method in tms and add new schedule, catch user incorrect exception if user doesn't exist
    public void addSchedule() {
        try {
            tms.eventEntry(name,day,eventName,startTime,endTime);
        } catch (IncorrectUserInputException ex) {
            JOptionPane.showMessageDialog(pane, "Incorrect user input.",
                    "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //effects: playsound of a given soundfile, catch an exception if such file doesn't exist
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
