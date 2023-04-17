package sk.stuba.fei.uim.oop.maze;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Maze {
    @Setter @Getter
    private int size;
    private Random rand = new Random();
    private Tile[][] maze;

    public Maze(int size) {
        this.size = size;
        this.maze = new Tile[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.maze[i][j] = new Tile(i, j);
            }
        }

        this.generateMaze();
    }

    public Tile getTile(int x, int y) {
        return this.maze[x][y];
    }

    private void generateMaze() {
        int[] start = {0,0};
        int[] end = {this.size-1, this.size-1};

        ArrayList<int[]> neigbors = new ArrayList<int[]>();
        ArrayList<Tile> path = new ArrayList<Tile>();
        HashSet<Tile> visited = new HashSet<Tile>();

        int[] current = start;

        while(current != end) {
            this.maze[current[0]][current[1]] = new ITile(current[0], current[1]);
            path.add(this.getTile(current[0], current[1]));
            visited.add(this.getTile(current[0], current[1]));
            neigbors = getNeighbors(current[0], current[1], visited);
            if(neigbors.size() < 1) {break;}
            current = neigbors.get(rand.nextInt(neigbors.size()));
        }

    }

    private ArrayList<int[]> getNeighbors(int x, int y, HashSet<Tile> visited) {
        ArrayList<int[]> a = new ArrayList<int[]>();

        if (x > 0 && !visited.contains(this.getTile(x-1, y))) {
            a.add(new int[]{x - 1, y});
        }
        if (y > 0 && !visited.contains(this.getTile(x, y-1))) {
            a.add(new int[]{x, y - 1});
        }
        if (x < this.size - 1 && !visited.contains(this.getTile(x+1, y))) {
            a.add(new int[]{x + 1, y});
        }
        if (y < this.size - 1 && !visited.contains(this.getTile(x, y+1))) {
            a.add(new int[]{x, y + 1});
        }


        return a;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.maze[i][j].paint(g);
            }
        }
    }
}
