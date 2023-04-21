package sk.stuba.fei.uim.oop.controls;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;

import lombok.Getter;
import sk.stuba.fei.uim.oop.maze.*;
import sk.stuba.fei.uim.oop.gui.Render;

public class GameLogic extends UniversalAdapter {
    @Getter
    private Render render;
    private Maze maze;
    @Getter
    private int mazeSize;
    private JFrame frame;
    @Getter
    private JLabel infoLabel;
    private int level;


    public GameLogic(JFrame f) {
        this.mazeSize = 4;
        this.frame = f;
        this.maze = new Maze(mazeSize);
        this.render = new Render(this.maze);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.level = 1;
        this.infoLabel = new JLabel();
        this.refreshInfo();
        

    }

    public void generateMaze(int size) {
        this.maze = new Maze(size);
        if (this.render != null) {
            this.render.setMaze(this.maze);
            this.render.revalidate();

        }
        this.refreshInfo();
    }

    private void checkMaze() {  
        System.out.println(this.maze.checkMaze());
        if (this.maze.checkMaze() && !this.maze.isSolved()) {
            this.level++;
            this.generateMaze(this.mazeSize);
        }
        
    }

    private void refreshInfo() {
        this.infoLabel.setText("Size of board is: " + String.valueOf(mazeSize) + "    You are on level: " + String.valueOf(level));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 82) {
            this.level = 1;
            this.generateMaze(this.mazeSize);
        }
        else if(e.getKeyCode() == 27) {
            this.frame.dispose();
        }
        else if(e.getKeyCode() == 32) {
            this.checkMaze();
            this.refreshInfo();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText() == "RESTART") {
            this.level = 1;
            this.generateMaze(this.mazeSize);

        }
        else {
            this.checkMaze();
            this.render.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
        if(c instanceof Tile){
            ((Tile)c).rotateDirection();
            ((Tile)c).setHighlight(true);
            this.maze.resetInPath();
            this.render.repaint();

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
       
        if(!(c instanceof Tile)) {
            return;    
        }
        ((Tile) c ).setHighlight(true);
        this.render.repaint();

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(((JSlider) e.getSource()).getValueIsAdjusting()) {
            this.mazeSize = ((JSlider) e.getSource()).getValue();
            this.generateMaze(this.mazeSize);
        }
    }
    
}
