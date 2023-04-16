package sk.stuba.fei.uim.oop.maze;

import javax.swing.JPanel;

abstract class Tile extends JPanel{
    
    protected final int x;
    protected final int y;
    protected Direction direction[];
    protected boolean highlight; 

    protected Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
