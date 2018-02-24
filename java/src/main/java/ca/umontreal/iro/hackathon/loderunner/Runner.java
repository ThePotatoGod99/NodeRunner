package main.java.ca.umontreal.iro.hackathon.loderunner;

import hola.Cheminement;
import hola.SVector3d;
import hola.Simulation;
import hola.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 */
public class Runner extends BasicRunner {

<<<<<<< .mine
    // TODO : Remplacer ceci par votre clé secrète
    public static final String ROOM = "andylecool";
||||||| .r44237
	// TODO : Remplacer ceci par votre clé secrète
	public static final String ROOM = "eee";
=======
	// TODO : Remplacer ceci par votre clé secrète
	public static final String ROOM = "pok";
>>>>>>> .r44252

    /*
     * Utilisez cette variable pour choisir le niveau de départ
     *
     * Notez: le niveau de départ sera 1 pour tout le monde pendant la compétition
     * :v)
     */
    public static final int START_LEVEL = 1;

    public Runner() {
        super(ROOM, START_LEVEL);
    }

    private World world;
    private ArrayList<Direction> directions = new ArrayList<Direction>();

    private Direction direction = Direction.NONE;

<<<<<<< .mine
    @Override
    public void start(String[] grid) {
        System.out.println("Nouveau niveau ! Grille initiale reçue :");
        world = new World(grid);
        for (int i = 0; i < grid.length; i++) {
            String ligne = grid[i];
            System.out.println(ligne);
        }
||||||| .r44237
	@Override
	public void start(String[] grid) {
		System.out.println("Nouveau niveau ! Grille initiale reçue :");
		world = new World(grid);
		for (int i = 0; i < grid.length; i++) {
			String ligne = grid[i];
			System.out.println(ligne);
		}
		
		JFrame frame = new JFrame("Node Runner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
=======
	@Override
	public void start(String[] grid) {
		System.out.println("Nouveau niveau ! Grille initiale reçue :");
		world = new World(grid);
		for (int i = 0; i < grid.length; i++) {
			String ligne = grid[i];
			System.out.println(ligne);
		}
		Cheminement cheminement = world.getCheminement();
        directions = cheminement.path();
		JFrame frame = new JFrame("Node Runner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
>>>>>>> .r44252

        JFrame frame = new JFrame("Node Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel textLabel = new JLabel("WASD pour bouger, Z et C pour creuser", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));

        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);


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
                    case 'p':
                        world.print();
                        break;
                }
//                 simulation.nextStep(direction);
//                directions.add(direction);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                direction = Direction.NONE;
            }
        });

<<<<<<< .mine
        world.print();
||||||| .r44237
	@Override
	public Move next(int x, int y) {
		 System.out.println("Position du runner : (" + x + ", " + y + ")");
		// world.print();
		world.move(direction);
		// world.setRunnerPosition(new SVector3d(x, y));
=======
    @Override
    public Move next(int x, int y) {
//        System.out.println("Position du runner : (" + x + ", " + y + ")");
//        world.print();
        world.move(direction);
//        world.setRunnerPosition(new SVector3d(x, y));
>>>>>>> .r44252

<<<<<<< .mine
||||||| .r44237
		// int direction = 0;//(int) (Math.random() * 4 + 1);
=======
//        int direction = 0;//(int) (Math.random() * 4 + 1);
>>>>>>> .r44252

<<<<<<< .mine
        System.out.println(world.dontComeBack + " AFD ADSF ");
        Simulation simulation = new Simulation(world, new SVector3d(0.0, 5.0));//world.getPorte().getPosition());
        ArrayList<Direction> asdf = simulation.simulate();
        System.out.println(asdf);
//        System.out.println(world.dontComeBack + " AFD ADSF ");
//        directions = asdf;
||||||| .r44237
		// Direction dir = Direction.fromInt(direction);
		Cheminement cheminement = world.getCheminement();
		directions = cheminement.chemin(world.getRunnerObject().getPosition(), world.getPorte().getPosition(),Direction.RIGHT);
		// Direction dir = direction;
		directions.add(direction);
		Direction dir = executerNext(directions);
=======
//        Direction dir = Direction.fromInt(direction);
        
//        Direction dir = direction;
        directions.add(direction);
        Direction dir = executerNext(directions);
>>>>>>> .r44252

<<<<<<< .mine
||||||| .r44237
		// System.out.println("\n\n\n");
		return new Move(Event.MOVE, dir);
	}
=======
//        System.out.println("\n\n\n");
        return new Move(Event.MOVE, dir);
    }
    public Direction executerNext(ArrayList<Direction> directions ) {
    	if(directions.isEmpty()==false) {
    		return directions.remove(0);
    	}
    	return Direction.NONE;
    }
}
>>>>>>> .r44252

<<<<<<< .mine
    }



    @Override
    public Move next(int x, int y) {
//		 System.out.println("Position du runner : (" + x + ", " + y + ")");
        // world.setRunnerPosition(new SVector3d(x, y));

        // int direction = 0;//(int) (Math.random() * 4 + 1);

        // Direction dir = Direction.fromInt(direction);


//		Cheminement cheminement = world.getCheminement();
//		directions = cheminement.chemin(world.getRunnerObject().getPosition(), world.getPorte().getPosition(),Direction.RIGHT);


        Direction dir = direction;
//        directions.add(direction);
//        Direction dir = executerNext(directions);

        boolean xd = world.move(dir);
//        System.out.println(xd);

        if (!xd) {
            dir = Direction.NONE;
        }
        else{

            if (direction != Direction.NONE) {
                world.print();
            }

            if(world.getPorte().getPosition().equals(world.getRunnerObject().getPosition())){
                System.out.println("ADFJ JDFJADF JASDJF AJD FA FASDF JASDF JASDJFASDJF AJSDFJASDJFASJDFA##JSFD");
            }
        }

//        System.out.println(world.move(dir));
        // System.out.println("\n\n\n");
        return new Move(Event.MOVE, dir);
    }

    public Direction executerNext(ArrayList<Direction> directions) {
        if (directions.isEmpty() == false) {
            return directions.remove(0);
        }
        return Direction.NONE;
    }

}
||||||| .r44237
	public Direction executerNext(ArrayList<Direction> directions) {
		if (directions.isEmpty() == false) {
			return directions.remove(0);
		}
		return Direction.NONE;
	}
    
}
=======
>>>>>>> .r44252
