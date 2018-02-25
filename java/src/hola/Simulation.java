package hola;

import com.sun.org.apache.xpath.internal.SourceTree;
import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;
import main.java.ca.umontreal.iro.hackathon.loderunner.Runner;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Simulation {

    private World world;

    private ArrayList<Direction> directions = new ArrayList<>();

    public Simulation(String[] grid) {
        this.world = new World(grid);
    }

    public ArrayList<Direction> cheminAB(WorldObject objetDepart, WorldObject objetArrive) {
        if (objetArrive.getPosition().getY() != objetDepart.getPosition().getY()) {
//            System.out.println("No shit");
            return new ArrayList<Direction>();
        } else {
//            System.out.println(objetArrive.getPosition() + " : " + objetDepart.getPosition());
        }
        ArrayList<Direction> chemin = new ArrayList<Direction>();
        ArrayList<Direction> temp = new ArrayList<Direction>();
        SVector3d iterator2 = objetDepart.getPosition();
        Direction direction = Direction.RIGHT;
        int i = 0;
        while (world.isInBounds(iterator2) && i != 2) {
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
            for (SVector3d iterator = iterator2; world.isInBounds(iterator); iterator = iterator.add(positionToAdd)) {
                if (iterator.equals(objetArrive.getPosition())) {
                    chemin.addAll(temp);
                    return chemin;
                } else if (iterator.getY() != objetArrive.getY() && world.get(iterator).getType() == TypeObjet.ECHELLE) {
                }
                temp.add(direction);
            }
            temp.clear();
            direction = Direction.LEFT;
            i++;
        }
        return chemin;
    }


//    public boolean goToDoorOrLadder() {
//        boolean xd = true, xd2 = false;
//        while (xd) {
//            boolean xd1 = goToFric();
//            xd2 = goToDoor();
//
//            System.out.println(goToFric());
//            System.out.println(goToLadder());
//            xd = xd1 || xd2;
//
//        }
//        return xd2;
//    }

    public static boolean goToFric2(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        int i = 0;
        while (i < world.getFricList().size()) {
            Simulation simulation = new Simulation(world.getStringList());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getFricList().get(i));
            System.out.println(" gotofri2 AS" + directionsTemp);
            if (directionsTemp.size() != 0) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static ArrayList<Direction> goToFric(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
//        System.out.println("GoToFric");
        int i = 0;
        boolean result = false;
        ArrayList<Direction> directionList = new ArrayList<>();

        while (i < world.getFricList().size()) {
            Simulation simulation = new Simulation(world.getStringList());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getFricList().get(i));
            if (directionsTemp.size() != 0) {
                directionList.addAll(directionsTemp);
                world.moveRunner(world.getFricList().get(i).getPosition());
                world.getFricList().remove(i);
                i--;
                result = true;
            }
            i++;
        }
        return directionList;
    }

    public static ArrayList<Direction> goToLadder(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
//        System.out.println("goToLadder");
        int i = 0;
        boolean result = false;

        ArrayList<Direction> directionList = new ArrayList<>();
        ArrayList<WorldObject> tempLadderList = (ArrayList) world.getLadderList().clone();
        while (i < tempLadderList.size()) {
            Simulation simulation = new Simulation(world.getStringList());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), tempLadderList.get(i));
            if (directionsTemp.size() != 0) {

                directionList.addAll(directionsTemp);
                world.moveRunner(tempLadderList.get(i).getPosition());
                tempLadderList.remove(i);
                i--;
                result = true;
                break;
            }
            i++;
        }
        return directionList;
    }

    public static ArrayList<Direction> goToRope(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        int i = 0;
        boolean result = false;

        ArrayList<Direction> directionList = new ArrayList<>();
        ArrayList<WorldObject> tempRopeList = (ArrayList) world.getRopeList().clone();
        while (i < tempRopeList.size()) {
            Simulation simulation = new Simulation(world.getStringList());
            SVector3d posiUnder = tempRopeList.get(i).getPosition().add(World.directionToVector(Direction.DOWN));

            if (world.spaceIsAvailable(posiUnder)) {

                ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), tempRopeList.get(i));
                if (directionsTemp.size() != 0) {

                    directionList.addAll(directionsTemp);
                    world.moveRunner(tempRopeList.get(i).getPosition());
                    tempRopeList.remove(i);
                    i--;
                    result = true;
                    return directionList;
                }
            }
            i++;
        }
        return directionList;
    }

    public static ArrayList<Direction> goDownOne(String[] grid, RunnerObject runnerObject) {

        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());

        ArrayList<Direction> directionList = new ArrayList<>();

        System.out.println(runnerObject.getPosition() + " : " + world.canMove(Direction.DOWN) + world.canMove(Direction.DOWN) + " ++++");
        if (world.canMove(Direction.DOWN)) {
            System.out.println("CAN MOVE DOWN");
            directionList.add(Direction.DOWN);
        }
        return directionList;

    }

    public ArrayList<Direction> goUpLadder(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        ArrayList<Direction> directionList = new ArrayList<>();
        SVector3d pos = runnerObject.getPosition();
        if (world.get(world.getActualRunPos()).getType() == TypeObjet.ECHELLE) {

            while (world.canMove(Direction.UP)) {
                System.out.println(world.getActualRunPos());
                pos = pos.add(World.directionToVector(Direction.UP));
                world.initPlayer(pos);
                directionList.add(Direction.UP);
                if (goToFric2(world.getStringList(), world.getRunnerObject())) {
                    return directionList;
                }
            }
        }
        return directionList;
    }

    /*public ArrayList<Direction> goDropGauche(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        ArrayList<Direction> directionList = new ArrayList<>();
    }*/

    public ArrayList<Direction> goDropGauche(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        ArrayList<Direction> directionList = new ArrayList<>();

        directionList.add(Direction.LEFT);
        directionList.add(Direction.DOWN);
        return directionList;
    }

    public ArrayList<Direction> goDownLadder(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());
        ArrayList<Direction> directionList = new ArrayList<>();
        SVector3d pos = world.getActualRunPos();


        SVector3d nextPos = pos.add(World.directionToVector(Direction.DOWN));
        if (world.get(nextPos).getType() == TypeObjet.ECHELLE) {

            while (world.canMove(Direction.DOWN)) {
                pos = pos.add(World.directionToVector(Direction.DOWN));
                world.initPlayer(pos);
                directionList.add(Direction.DOWN);

                if (goToFric2(world.getStringList(), world.getRunnerObject())) {
                    return directionList;
                }
            }
        }
        return directionList;

    }

    public static ArrayList<Direction> goToDoor(String[] grid, RunnerObject runnerObject) {
        World world = new World(grid, runnerObject.getPosition());
        world.initPlayer(runnerObject.getPosition());

        ArrayList<Direction> directionList = new ArrayList<>();


        if (!world.getFricList().isEmpty()) {
            return directionList;
        }
        Simulation simulation = new Simulation(world.getStringList());

        ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getPorte());
        if (directionsTemp.size() != 0) {
            directionList.addAll(simulation.cheminAB(world.getRunnerObject(), world.getPorte()));
            return directionList;
        }
        return directionList;
    }


    public World getWorld() {
        return world;
    }
}
