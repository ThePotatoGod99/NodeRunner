package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Simulation {

    private World world;
    private SVector3d destination;


    private int[] directionChoices = {2, 3, 4, 1};
    private int i = 0;


    private int Maxshit = 0;


    private ArrayList<Direction> directionsList = new ArrayList<>();

    public Simulation(World world, SVector3d destination) {
        this.world =  new World(world.getStringList());

        world.getVisitedList().add(new SVector3d(1, 4.0));

        world.dontComeBack = true;
        this.destination = destination;
    }


    public ArrayList<Direction> cheminUPDOWN(WorldObject objetDepart,WorldObject objetArrive){
        if(objetArrive.getPosition().getX() != objetDepart.getPosition().getX()){
            System.out.println("No shit");
            return new ArrayList<Direction>();
        }
        else{
            System.out.println(objetArrive.getPosition() + " : " + objetDepart.getPosition());
        }
        ArrayList<Direction> chemin = new ArrayList<Direction>();
        ArrayList<Direction> temp = new ArrayList<Direction>();
        SVector3d iterator2 = objetDepart.getPosition();
        Direction direction = Direction.UP;
        int i = 0;
        while(world.isInBounds(iterator2) && i != 2) {
            SVector3d positionToAdd = new SVector3d();
            switch (direction) {
                case UP:
                    positionToAdd = new SVector3d(0.0, -1.0);
                    break;
                case DOWN:
                    positionToAdd = new SVector3d(0.0, 1.0);
                    break;
                case LEFT:
                    positionToAdd = new SVector3d(-1.0, 0.0);
                    break;
                case NONE:
                case RIGHT:
                    positionToAdd = new SVector3d(1.0, 0.0);
                    break;
            }
            for(SVector3d iterator = iterator2;world.isInBounds(iterator);iterator = iterator.add(positionToAdd)) {
                if(iterator.equals(objetArrive.getPosition())) {
                    chemin.addAll(temp);
                    return chemin;
                } else if(iterator.getY()!= objetArrive.getY() && world.get(iterator).getType()==TypeObjet.ECHELLE) {
                }
                temp.add(direction);
            }
            temp.clear();
            direction = Direction.DOWN;
            i++;
        }
        return chemin;
    }


    public ArrayList<Direction> cheminAB(WorldObject objetDepart,WorldObject objetArrive){
        if(objetArrive.getPosition().getY() != objetDepart.getPosition().getY()){
            System.out.println("No shit");
            return new ArrayList<Direction>();
        }
        else{
            System.out.println(objetArrive.getPosition() + " : " + objetDepart.getPosition());
        }
        ArrayList<Direction> chemin = new ArrayList<Direction>();
        ArrayList<Direction> temp = new ArrayList<Direction>();
        SVector3d iterator2 = objetDepart.getPosition();
        Direction direction = Direction.RIGHT;
        int i = 0;
        while(world.isInBounds(iterator2) && i != 2) {
            SVector3d positionToAdd = new SVector3d();
            switch (direction) {
                case UP:
                    positionToAdd = new SVector3d(0.0, -1.0);
                    break;
                case DOWN:
                    positionToAdd = new SVector3d(0.0, 1.0);
                    break;
                case LEFT:
                    positionToAdd = new SVector3d(-1.0, 0.0);
                    break;
                case NONE:
                case RIGHT:
                    positionToAdd = new SVector3d(1.0, 0.0);
                    break;
            }
            for(SVector3d iterator = iterator2;world.isInBounds(iterator);iterator = iterator.add(positionToAdd)) {
                if(iterator.equals(objetArrive.getPosition())) {
                    chemin.addAll(temp);
                    return chemin;
                } else if(iterator.getY()!= objetArrive.getY() && world.get(iterator).getType()==TypeObjet.ECHELLE) {
                }
                temp.add(direction);
            }
            temp.clear();
            direction = Direction.LEFT;
            i++;
        }
        return chemin;
    }
    public ArrayList<Direction> simulate() {

        int remainingTries = 10;
//        while (true) {
            System.out.println(world.getRunnerObject().getPosition() + " REMAINING*  " + remainingTries);
            ArrayList<Direction> direction1 = XdSimulation.simulation0(world, destination, remainingTries ).simulate();
//            ArrayList<Direction> direction2 = XdSimulation.simulation2(world, destination, remainingTries ).simulate();
//            ArrayList<Direction> direction3 = XdSimulation.simulation3(world, destination, remainingTries ).simulate();
//            ArrayList<Direction> direction4 = XdSimulation.simulation4(world, destination, remainingTries ).simulate();

            if (direction1 != null) {
                directionsList.addAll(direction1);
                return directionsList;
            }

//            if (direction2 != null) {
//                directionsList.addAll(direction2);
//                return directionsList;
//            }
//            if (direction3 != null) {
//                directionsList.addAll(direction3);
//                return directionsList;
//            }
//            if (direction4 != null) {
//                directionsList.addAll(direction4);
//                return directionsList;
//            }

   /*         remainingTries++;
            if(remainingTries >= 10){
                System.out.println("ASDFASDF 100");
                return directionsList;
            }
        }*/

   return directionsList;



//        nextStep(direction);

      /*  ArrayList<Direction> directions1 = new XdSimulation(world, Direction.LEFT, 0).simulate();
        ArrayList<Direction> directions2 = new XdSimulation(world, Direction.RIGHT, 0).simulate();
        ArrayList<Direction> directions3 = new XdSimulation(world, Direction.UP, 0).simulate();
        ArrayList<Direction> directions4 = new XdSimulation(world, Direction.DOWN, 0).simulate();
        int[] sizes = new int[4];
        if (directions1 != null) {
            int l1 = directions1.size();
            sizes[0] = l1;
        }
        if (directions2 != null) {
            int l2 = directions2.size();
            sizes[1] = l2;
        }

        if (directions3 != null) {
            int l3 = directions3.size();
            sizes[2] = l3;
        }
        if (directions4 != null) {
            int l4 = directions4.size();
            sizes[3] = l4;
        }

        int min = getMin(sizes);

        if (min == 0) {
            return directions1;
        }
        if (min == 1) {
            return directions2;
        }
        if (min == 2) {
            return directions3;
        }
        if (min == 3) {
            return directions4;
        }

        */

    }


    public static int getMin(int[] inputArray) {
        int minValue = inputArray[0];
        int minIndex = 0;
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] < minValue) {
                minValue = inputArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


//    public boolean nextStep(int direction) {
////        Direction dir = Direction.fromInt(direction);
////        return nextStep(dir);
////
////    }
////
////    public boolean nextStep(Direction direction) {
//
//    }


}



/*
            boolean xd = world.move(direction);
            System.out.println(direction + " + " + xd);
            if (xd) {

                directionsList.add(direction);
                if (world.getRunnerObject().getPosition().equals(destination.getPosition())) {
                    return directionsList;
                }

                boolean continuer = true;

                ArrayList<Direction> result = new ArrayList<>();
                ArrayList<Direction> directions1 = new XdSimulation(world, Direction.LEFT, maxShit).simulate();
                ArrayList<Direction> directions2 = new XdSimulation(world, Direction.RIGHT, maxShit).simulate();
                ArrayList<Direction> directions3 = new XdSimulation(world, Direction.UP, maxShit).simulate();
                ArrayList<Direction> directions4 = new XdSimulation(world, Direction.DOWN, maxShit).simulate();

                ArrayList<ArrayList<Direction>> dir = new ArrayList<>();
                ArrayList<Float> sizes = new ArrayList<Float>();




                    if (directions1 != null) {
                        dir.add(directions1);
                        sizes.add((float) directions1.size());
                    }
                    if (directions2 != null) {
                        dir.add(directions2);
                        sizes.add((float) directions2.size());
                    }
                    if (directions3 != null) {
                        dir.add(directions3);
                        sizes.add((float) directions3.size());
                    }
                    if (directions4 != null) {
                        dir.add(directions4);
                        sizes.add((float) directions4.size());
                    }


                directionsList.addAll(result);
                return directionsList;

            } else {
                return null;
            }*/
