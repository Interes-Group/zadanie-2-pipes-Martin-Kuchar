package sk.stuba.fei.uim.oop.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import sk.stuba.fei.uim.oop.maze.*;

public class Render extends JPanel {
    private Maze maze;
    

    public Render(Maze maze) {
        this.setMaze(maze);
        this.setBackground(Color.LIGHT_GRAY);

    }

    public void setMaze(Maze maze) {
        this.removeAll();
        this.maze = maze;
        this.setLayout(new GridLayout(this.maze.getSize(), this.maze.getSize()));
        
 
        for (int i = 0; i < this.maze.getSize(); i++) {
            for (int j = 0; j < this.maze.getSize(); j++) {
                this.add(this.maze.getTile(j, i));
            }
        }
        
    }
}
