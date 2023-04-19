package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class EndTile extends Tile {

    public EndTile(int x, int y) {
        super(x, y);
        this.direction.add(Direction.UP);
        this.direction.add(Direction.LEFT);
        this.direction.add(Direction.DOWN);
        this.direction.add(Direction.RIGHT);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
