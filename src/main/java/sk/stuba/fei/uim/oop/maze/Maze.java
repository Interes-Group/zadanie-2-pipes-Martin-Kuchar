package sk.stuba.fei.uim.oop.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import lombok.Getter;
import lombok.Setter;

public class Maze {
    @Setter @Getter
    private int size;
    @Getter
    private Tile[][] maze;
    private Tile start;
    private Tile end;
    
    @Setter @Getter
    private boolean solved;

    public Maze(int size) {
        this.size = size;

        this.maze = new Tile[this.size][this.size];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.maze[j][i] = new Tile(j, i);
            }
        }
        this.generateMaze();
    }

    public Tile getTile(int x, int y) {
        Tile t = this.maze[x][y];
        return t;
    }

    private void generateMaze() {
        //TODO prijebanec sa nanakresli tile pri vrateni sa a ked s a vracia neda dobry tile na krizovatke idealne zmazat cestu po vrateni
        
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
                //path.add(currentNode);
                path.add(step.getPrevious());
            }
            if(currentNode == this.maze[size-1][size-1]) {
                path.add(currentNode);
                break;
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
        //set start end
        this.maze[path.get(0).getXPos()][path.get(0).getYPos()] = new EndTile(0, 0);
        this.maze[path.get(path.size()-1).getXPos()][path.get(path.size()-1).getYPos()] = new EndTile(size-1, size-1);

        this.start = this.maze[path.get(0).getXPos()][path.get(0).getYPos()];
        this.end = this.maze[path.get(path.size()-1).getXPos()][path.get(path.size()-1).getYPos()];

        //set other
        for (int i = 1; i < path.size()-1; i++) {
            Tile currt = this.maze[path.get(i).getXPos()][path.get(i).getYPos()];
            
            if (path.get(i-1).getXPos() == path.get(i+1).getXPos() || path.get(i-1).getYPos() == path.get(i+1).getYPos()) {
                this.maze[path.get(i).getXPos()][path.get(i).getYPos()] = new ITile(path.get(i).getXPos(), path.get(i).getYPos());
            }
            else {
                this.maze[path.get(i).getXPos()][path.get(i).getYPos()] = new LTile(path.get(i).getXPos(), path.get(i).getYPos());
            }

            //System.out.println(currt.getXPos() + " " + currt.getYPos());
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
        
        stack.add(curr);  //TODO checkuje aj tile kt je napojenz na end ale neni napojeny na path

        while(curr != this.end) {
            
            if( stack.size() == 0) {
                return false;
            }

            curr = stack.remove(0);
            neighbors = this.getNeighbors(curr.getXPos(), curr.getYPos(), visited);

            if (neighbors.size() == 0) {
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
        //TODO resetni cyan farbu vsetkeho po pohnuti tile
    }
}
