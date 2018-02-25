package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class XdSimulation {
    private World world;


    public XdSimulation(String[] grid) {
        world = new World(grid);
    }






    public boolean simulate(ArrayList<Direction> directionList) {

        SVector3d runnerPos = getWorld().getRunnerObject().getPosition();
        for (int i = 0; i < directionList.size(); i++) {
            Direction direction = directionList.get(i);
            System.out.println(direction + " : " + world.canMove(direction) + " : " + World.directionToVector(direction));
            if (world.canMove(direction)) {
                SVector3d nextPos = runnerPos.add(World.directionToVector(direction));
                world.moveRunner(nextPos);
                runnerPos = nextPos;

                WorldObject objectAtPos = world.get(runnerPos);
                if(objectAtPos.getType() == TypeObjet.FRIC){
                    world.getFricList().remove(objectAtPos);
                    world.change(runnerPos, new WorldObject());
                }
            }
            world.printString();
            System.out.println(world.getFricList() + " + " +world.getFricList().isEmpty());
            System.out.println("--------------\n\n\n");


            world.setActualRunPos(runnerPos);
        }


        return world.get(runnerPos).getType() == TypeObjet.SORTIE && world.getFricList().isEmpty();
    }




    public World getWorld() {
        return world;
    }

    public static void main(String[] args) {

        String[] grid = {
                "          ",
                " $  H  $  ",
                " ###H###  ",
                "    H     ",
                "$& $H  S  ",
                "@@@@@@@@@@"
        };
       /* String[] grid = {"         ",
                "         ",
                "S $  H   ",
                "#####H   ",
                "     H   ",
                "& $  H $ ",
                "@@@@@@@@@",
        };*/
        XdSimulation xdSimulation = new XdSimulation(grid);
        World world2 = new World(xdSimulation.getWorld().getStringList());
//        Cheminement cheminement = new Cheminement(world2, world2.getRunnerObject(),world2.getPorte() );
//                xdSimulation.getWorld().getCheminement();
//        ArrayList<Direction> directions = cheminement.path();
//        ArrayList<Direction> directions = new ArrayList<>();

        Simulation sim = new Simulation(grid);


//        SVector3d playerPos = xdSimulation.getRunnerObject().getPosition();
//        xdSimulation = new XdSimulation(xdSimulation.getGrid());
//        xdSimulation.getWorld().initPlayer(playerPos);


        while(!xdSimulation.simulate(xdSimulation.goToDoor(sim))){
            xdSimulation.simulate(xdSimulation.goToFric(sim));
            if(xdSimulation.simulate(xdSimulation.goToDoor(sim))){
                break;
            }
            xdSimulation.simulate(xdSimulation.goToLadder(sim));
            xdSimulation.simulate(xdSimulation.goUpLadder(sim));
        }

//        xdSimulation.simulate(xdSimulation.goToLadder(sim));
//        xdSimulation.simulate(xdSimulation.goUpLadder(sim));
//        xdSimulation.simulate(xdSimulation.goToFric(sim));
//        xdSimulation.simulate(xdSimulation.goToDoor(sim));
        /*
        ArrayList<Direction> dir = sim.goToLadder(xdSimulation.getGrid(), xdSimulation.getRunnerObject());

        xdSimulation.simulate(dir);


        ArrayList<Direction> dir2 = sim.goUpLadder(xdSimulation.getGrid(), xdSimulation.getRunnerObject());
        xdSimulation.simulate(dir2);

        ArrayList<Direction> dir3 = sim.goToFric(xdSimulation.getGrid(), xdSimulation.getRunnerObject());
        xdSimulation.simulate(dir3);

        ArrayList<Direction> dir4 = sim.goToDoor(xdSimulation.getGrid(), xdSimulation.getRunnerObject());
        xdSimulation.simulate(dir4);*/
    }

    public ArrayList<Direction> goToFric(Simulation sim){
        ArrayList<Direction> direction1 = sim.goToFric(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goToDoor(Simulation sim){
        ArrayList<Direction> direction1 = sim.goToDoor(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }
    public ArrayList<Direction> goToLadder(Simulation sim){
        ArrayList<Direction> direction1 = sim.goToLadder(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }
    public ArrayList<Direction> goUpLadder(Simulation sim){
        ArrayList<Direction> direction1 = sim.goUpLadder(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public void reset(){

    }

    public RunnerObject getRunnerObject(){
        return getWorld().getRunnerObject();
    }
    public String[] getGrid(){

        return getWorld().getStringList();
    }
}

