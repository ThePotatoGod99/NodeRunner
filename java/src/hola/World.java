package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;
import main.java.ca.umontreal.iro.hackathon.loderunner.Runner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class World implements Cloneable {
//    private ArrayList<WorldObject> objectList = new ArrayList<>();

    private ArrayList<ArrayList<WorldObject>> worldMap = new ArrayList<>();

    private RunnerObject runnerObject = new RunnerObject();
    private WorldObject porte = new WorldObject();
    private ArrayList<WorldObject> fricList = new ArrayList<>();


    private SVector3d positionOfInitalizer = new SVector3d();


    private String[] grid;

    public World(String[] grid) {
        this.grid = grid;

        for (int i = 0; i < grid[0].length(); i++) {

            worldMap.add(new ArrayList<WorldObject>());
        }
        for (int i = 0; i < grid.length; i++) {
            String ligne = grid[i];
            this.addLine(ligne);
        }
    }


    public void addLine(String line) {
        positionOfInitalizer = new SVector3d(0.0, positionOfInitalizer.getY());

        for (char character : line.toCharArray()) {
            int x = (int) positionOfInitalizer.getX(), y = (int) positionOfInitalizer.getY();
            WorldObject object = new WorldObject();
            switch (character) {
                case ' ':
                    break;
                case '@':
                    object = new WorldObject(TypeObjet.BLOC_PIERRE, positionOfInitalizer);
                    break;
                case '#':
                    object = new WorldObject(TypeObjet.BLOC_BRIQUE, positionOfInitalizer);
                    break;
                case '-':
                    object = new WorldObject(TypeObjet.CORDE, positionOfInitalizer);
                    break;
                case 'H':
                    object = new WorldObject(TypeObjet.ECHELLE, positionOfInitalizer);
                    break;
                case '$':
                    object = new WorldObject(TypeObjet.FRIC, positionOfInitalizer);
                    fricList.add(object);
                    break;
                case '&':
                    runnerObject = new RunnerObject(positionOfInitalizer);
                    object = runnerObject;
                    break;
                case 'S':
                    object = new WorldObject(TypeObjet.SORTIE, positionOfInitalizer);
                    porte = object;
                    break;
            }
            if (!object.equals(new WorldObject())) {
//                objectList.add(object);
            } else {
                object = new WorldObject(positionOfInitalizer);
            }

            worldMap.get(x).add(y, object);
            positionOfInitalizer = positionOfInitalizer.add(new SVector3d(1.0, 0));
        }
        positionOfInitalizer = positionOfInitalizer.add(new SVector3d(0, 1.0));

    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {

        int x = 0, y = 0;
        String result = "";
        while (y < (int) positionOfInitalizer.getY()) {

            while (x < (int) positionOfInitalizer.getX()) {
                result += "[" + get(x, y).toString() + "]";
                x++;
            }
            x = 0;
            y++;
            System.out.print("\n");
            result += "\n";
        }
        return result;
    }


    public boolean spaceIsAvailable(SVector3d position) {
        WorldObject objectAtPos = this.get(position);
        TypeObjet type = objectAtPos.getType();
        switch (type) {
            case ESPACE:
                break;
            case CORDE:
                break;
            case FRIC:
                break;
            case SORTIE:
                break;
            case ECHELLE:
                break;
            default:
                return false;
        }
        return true;

    }

    public boolean move(Direction direction) {
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
                break;
            case RIGHT:
                positionToAdd = new SVector3d(1.0, 0.0);
                break;
        }
        SVector3d oldPos = runnerObject.getPosition();
        SVector3d newPos = runnerObject.getPosition().add(positionToAdd);

        if (!isInBounds(newPos)) {
            return false;
        }


        WorldObject objectAtOld = this.get(oldPos);
        WorldObject objectAtPos = this.get(newPos);

        if (direction.equals(Direction.UP) && !objectAtOld.getType().equals(TypeObjet.ECHELLE)) {
            return false;
        }
        if (direction.equals(Direction.DOWN) && !objectAtOld.getType().equals(TypeObjet.ECHELLE) && !objectAtOld.getType().equals(TypeObjet.CORDE)) {
            return false;
        }


        if (spaceIsAvailable(newPos)) {
            if (objectAtPos.getType().equals(TypeObjet.ESPACE)) {

                change(newPos, runnerObject);

            } else {

            }
            runnerObject.setPosition(newPos);
            change(oldPos, new WorldObject(TypeObjet.VISITED, oldPos));

        } else {
            return false;
        }

        return true;
    }

    public boolean isInBounds(SVector3d position) {
        return position.getX() <= positionOfInitalizer.getX() &&
                position.getY() <= positionOfInitalizer.getY() &&
                position.getX() >= 0.0 &&
                position.getY() >= 0.0;
    }

    public void change(SVector3d position, WorldObject newObject) {
        this.change((int) position.getX(), (int) position.getY(), newObject);
    }

    public void change(int x, int y, WorldObject newObject) {
        worldMap.get(x).set(y, newObject);
    }

    public ArrayList<WorldObject> getFricList() {
        return fricList;
    }

    public WorldObject getPorte() {
        return porte;
    }

    public WorldObject get(int x, int y) {
        return worldMap.get(x).get(y);
    }

    public WorldObject get(SVector3d position) {
        return get((int) position.getX(), (int) position.getY());
    }

    public RunnerObject getRunnerObject() {
        return runnerObject;
    }

    @Override
    protected Object clone() {
        try {

            World world = new World(grid);
            world.worldMap = (ArrayList) this.worldMap.clone();
            world.runnerObject = (RunnerObject) runnerObject.clone();
            world.porte = (WorldObject) porte.clone();
            world.fricList = (ArrayList) this.fricList.clone();
            world.positionOfInitalizer = new SVector3d(positionOfInitalizer.getX(), positionOfInitalizer.getY());
            return world;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
