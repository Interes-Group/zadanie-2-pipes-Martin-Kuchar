package sk.stuba.fei.uim.oop.maze;

import java.awt.Color;
import java.awt.Graphics;

public class ITile extends Tile {
    
    public ITile(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.BLUE);
        g.fillRect((this.getWidth()/2)-6, 1, 12, this.getHeight()-1);
        //g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
    }
}
