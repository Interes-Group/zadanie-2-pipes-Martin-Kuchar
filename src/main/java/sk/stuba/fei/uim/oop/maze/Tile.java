package sk.stuba.fei.uim.oop.maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import lombok.Setter;

public class Tile extends JPanel{
    
    private final int x;
    private final int y;
    public ArrayList<Direction> direction; //set to private!!
    @Setter
    private boolean highlight; 

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = new ArrayList<Direction>();
        this.highlight = false;
        
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
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());

        if(this.highlight) {
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            this.highlight = false;
        }
    }
}
