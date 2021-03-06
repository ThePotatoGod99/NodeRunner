package main.java.ca.umontreal.iro.hackathon.loderunner;

import hola.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 */
public class Runner extends BasicRunner {

    // TODO : Remplacer ceci par votre clé secrète
    public static final String ROOM = "andylecool";

    /*
     * Utilisez cette variable pour choisir le niveau de départ
     *
     * Notez: le niveau de départ sera 1 pour tout le monde pendant la compétition
     * :v)
     */
    public static final int START_LEVEL = 4;

    private int level = 0;

    public Runner() {
        super(ROOM, START_LEVEL);
        level = START_LEVEL;
    }

    private World world;
    private ArrayList<Direction> directions = new ArrayList<Direction>();

    private Direction direction = Direction.NONE;

    @Override
    public void start(String[] grid) {
        System.out.println("Nouveau niveau ! Grille initiale reçue :");
        world = new World(grid);
        for (int i = 0; i < grid.length; i++) {
            String ligne = grid[i];
            System.out.println(ligne);
        }
//		Cheminement cheminement = world.getCheminement();
//        directions = cheminement.path();
        JFrame frame = new JFrame("Node Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel textLabel = new JLabel("WASD pour bouger, Z et C asdfasdfpour creuser", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));

        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);

        // Simulation simulation = new Simulation(world, world.getFricList().get(0));
        // System.out.println(simulation.simulate());

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'q':
                        System.exit(-1);
                        break;
                    case 'w':
                        direction = Direction.UP;
                        break;
                    case 'a':
                        direction = Direction.LEFT;
                        break;
                    case 's':
                        direction = Direction.DOWN;
                        break;
                    case 'd':
                        direction = Direction.RIGHT;
                        break;
                    case 'z':
                        break;

                    case 'c':
                        break;
                }
                // simulation.nextStep(direction);

                System.out.println("KEY_PRESSED");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                direction = Direction.NONE;
            }
        });


//		directions.add(Direction.LEFT);
//		directions.add(Direction.LEFT);
//		directions.add(Direction.LEFT);


//        while (!goToDoorOrLadder()) {
//
////        goToDoorOrLadder();
//            world.printString();
//            goUpLadder();
//            world.printString();
//            goToFric();
//            goToDoor();
//
//
//        }
//        goToDoorOrLadder();
//        System.out.println(goToFric());


//        goToFric();
//        goToLadder();

//        world.print();
//        world.moveRunner(new SVector3d(29.0, 4.0));
//        world.print();
//
//        System.out.println(goToFric2(world));


//        goToFric();
//        goToDoor();
//        goToLadder();
//
//
//        goUpLadder();

//        world.moveRunner(new SVector3d(29.0, 4.0));
        world.print();
        System.out.println(" GO TO FRIC 2 XDXD " + goToFric2(world));

//        goUpLadder();

//        goToDoor();
//        goToFric();
//        goToDoor();

//        goUpLadder();

//        goUpLadder();

        int i = 0;
        while (!goToDoor()) {
            if(goToFric()){
                i = 0;
            }
            if (goToDoor()) {
                System.out.println("GOTODOOR");
                break;
            }
            if (goToLadder()) {
                i = 0;
                goUpLadder();

            }
            if(goToDoor()){
                System.out.println("GOTODOOR");
                break;
            } else if (i >= 5) {
                System.out.println("ropeASDF adsFDF SGSG SG DDFDGHDGJHJDGHJKDGJHK");
                goToRopeAndDrop();
                goToDoor();
            }
            i++;
            world.printString();
        }

        System.out.println("directions: " + directions);

    }

    public boolean goToRopeAndDrop(){
        int i = 0;
        boolean result = false;

        ArrayList<WorldObject> tempLadderList = (ArrayList) world.getLadderList().clone();
        while (i < tempLadderList.size()) {
            Simulation simulation = new Simulation(world, new SVector3d());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), tempLadderList.get(i));
            if (directionsTemp.size() != 0) {

                directions.addAll(directionsTemp);
                world.moveRunner(tempLadderList.get(i).getPosition());
                tempLadderList.remove(i);
                i--;
                result = true;
            }
            i++;
        }



        directions.add(Direction.DOWN);
        return result;
    }
    public boolean goToDoorOrLadder() {
        boolean xd = true, xd2 = false;
        while (xd) {
            boolean xd1 = goToFric();
            xd2 = goToDoor();

            System.out.println(goToFric());
            System.out.println(goToLadder());
            xd = xd1 || xd2;

        }
        return xd2;
    }


    public static boolean goToFric2(World world) {
        int i = 0;
        while (i < world.getFricList().size()) {
            Simulation simulation = new Simulation(world, new SVector3d());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getFricList().get(i));
            System.out.println(" gotofri2 AS" + directionsTemp);
            if (directionsTemp.size() != 0) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean goToFric() {
//        System.out.println("GoToFric");
        int i = 0;
        boolean result = false;

        while (i < world.getFricList().size()) {
            Simulation simulation = new Simulation(world, new SVector3d());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getFricList().get(i));
            if (directionsTemp.size() != 0) {
                directions.addAll(directionsTemp);
                world.moveRunner(world.getFricList().get(i).getPosition());
                world.getFricList().remove(i);
                i--;
                result = true;
            }
            i++;
        }
        return result;
    }

    public boolean goToLadder() {
//        System.out.println("goToLadder");
        int i = 0;
        boolean result = false;

        ArrayList<WorldObject> tempLadderList = (ArrayList) world.getLadderList().clone();
        while (i < tempLadderList.size()) {
            Simulation simulation = new Simulation(world, new SVector3d());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), tempLadderList.get(i));
            if (directionsTemp.size() != 0) {

                directions.addAll(directionsTemp);
                world.moveRunner(tempLadderList.get(i).getPosition());
                tempLadderList.remove(i);
                i--;
                result = true;
            }
            i++;
        }
        return result;
    }

    public void goUpLadder() {
        System.out.println("goUpLadder");
        int i = 0;

        Simulation simulation = new Simulation(world, new SVector3d());
        //CheminEtPos cheminEtPos = simulation.cheminUPDOWN(world.getRunnerObject());
        //ArrayList<Direction> directionsTemp = cheminEtPos.getDirections();


        double numberOfUp = 0.0;
        if (level == 3 || level == 4) {
            numberOfUp = 3.0;

        }
        for (int j = 0; j < numberOfUp; j++) {

            directions.add(Direction.UP);
        }

        world.moveRunner(world.getRunnerObject().getPosition().add(new SVector3d(0.0, -numberOfUp)));

        System.out.println(goToFric2(world));
        /*if (directionsTemp.size() != 0) {
            directions.addAll(directionsTemp);
            world.moveRunner(cheminEtPos.getPosition());
            i--;
        }*/
    }


    public boolean goToDoor() {
        System.out.println("goToDoor");
        if (!world.getFricList().isEmpty()) {
            return false;
        }
        Simulation simulation = new Simulation(world, new SVector3d());

        ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), world.getPorte());
        if (directionsTemp.size() != 0) {
            directions.addAll(simulation.cheminAB(world.getRunnerObject(), world.getPorte()));
            return true;
        }
        return false;
    }

    @Override
    public Move next(int x, int y) {
        //    System.out.println("Position du runner : (" + x + ", " + y + ")");
//        world.print();
        // world.move(direction);
//        world.setRunnerPosition(new SVector3d(x, y));

//        int direction = 0;//(int) (Math.random() * 4 + 1);

//        Direction dir = Direction.fromInt(direction);

//        Direction dir = direction;
        Direction dir;
        if (direction != Direction.NONE) {
            dir = direction;
        } else {

            dir = executerNext(directions);
        }

//		System.out.println(dir);
        // Direction dir = direction;

//        System.out.println("\n\n\n");
        return new Move(Event.MOVE, dir);
    }

    public Direction executerNext(ArrayList<Direction> directions) {
        if (directions.isEmpty() == false) {
            return directions.remove(0);
        }
        return Direction.NONE;
    }


}

