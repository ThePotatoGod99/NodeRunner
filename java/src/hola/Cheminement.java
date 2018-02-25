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
    private SVector3d monter = new SVector3d(0, -1, 0);
    private SVector3d descendre = new SVector3d(0, 1, 0);
    private ArrayList<WorldObject> listFric;

    public Cheminement(World world, WorldObject objetDepart, WorldObject objetArrive) {
        this.world = world;
        this.objetDepart = objetDepart;
        this.objetArrive = objetArrive;
        listFric = world.getFricList();
    }

    public ArrayList<Direction> chemin(SVector3d depart, SVector3d arrive, Direction deplacement) {
        SVector3d prochainPos = new SVector3d(depart.getX(), depart.getY(), 0);
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

    public ArrayList<Direction> cheminAB(WorldObject objetDepart, WorldObject objetArrive) {
        ArrayList<Direction> chemin = new ArrayList<Direction>();
        ArrayList<Direction> temp = new ArrayList<Direction>();
        SVector3d iteratorY = objetDepart.getPosition();
        Direction direction = Direction.RIGHT;
        SVector3d sens = avance;
        while (world.isInBounds(iteratorY)) {
            for (SVector3d iteratorX = iteratorY; world.isInBounds(iteratorX); iteratorX = iteratorX.add(sens)) {
                //isOnOtherFric(iteratorX);
                if (iteratorX.equals(objetArrive.getPosition())) {
                    chemin.addAll(temp);
                    return chemin;
                } else if (iteratorX.getY() != objetArrive.getY() && world.get(iteratorX).getType() == TypeObjet.ECHELLE) {
                    direction = Direction.UP;
                    sens = monter;
                    for (iteratorY = iteratorX; world.isInBounds(iteratorY); iteratorY = iteratorY.add(sens)) {
                        if (iteratorY.getY() == objetArrive.getY() && (iteratorY.add(avance).equals(objetArrive.getPosition()) || iteratorY.add(recule).equals(objetArrive.getPosition()))) {
                            if (iteratorY.add(avance).equals(objetArrive.getPosition())) {
                                temp.add(Direction.RIGHT);
                                chemin.addAll(temp);
                                return chemin;
                            } else {
                                temp.add(Direction.LEFT);
                                chemin.addAll(temp);
                                return chemin;
                            }
                        } else if (iteratorY.getY() > objetArrive.getY()) {
                            if (world.get(iteratorY.add(avance)).getType() == TypeObjet.BLOC_BRIQUE || world.get(iteratorY.add(avance)).getType() == TypeObjet.BLOC_PIERRE || world.get(iteratorY.add(recule)).getType() == TypeObjet.BLOC_BRIQUE || world.get(iteratorY.add(recule)).getType() == TypeObjet.BLOC_PIERRE) {
                                temp.add(direction);
                                if (world.get(iteratorY.add(avance)).getType() == TypeObjet.BLOC_BRIQUE || world.get(iteratorY.add(avance)).getType() == TypeObjet.BLOC_PIERRE) {
                                    iteratorY = iteratorY.add(sens);
                                    direction = Direction.RIGHT;
                                    sens = avance;
                                } else {
                                    iteratorY = iteratorY.add(sens);
                                    direction = Direction.LEFT;
                                    sens = recule;
                                }
                                temp.add(direction);
                                chemin.addAll(temp);
                                temp.clear();
                                iteratorX = iteratorY;
                                break;
                            } else {
                                temp.add(direction);
                            }
                        } else if (iteratorY.getY() <= objetArrive.getY()) {

                            sens = descendre;
                            direction = Direction.DOWN;
                            if (iteratorY.getY() != objetDepart.getY() && (world.get(iteratorY.add(avance).add(descendre.multiply(2))).getType() == TypeObjet.BLOC_BRIQUE || world.get(iteratorY.add(avance).add(descendre.multiply(2))).getType() == TypeObjet.BLOC_PIERRE || world.get(iteratorY.add(recule).add(descendre.multiply(2))).getType() == TypeObjet.BLOC_BRIQUE || world.get(iteratorY.add(recule).add(descendre.multiply(2))).getType() == TypeObjet.BLOC_PIERRE)) {
                                temp.add(direction);

                                iteratorY = iteratorY.add(sens);
                                direction = Direction.RIGHT;
                                sens = avance;

                                chemin.addAll(temp);
                                temp.clear();

                                iteratorX = iteratorY;
                                break;
                            } else {
                                temp.add(direction);
                            }
                        }
                    }

                }

				/*else if(iteratorX.getY()< objetArrive.getY() && world.get(iteratorX).getType()==TypeObjet.CORDE&&(world.get(iteratorX.add(descendre)).getType()!=TypeObjet.BLOC_BRIQUE&&world.get(iteratorX.add(descendre)).getType()!=TypeObjet.BLOC_PIERRE)){
					iteratorY = iteratorX;
					sens = descendre;
					direction = Direction.DOWN;
					temp.add(direction);
					while(world.isInBounds(iteratorY.add(sens))&&(world.get(iteratorY.add(sens)).getType()!=TypeObjet.BLOC_BRIQUE||world.get(iteratorY.add(sens)).getType()!=TypeObjet.BLOC_PIERRE)) {
						iteratorY = iteratorY.add(sens);
					}
					chemin.addAll(temp);
					direction = Direction.RIGHT;
					sens = avance;
					iteratorX = iteratorY;
				}*/


                else {
                    temp.add(direction);
                }

            }
            temp.clear();
            direction = Direction.LEFT;
            sens = recule;
        }


        return chemin;
    }

    public ArrayList<Direction> goUpLadder() {
        ArrayList<Direction> directionList = new ArrayList<>();

        directionList.add(Direction.UP);
        directionList.add(Direction.UP);
        directionList.add(Direction.UP);
        return directionList;
    }

    public ArrayList<Direction> goToRope() {
        int i = 0;
        boolean result = false;
        ArrayList<Direction> directionList = new ArrayList<>();

        ArrayList<WorldObject> tempLadderList = (ArrayList) world.getRopeList().clone();
        while (i < tempLadderList.size()) {
            Simulation simulation = new Simulation(world, new SVector3d());
            ArrayList<Direction> directionsTemp = simulation.cheminAB(world.getRunnerObject(), tempLadderList.get(i));
            if (directionsTemp.size() != 0) {

                SVector3d newPos = tempLadderList.get(i).getPosition();

                    if (world.spaceIsAvailable(newPos.add(new SVector3d(0.0, 1.0)))) {

                        directionList.addAll(directionsTemp);
                        world.moveRunner(tempLadderList.get(i).getPosition());
                        tempLadderList.remove(i);
                        i--;
                        result = true;
                    }

            }
            i++;
        }


        directionList.add(Direction.DOWN);
        return directionList;
    }

    public boolean isOnLadder(SVector3d position){
        for(WorldObject ladder : world.getLadderList()){
            if(ladder.getPosition() == position){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Direction> path() {
        ArrayList<Direction> finale = new ArrayList<Direction>();
        int compteur = 0;
        while (listFric.isEmpty() == false && compteur < listFric.size()) {
            finale.addAll(cheminAB(world.getRunnerObject(), listFric.get(compteur)));
            world.moveRunner(listFric.get(compteur).getPosition());
            compteur++;
        }

        world.printString();
        System.out.println(finale);


//		finale.addAll(cheminAB(world.getRunnerObject(),world.getPorte()));

        System.out.println(world.get(world.getRunnerObject().getPosition()).getType() + " PPP " + world.getRunnerObject().getPosition());
        System.out.println("PPP" + isOnLadder(world.getRunnerObject().getPosition()));

        if(isOnLadder(world.getRunnerObject().getPosition())){ //world.get(world.getRunnerObject().getPosition()).getType().equals(TypeObjet.ECHELLE)){

            finale.addAll(goUpLadder());
        }
        ArrayList<Direction> ropeList = goToRope();

        finale.addAll(goToRope());

		finale.addAll(cheminAB(world.getRunnerObject(),world.getPorte()));
		finale.addAll(goUpLadder());
        finale.addAll(cheminAB(world.getRunnerObject(),world.getPorte()));


        return finale;

    }

    public void isOnOtherFric(SVector3d position) {
        for (int i = 0; i < listFric.size(); i++) {
            if (position.equals(listFric.get(i).getPosition())) {
                world.change(position, new WorldObject(TypeObjet.ESPACE, position));
                listFric = world.getFricList();
            }
        }
    }

    public void monterOuDescendreEchelle(SVector3d iteratorX, SVector3d iteratorY, SVector3d sens, Direction direction) {

    }


}
