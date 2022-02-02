package ui;

import exceptions.IncorrectUserInputException;
import model.TimeMatchingSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class TimeManager extends JFrame implements ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JTextField field;
    TimeMatchingSystem tms;


    public TimeManager() {
        super("Time Matching System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(200, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        tms = new TimeMatchingSystem();
        getContentPane().setBackground(Color.PINK);
        JButton btn1 = new JButton("Enter");
        btn1.setActionCommand("enter");
        btn1.addActionListener(this);
        label1 = new JLabel("Hello!");
        label2 = new JLabel("Enter your user name.");
        add(label1);
        add(label2);
        field = new JTextField(5);
        add(field);
        add(btn1);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //effects: listen to button click, if actionCommand is "enter", call addNewUser method in tms
    //and bring up a new AfterLogIn pane
    //catch an exception if user input is incorrect, catch IOEception
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enter")) {
            try {
                tms.addNewUser(field.getText());
                dispose();
                playSound("music.wav");
                new AfterLogIn(tms);
            } catch (IncorrectUserInputException i) {
                JOptionPane.showMessageDialog(this, "Incorrect user input.",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException i) {
                JOptionPane.showMessageDialog(this, "IOException.",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            }
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

    public static void main(String[] args) {
        new TimeManager();
    }
}