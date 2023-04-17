package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class ITile extends Tile {
    
    public ITile(int x, int y) {
        super(x, y);
        this.direction.add(Direction.DOWN);
        this.direction.add(Direction.UP);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.BLUE);

        if(this.direction.contains(Direction.UP)) {
            g.fillRect((this.getWidth()/2)-6, 1, 12, this.getHeight()-1);
        }
        else {
            g.fillRect(0, (this.getHeight()/2)-6, this.getWidth(), 12);
        }
    }
}
