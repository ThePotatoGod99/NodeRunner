package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.util.ArrayList;

public class XdSimulation {
    private World world;
    private Direction direction;
    private ArrayList<Direction> directionsList = new ArrayList<>();

    private SVector3d destination;

    int maxShit;

    int remainingTries = 0;

    public XdSimulation(World world, Direction direction, SVector3d destination, int remainingTries) {
        this.world = new World(world.getStringList(), true);
        this.direction = direction;
        this.remainingTries = remainingTries;
        this.destination = destination;
    }


    public static XdSimulation simulation0(World world, SVector3d destination, int remainingTries) {
        return new XdSimulation(world, Direction.NONE, destination, remainingTries);
    }

    public static XdSimulation simulation1(World world, SVector3d destination, int remainingTries) {
        return new XdSimulation(world, Direction.LEFT, destination, remainingTries);
    }

    public static XdSimulation simulation2(World world, SVector3d destination, int remainingTries) {
        return new XdSimulation(world, Direction.RIGHT, destination, remainingTries);
    }
    public static XdSimulation simulation3(World world, SVector3d destination, int remainingTries) {
        return new XdSimulation(world, Direction.UP, destination, remainingTries);
    }
    public static XdSimulation simulation4(World world, SVector3d destination, int remainingTries) {
        return new XdSimulation(world, Direction.DOWN, destination, remainingTries);
    }

    public ArrayList<Direction> simulate() {

        if(destination.equals(world.getRunnerObject().getPosition())){
            System.out.println("ADFJ JDFJADF JASDJF AJD FA FASDF JASDF JASDJFASDJF AJSDFJASDJFASJDFAJSFD== " + world.getRunnerObject().getPosition() + " : " + destination);
            return directionsList;
        }
        if(remainingTries == 0){
            System.out.println(world.getRunnerObject().getPosition() + " remaining tries 0");
            return null;
        }


        System.out.println(world.getRunnerObject().getPosition() + " : moved" + direction + " : " + remainingTries +" + [[[[[[" + destination + "{{{{{");
        boolean moved = world.move(direction);

        world.printString();
        if (moved) {
//            System.out.println(direction);
            directionsList.add(direction);


            ArrayList<Direction> direction1 = XdSimulation.simulation1(new World(world.getStringList(), true), destination, remainingTries - 1).simulate();
//            ArrayList<Direction> direction2 = XdSimulation.simulation2(new World(world.getStringList(), true), destination, remainingTries - 1).simulate();
//            ArrayList<Direction> direction3 = XdSimulation.simulation3(world, destination, remainingTries - 1).simulate();
//            ArrayList<Direction> direction4 = XdSimulation.simulation4(world, destination, remainingTries - 1).simulate();

            if(direction1 != null){
                directionsList.addAll(direction1);
                return directionsList;
            }

//            if(direction2 != null){
//                directionsList.addAll(direction2);
//                return directionsList;
//            }
            /*if(direction3 != null){
                directionsList.addAll(direction3);
                return directionsList;
            }
            if(direction4 != null){
                directionsList.addAll(direction4);
                return directionsList;
            }*/


        }
        return null;
    }
}

