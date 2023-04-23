package sk.stuba.fei.uim.oop.maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

public class Tile extends JPanel{
    @Getter
    protected final int xPos;
    @Getter
    protected final int yPos;
    @Getter
    protected ArrayList<Direction> direction;
    @Setter
    protected boolean highlight;
    @Setter
    protected boolean inPath; 

    public Tile(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.direction = new ArrayList<Direction>();
        this.highlight = false;
        this.inPath = false;
    }

    public void rotateDirection() {
        
        for (int i = 0; i < this.direction.size(); i++) {
            Direction d = this.direction.get(i);
            switch (d) {
                case UP:
                this.direction.set(i, Direction.RIGHT);      
                break;
  
                case RIGHT:
                this.direction.set(i, Direction.DOWN);               
                break;

                case DOWN:
                this.direction.set(i, Direction.LEFT);                
                break;

                case LEFT:
                this.direction.set(i, Direction.UP);
                break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        this.setBackground(Color.WHITE);
            
        g.drawRect(0, 0, this.getWidth(), this.getHeight());

        if(highlight) {
            g.setColor(Color.RED);
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawRect(5, 5, this.getWidth()-10, this.getHeight()-10);
            highlight = false;
        }
    }
}
