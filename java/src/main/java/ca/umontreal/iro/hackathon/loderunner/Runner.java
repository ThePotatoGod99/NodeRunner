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
	public static final int START_LEVEL = 6;

	public Runner() {
		super(ROOM, START_LEVEL);
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
		Cheminement cheminement = world.getCheminement();
//        directions = cheminement.path();

//		Simulation simulation = new Simulation(world, new SVector3d());
//		directions.addAll(simulation.simulate());

		JFrame frame = new JFrame("Node Runner");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel textLabel = new JLabel("WASD pour bouger, Z et C asdfasdfpour creuser", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(300, 100));

		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.pack();

//		frame.setVisible(true);
	
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

		XdSimulation xdSimulation = new XdSimulation(grid);
		World world2 = new World(xdSimulation.getWorld().getStringList());
		Simulation sim = new Simulation(grid);


//		xdSimulation.simulateXd(sim);

		directions.addAll(xdSimulation.getDirections());

	}

    @Override
    public Move next(int x, int y) {
		world.setActualRunPos(new SVector3d((double)x, (double)y));
    //    System.out.println("Position du runner : (" + x + ", " + y + ")");
//        world.print();
        // world.move(direction);
//        world.setRunnerPosition(new SVector3d(x, y));

//        int direction = 0;//(int) (Math.random() * 4 + 1);

//        Direction dir = Direction.fromInt(direction);
        
//        Direction dir = direction;
    	Direction dir;
//        if(direction != Direction.NONE){
//            dir = direction;
//        }
//        else{

            dir = executerNext(directions);
		System.out.println(dir + " : " + directions);
//        }
		// Direction dir = direction;

//        System.out.println("\n\n\n");
        return new Move(Event.MOVE, dir);
    }
    public Direction executerNext(ArrayList<Direction> directions ) {
    	if(directions == null || directions.isEmpty()==false) {
    		return directions.remove(0);
    	}
    	world.moveRunner(world.getActualRunPos());
		world.printString();

		//TODO: i, go to door, go to frick...
//		Cheminement cheminement = world.getCheminement();
//		this.directions = cheminement.path();
//		System.out.println("FINISHED " + this.directions);
    	return Direction.NONE;
    }



}

