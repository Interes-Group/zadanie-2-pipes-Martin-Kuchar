package sk.stuba.fei.uim.oop.controls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.maze.Maze;

public class GameLogic extends UniversalAdapter {
    @Getter
    private Render render;
    private Maze maze;
    private int mazeSize;

    public GameLogic() {
        this.mazeSize = 10;

        this.generateMaze(mazeSize);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText() == "RESTART") {
            this.generateMaze(this.mazeSize); //nechce sa vzhreslit buk
            System.out.println("restart");
        }
        else {
            
        }
            
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.mazeSize = ((JSlider) e.getSource()).getValue();
        System.out.println(this.mazeSize);
    }
    
}
