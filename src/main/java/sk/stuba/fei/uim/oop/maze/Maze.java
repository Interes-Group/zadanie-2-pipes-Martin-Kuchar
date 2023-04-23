package sk.stuba.fei.uim.oop.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Maze {
    @Setter @Getter
    private int size;
    @Getter
    private Tile[][] maze;
    private Tile start;
    private Tile end;
    private Random rand;
    @Setter @Getter
    private boolean solved;

    public Maze(int size) {
        this.size = size;
        this.rand = new Random();
        this.maze = new Tile[this.size][this.size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.maze[j][i] = new Tile(j, i);
            }
        }
        this.generateMaze();
        this.shuffleTiles();
    }

    public Tile getTile(int x, int y) {
        return this.maze[x][y];
    }

    private void generateMaze() {
        int[] start = {0, 0};
        int[] end = {0, 0};

        if(rand.nextInt(2) == 0) {
            start[0] = 0;
            start[1] = rand.nextInt(size);
            end[0] = size-1;
            end[1] = rand.nextInt(size);
        }
        else {
            start[0] = rand.nextInt(size);
            start[1] = 0;
            end[0] = rand.nextInt(size);
            end[1] = size-1;
        }

        ArrayList<Tile> path = new ArrayList<Tile>();
        ArrayList<Tile> neigbors = new ArrayList<Tile>();
        HashSet<Tile> visited = new HashSet<Tile>();

        path.add( this.maze[start[0]][start[1]]);

        while(path.get(path.size()-1) != this.maze[end[0]][end[1]]) {
            Tile curr = path.get(path.size()-1);
            visited.add(curr);

            neigbors = this.getNeighbors(curr.getXPos(), curr.getYPos(), visited);
            Collections.shuffle(neigbors);

            if(neigbors.isEmpty()) {
                path.remove(path.size()-1);
                continue;
            }

            for (Tile tile : neigbors) {
                if (!visited.contains(tile)) {
                    path.add(tile);
                    break;
                }
            }
        }


        maze[start[0]][start[1]] = new StartTile(start[0], start[1], this.getRelativeDirection(this.maze[start[0]][start[1]], path.get(1)));
        maze[end[0]][end[1]] = new EndTile(end[0], end[1], this.getRelativeDirection(this.maze[end[0]][end[1]], path.get(path.size()-2)));
        
        this.start = this.maze[start[0]][start[1]];
        this.end = this.maze[end[0]][end[1]];

        for (int i = 1; i < path.size()-1; i++) {
            Tile currt = path.get(i);

            if (!(currt instanceof LTile) && !(currt instanceof ITile)) {
                if (path.get(i-1).getXPos() == path.get(i+1).getXPos() || path.get(i-1).getYPos() == path.get(i+1).getYPos()) {
    
                    this.maze[path.get(i).getXPos()][path.get(i).getYPos()] = new ITile(path.get(i).getXPos(), path.get(i).getYPos());
                }
                else {
                    this.maze[path.get(i).getXPos()][path.get(i).getYPos()] = new LTile(path.get(i).getXPos(), path.get(i).getYPos());
                }
            }
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

    public boolean checkMaze() {

        Tile curr = this.start;
        ArrayList<Tile> stack = new ArrayList<Tile>();
        ArrayList<Tile> neighbors;
        HashSet<Tile> visited = new HashSet<Tile>();
        Direction lastDir = null;
        
        stack.add(curr);  

        while(curr != this.end) {
            
            if( stack.size() == 0) {
                return false;
            }

            curr = stack.remove(0);
            neighbors = this.getNeighbors(curr.getXPos(), curr.getYPos(), visited);

            if (neighbors.size() == 0 && curr != this.end) {
                return false;
            }

            visited.add(curr);
            for (Tile t : neighbors) {
                if (t instanceof LTile || t instanceof ITile || t instanceof EndTile) {
                    if (this.getRelativeDirection(curr, t) == Direction.DOWN && curr.getDirection().contains(Direction.DOWN) && t.getDirection().contains(Direction.UP) && lastDir != Direction.DOWN) {
                        t.setInPath(true);
                        lastDir = Direction.UP;
                        stack.add(t);
                    }
                    else if (this.getRelativeDirection(curr, t) == Direction.UP && curr.getDirection().contains(Direction.UP) && t.getDirection().contains(Direction.DOWN) && lastDir != Direction.UP) {
                        t.setInPath(true);
                        lastDir = Direction.DOWN;
                        stack.add(t);
                    }
                    else if (this.getRelativeDirection(curr, t) == Direction.RIGHT && curr.getDirection().contains(Direction.RIGHT) && t.getDirection().contains(Direction.LEFT) && lastDir != Direction.RIGHT) {
                        t.setInPath(true);
                        lastDir = Direction.LEFT;
                        stack.add(t);
                    }
                    else if (this.getRelativeDirection(curr, t) == Direction.LEFT && curr.getDirection().contains(Direction.LEFT) && t.getDirection().contains(Direction.RIGHT) && lastDir != Direction.LEFT) {
                        t.setInPath(true);
                        lastDir = Direction.RIGHT;
                        stack.add(t);
                    }
                }
            }
        }
        return true;
    }

    private Direction getRelativeDirection(Tile t, Tile r) {
        
        if (t.getXPos() > r.getXPos()) {
            return Direction.LEFT;
        }
        else if (t.getXPos() < r.getXPos()) {
            return Direction.RIGHT;
        }
        else if (t.getYPos() < r.getYPos()) {
            return Direction.DOWN;
        }
        else {
            return Direction.UP;
        }
    }

    public void resetInPath() {
        for (Tile[] tiles : maze) {
            for (Tile t : tiles) {
                t.setInPath(false);
            }
        }
    }

    private void shuffleTiles() {
        for (Tile[] tiles : maze) {
            for (Tile t : tiles) {
                for(int i = 0; i < this.rand.nextInt(4); i++){
                    t.rotateDirection();
                }
            }
        }
    }
}
