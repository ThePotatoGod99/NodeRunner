package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.util.ArrayList;

public class CheminEtPos {
    private ArrayList<Direction> directions;
    private SVector3d position;

    public CheminEtPos(ArrayList<Direction> directions, SVector3d position) {
        this.directions = directions;
        this.position = position;

    }

    public CheminEtPos() {
        this.directions = new ArrayList<>();
        this.position = new SVector3d();
    }
    public CheminEtPos(ArrayList<Direction> directions){
        this();
        this.directions = directions;
    }

    public SVector3d getPosition() {
        return position;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public void setPosition(SVector3d position) {
        this.position = position;
    }
}
