package sk.stuba.fei.uim.oop.controls;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

import lombok.Getter;
import sk.stuba.fei.uim.oop.maze.*;
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
            this.generateMaze(this.mazeSize);
        }
        else {
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
        if(c instanceof Tile){
            ((Tile)c).rotateDirection();
            this.render.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component c = (Component)this.render.getComponentAt(e.getPoint());
        if(c instanceof Tile) {
            ((Tile) c ).setHighlight(true);
            this.render.revalidate();
            this.render.repaint();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.mazeSize = ((JSlider) e.getSource()).getValue();
    }
    
}
