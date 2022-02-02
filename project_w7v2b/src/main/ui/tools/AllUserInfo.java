package ui.tools;

import model.TimeMatchingSystem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AllUserInfo extends JFrame implements ActionListener {
    TimeMatchingSystem tms;

    public AllUserInfo(TimeMatchingSystem tms) {
        this.tms = tms;
        JList<String> displayList = new JList<>(tms.printAllUserInfo().toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(displayList);
        getContentPane().add(scrollPane);
        getContentPane().setBackground(Color.PINK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));

        pack();
        setVisible(true);

    }

    //effects: listen to button click, if actionCommand is "enter", play sound and close the window
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enter")) {
            playSound("click.wav");
            this.dispose();
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
