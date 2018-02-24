package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.util.ArrayList;

public class Simulation {

    private World world;
    private WorldObject destination;


    private ArrayList<Direction> directionsList = new ArrayList<>();

    public Simulation(World world, WorldObject destination) {
        this.world = (World) world.clone();
        this.destination = destination;


    }

    public ArrayList<Direction> simulate() {
        int direction = (int) (Math.random() * 4 + 1);
        Direction dir = Direction.fromInt(direction);
        nextStep(dir);
        return directionsList;

    }

    public boolean nextStep(Direction direction) {
        if (world.move(direction)) {

            directionsList.add(direction);
            if (world.getRunnerObject().getPosition().equals(destination.getPosition())) {
                return true;
            }

            Simulation simulation = new Simulation(world, destination);
            directionsList.addAll(simulation.simulate());
        }
        return false;
    }


}
