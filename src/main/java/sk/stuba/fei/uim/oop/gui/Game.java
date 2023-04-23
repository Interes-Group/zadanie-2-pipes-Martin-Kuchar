package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    
    public Game() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setResizable(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setLayout(new BorderLayout());
        
        GameLogic logic = new GameLogic(frame);
        frame.addKeyListener(logic);
        
        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        JButton buttonRestart = new JButton("RESTART");
        JButton buttonCheck = new JButton("CHECK");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);
        buttonCheck.addActionListener(logic);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(logic);

        sideMenu.setLayout(new GridLayout(2, 2));
        sideMenu.add(buttonRestart);
        sideMenu.add(buttonCheck);
        sideMenu.add(slider);
        sideMenu.add(logic.getInfoLabel());
        frame.add(sideMenu, BorderLayout.PAGE_START);
        frame.add(logic.getRender(),BorderLayout.CENTER);
        
        frame.setVisible(true);           
    }
}
