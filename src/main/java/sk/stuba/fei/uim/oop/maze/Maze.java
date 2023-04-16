package sk.stuba.fei.uim.oop.maze;

import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;

public class Maze {
    @Setter @Getter
    private int size;

    private Tile[][] maze;

    public Maze(int size) {
        this.size = size;
        this.maze = new Tile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.maze[i][j] = new ITile(i, j);

            }
        }
    }

    public Tile getTile(int x, int y) {
        return this.maze[x][y];
    }

    public void draw(Graphics g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.maze[i][j].paint(g);
            }
        }
    }
}
