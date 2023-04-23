package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class EndTile extends Tile {
    public EndTile(int x, int y, Direction d) {
        super(x, y);
        this.direction.add(d);
    }

    @Override
    public void rotateDirection() {
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);

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
