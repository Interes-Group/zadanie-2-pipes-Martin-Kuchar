package sk.stuba.fei.uim.oop.maze;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Maze {
    @Setter @Getter
    private int size;
    private Tile[][] maze;

    public Maze(int size) {
        this.size = size;

        this.initializeMaze();
        this.generateMaze();
    }

    public Tile getTile(int x, int y) {
        return this.maze[x][y];
    }

    private void generateMaze() {

        int[] start = {0,0};
        int[] end = {this.size-1, this.size-1};

        ArrayList<Step> stack = new ArrayList<Step>();
        ArrayList<Tile> path = new ArrayList<Tile>();
        HashSet<Tile> visited = new HashSet<Tile>();
        
       stack.add(new Step(this.maze[start[0]][start[1]], null));

       while (!stack.isEmpty()) {
            Step step = stack.remove(0);
            Tile currentNode = step.getCurrent();
            if (visited.contains(currentNode)) {
                continue;
            }
            if (step.getPrevious() != null) {
                path.add(step.getPrevious());
            }
            //ArrayList<Tile> allNeighbours = currentNode.getAllNeighbour();
            ArrayList<Tile> allNeighbours = this.getNeighbors(currentNode.getXPos(), currentNode.getYPos(), visited);
            Collections.shuffle(allNeighbours);
            allNeighbours.forEach(neighbour -> {
                if (!visited.contains(neighbour)) {
                    stack.add(0, new Step(neighbour, currentNode));
                }
            });
            visited.add(currentNode);
        }
        for (Tile t : path) {
            this.maze[t.getXPos()][t.getYPos()] = new ITile(t.getXPos(), t.getYPos());
        }

    }

    private ArrayList<Tile> getNeighbors(int x, int y, HashSet<Tile> visited) {
        ArrayList<Tile> a = new ArrayList<Tile>();

        if (x > 0 && !visited.contains(this.getTile(x-1, y))) {
            a.add(this.getTile(x-1, y));
        }
        if (y > 0 && !visited.contains(this.getTile(x, y-1))) {
            a.add(this.getTile(x, y-1));
        }
        if (x < this.size - 1 && !visited.contains(this.getTile(x+1, y))) {
            a.add(this.getTile(x+1, y));
        }
        if (y < this.size - 1 && !visited.contains(this.getTile(x, y+1))) {
            a.add(this.getTile(x, y+1));
        }


        return a;
    }

    private void initializeMaze() {
        this.maze = new Tile[this.size][this.size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.maze[i][j] = new Tile(i, j);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.maze[i][j].paint(g);
            }
        }
    }
}
