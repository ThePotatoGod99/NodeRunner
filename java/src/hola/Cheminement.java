package hola;

import java.util.ArrayList;
import main.java.ca.umontreal.iro.hackathon.loderunner.*;

public class Cheminement {
	private WorldObject objetDepart;
	private WorldObject objetArrive;
	private World world;
	private ArrayList<Direction> checkpoint = new ArrayList<Direction>();
	private ArrayList<Direction> cheminFinal = new ArrayList<Direction>();
	private SVector3d avance = new SVector3d(1, 0, 0);
	private SVector3d recule = new SVector3d(-1, 0, 0);
	private SVector3d monter = new SVector3d(0, 1, 0);
	

	public Cheminement(World world, WorldObject objetDepart, WorldObject objetArrive) {
		this.world = world;
		this.objetDepart = objetDepart;
		this.objetArrive = objetArrive;
	}

	public ArrayList<Direction> chemin(SVector3d depart, SVector3d arrive, Direction deplacement) {
		SVector3d prochainPos = new SVector3d (depart.getX(),depart.getY(),0);
		if (world.isInBounds(prochainPos.add(avance))) {
			if (world.get(depart.add(avance)).equals(objetArrive)) {
				checkpoint.add(deplacement);
				cheminFinal.addAll(checkpoint);
				return cheminFinal;
			} else if (world.spaceIsAvailable(prochainPos)) {
				cheminFinal.add(deplacement);
				chemin(depart, arrive, Direction.RIGHT);
			} else {
				cheminFinal.clear();
				avance = avance.multiply(-1);
				chemin(objetDepart.getPosition(), objetArrive.getPosition(), Direction.LEFT);
			}
			
		}
		return cheminFinal;
	}
	public ArrayList<Direction> cheminAB(WorldObject objetDepart,WorldObject objetArrive){
		ArrayList<Direction> chemin = new ArrayList<Direction>();
		ArrayList<Direction> temp = new ArrayList<Direction>();
		SVector3d iterator2 = objetDepart.getPosition();
		Direction direction = Direction.RIGHT;
		SVector3d sens = avance;
		while(world.isInBounds(iterator2)) {
			for(SVector3d iterator = iterator2;world.isInBounds(iterator);iterator = iterator.add(sens)) {
				if(iterator.equals(objetArrive.getPosition())) {
					chemin.addAll(temp);
					return chemin;
				} else if(iterator.getY()!= objetArrive.getY() && world.get(iterator).getType()==TypeObjet.ECHELLE) {
					utiliserEchelle(iterator);
				}
				temp.add(direction);
			}
			temp.clear();
			direction = Direction.LEFT;
			sens = recule;
		}
		return chemin;
	}
	public void utiliserEchelle(SVector3d position) {
		
	}
	public ArrayList<Direction> path(){
		ArrayList<WorldObject> listFric = world.getFricList();
		ArrayList<Direction> finale = new ArrayList<Direction>();
		int compteur = 0;
		while(listFric.isEmpty()==false&&compteur<listFric.size()) {
			finale.addAll(cheminAB(world.getRunnerObject(),listFric.get(compteur)));
			world.moveRunner(listFric.get(compteur).getPosition());
			compteur++;
		}
		finale.addAll(cheminAB(world.getRunnerObject(),world.getPorte()));
		

		return finale;
		
	}
	

}
