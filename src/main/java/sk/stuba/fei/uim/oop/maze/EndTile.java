package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class EndTile extends Tile {

    public EndTile(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.GREEN);

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
