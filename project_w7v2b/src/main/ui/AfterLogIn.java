package ui;

import exceptions.IncorrectUserInputException;
import model.TimeMatchingSystem;
import sun.misc.JavaLangAccess;
import ui.tools.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class AfterLogIn implements ActionListener {
    private JFrame second = new JFrame("Time Matching System");
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    TimeMatchingSystem tms;

    public AfterLogIn(TimeMatchingSystem tms) throws IOException {
        this.tms = tms;
        second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        second.setPreferredSize(new Dimension(500, 365));
        second.setLayout(new FlowLayout());
        JLabel background;
        ImageIcon img = new ImageIcon("1.jpg");
        background = new JLabel("",img, JLabel.HORIZONTAL);
        background.setBounds(0,0,300,300);
        second.add(background);
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        second.setJMenuBar(createMenuBar());
        second.pack();
        second.setLocationRelativeTo(null);
        second.setVisible(true);
    }

    //modifies: second and menuBar
    //effects: return and add 4 menu items into menuBar
    public JMenuBar createMenuBar() {
        menuItem = new JMenuItem("Add another user", KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem.setActionCommand("1");
        menuItem.addActionListener(this);
        menuItem = new JMenuItem("Add new schedule", KeyEvent.VK_B);
        menu.add(menuItem);
        menuItem.setActionCommand("2");
        menuItem.addActionListener(this);
        menuItem = new JMenuItem("Display all user info", KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem.setActionCommand("3");
        menuItem.addActionListener(this);
        menuItem = new JMenuItem("Create meeting with another user", KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem.setActionCommand("4");
        createMenu5();
        return menuBar;
    }

    //modifies: second and menuBar
    //effects: return and add the 5th menu items into menuBar
    public void createMenu5() {
        menuItem.addActionListener(this);
        menuItem = new JMenuItem("Display entire schedule for user", KeyEvent.VK_D);
        menu.add(menuItem);
        menuItem.setActionCommand("5");
        menuItem.addActionListener(this);
    }

    //modifies: tms
    //effects: if actionCommand is "1", new AddAnotherUser, ang bring up a new option pane
    //else if actionCommand is "2", new AddNewSchedule, ang bring up a new option pane
    //else if actionCommand is "3", new AllUserInfo, ang bring up a new option pane
    //else if actionCommand is "4", new MatchWithUserForADay, ang bring up a new option pane
    //else if actionCommand is "5", new EntireScheduleForUser, ang bring up a new option pane
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("1")) {
            new AddAnotherUser(tms); //new optionpane
        } else if (e.getActionCommand().equals("2")) {
            new AddNewSchedule(tms); //new optionpane
        } else if (e.getActionCommand().equals("3")) {
            new AllUserInfo(tms);
        } else if (e.getActionCommand().equals("4")) {
            new MatchWithUserForADay(tms);
        } else if (e.getActionCommand().equals("5")) {
            System.out.println("method called");
            new EntireScheduleForUser(tms);
        }
    }



}
