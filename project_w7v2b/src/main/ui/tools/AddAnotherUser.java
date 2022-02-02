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

public class AddAnotherUser implements ActionListener {
    TimeMatchingSystem tms;
    private JTextField f1;
    private String userName;
    private JFrame frame;

    public AddAnotherUser(TimeMatchingSystem tms) {
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
    //create 1 field, and 2 buttons
    public void createPane(Container pane) {
        pane.setLayout(new GridLayout(2, 2, 2, 2));
        pane.add(new JLabel("Enter your user name."));
        f1 = new JTextField(12);
        pane.add(f1);
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
    //effects: listen to button click, if actionCommand is "yes", call addNewUser method and add a new user into tms
    //catch an exception if user input is incorrect
    //else simply close the frame
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("yes")) {
            playSound("click.wav");
            try {
                userName = f1.getText();
                tms.addNewUser(userName);
                frame.dispose();
            } catch (IncorrectUserInputException ex) {
                JOptionPane.showMessageDialog(frame, "Incorrect user input.",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            playSound("click.wav");
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
