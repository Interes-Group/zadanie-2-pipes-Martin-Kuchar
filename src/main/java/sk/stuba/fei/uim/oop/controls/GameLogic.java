package sk.stuba.fei.uim.oop.controls;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;

import lombok.Getter;
import sk.stuba.fei.uim.oop.maze.*;
import sk.stuba.fei.uim.oop.gui.Render;

public class GameLogic extends UniversalAdapter {
    @Getter
    private Render render;
    private Maze maze;
    private int mazeSize;
    private JFrame frame;

    public GameLogic(JFrame f) {
        this.mazeSize = 6;
        this.frame = f;
        this.maze = new Maze(mazeSize);
        this.render = new Render(this.maze);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        

    }

    public void generateMaze(int size) {
        this.maze = new Maze(size);
        if (this.render != null) {
            this.render.setMaze(this.maze);
            this.render.revalidate();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 82) {
            this.generateMaze(this.mazeSize);
        }
        else if(e.getKeyCode() == 27) {
            this.frame.dispose();
        }
        else if(e.getKeyCode() == 32) {
            this.maze.checkMaze();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText() == "RESTART") {
            this.generateMaze(this.mazeSize);
        }
        else {
            this.maze.checkMaze();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
        if(c instanceof Tile){
            ((Tile)c).rotateDirection();
            ((Tile)c).setHighlight(true);
            this.render.repaint();

            //System.out.println(((Tile)c).getXPos() + " " + ((Tile)c).getYPos());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
       
        if(!(c instanceof Tile)) {
            return;    
        }

        ((Tile) c ).setHighlight(true);
        //this.render.revalidate();
        this.render.repaint();


    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.mazeSize = ((JSlider) e.getSource()).getValue();
    }
    
}
