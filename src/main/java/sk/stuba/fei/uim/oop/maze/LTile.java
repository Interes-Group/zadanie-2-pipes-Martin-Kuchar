package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class LTile extends Tile {
    

    public LTile(int x, int y) {
        super(x, y);
        this.direction.add(Direction.UP);
        this.direction.add(Direction.LEFT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.inPath) {
            g.setColor(Color.CYAN);
        }
        else {
            g.setColor(Color.BLUE);
        }

        if (this.direction.contains(Direction.UP)) {
            g.fillRect(this.getWidth()/2-6, 0, 12, this.getHeight()/2);
        }
        if (this.direction.contains(Direction.RIGHT)) {
            g.fillRect(this.getWidth()/2, (this.getHeight()/2)-6, this.getWidth(), 12);   
        }
        if (this.direction.contains(Direction.DOWN)) {
            g.fillRect(this.getWidth()/2-6, this.getHeight()/2, 12, this.getHeight());
        }
        if (this.direction.contains(Direction.LEFT)) {
            g.fillRect(0, (this.getHeight()/2)-6, this.getWidth()/2, 12);
        }
        
    }
}
