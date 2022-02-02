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

public class MatchWithUserForADay implements ActionListener {
    TimeMatchingSystem tms;
    private JFrame frame;
    private JTextField f1;
    private JTextField f2;
    private JTextField f3;
    private String userName1;
    private String userName2;
    private String day;

    public MatchWithUserForADay(TimeMatchingSystem tms) {
        this.tms = tms;
        frame = new JFrame("Create meeting wtih another user");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.PINK);

        this.tms = tms;

        createPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    //modifies: pane
    //effects: create a new pane based on information given
    //create 3 field, and call addButton method to create button
    public void createPane(Container pane) {
        pane.setLayout(new GridLayout(5, 5, 2, 2));
        pane.add(new JLabel("Enter your user name:"));
        f1 = new JTextField(5);
        pane.add(f1);
        pane.add(new JLabel("Enter another user name:"));
        f2 = new JTextField(5);
        pane.add(f2);
        pane.add(new JLabel("Enter a day that you want to have a meeting on:"));
        f3 = new JTextField(5);
        pane.add(f3);
        addButton(pane);
    }

    //modifies: pane
    //effects: create 3 new buttons
    public void addButton(Container pane) {
        JButton btn3 = new JButton("Click to view all user info");
        btn3.setActionCommand("all");
        btn3.addActionListener(this);
        pane.add(btn3);
        JButton btn1 = new JButton("Yes");
        btn1.setActionCommand("yes");
        btn1.addActionListener(this);
        pane.add(btn1);
        JButton btn2 = new JButton("No");
        btn2.setActionCommand("no");
        btn2.addActionListener(this);
        pane.add(btn2);

    }

    //modifies: tms
    //effects: listen to button click, if actionCommand is "yes", call compareUsersOnADay method in tms
    //catch an exception if user input is incorrect
    //else if actionCommand is "all", new AllUserInfo and display all user information
    //else simply close the frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("yes")) {
            playSound("click.wav");
            try {
                userName1 = f1.getText();
                userName2 = f2.getText();
                day = f3.getText();
                tms.compareUsersOnADay(userName1,userName2,day);
                frame.dispose();
            } catch (IncorrectUserInputException ex) {
                JOptionPane.showMessageDialog(frame, "Incorrect user input.",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("all")) {
            playSound("click.wav");
            new AllUserInfo(tms);
        } else {
            frame.dispose();
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
