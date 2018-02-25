package hola;

import main.java.ca.umontreal.iro.hackathon.loderunner.Direction;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class XdSimulation {
    private World world;
    private ArrayList<Direction> directions = new ArrayList<>();


    public XdSimulation(String[] grid) {
        world = new World(grid);
    }


    public void simulateGravity(SVector3d runnerPos) {
        runnerPos = getWorld().getRunnerObject().getPosition();
        SVector3d gravity = runnerPos.add(World.directionToVector(Direction.DOWN));
        while (world.canMove(Direction.DOWN) && world.isInBounds(gravity) && world.get(gravity).getType() == TypeObjet.ESPACE) {
            System.out.println((world.get(runnerPos).getType() == TypeObjet.ECHELLE) + " PPPPP");
            if (world.get(runnerPos).getType() != TypeObjet.CORDE) {
                SVector3d nextPos = runnerPos.add(World.directionToVector(Direction.DOWN));
                directions.add(Direction.DOWN);
                world.initPlayer(nextPos);
                runnerPos = nextPos;


                WorldObject objectAtPos = world.get(runnerPos);


                if (objectAtPos.getType() == TypeObjet.FRIC) {
                    world.getFricList().remove(objectAtPos);
                    world.change(runnerPos, new WorldObject());
                }
                gravity = world.getActualRunPos().add(World.directionToVector(Direction.DOWN));

            } else {
                break;
            }
        }
    }

    public boolean simulate(ArrayList<Direction> directionList) {

        SVector3d runnerPos = getWorld().getRunnerObject().getPosition();
        for (int i = 0; i < directionList.size(); i++) {
            Direction direction = directionList.get(i);
            System.out.println(direction + " : " + world.canMove(direction) + " : " + World.directionToVector(direction));
            if (world.canMove(direction)) {


                SVector3d nextPos = runnerPos.add(World.directionToVector(direction));
                directions.add(direction);
                world.moveRunner(nextPos);
                runnerPos = nextPos;


                WorldObject objectAtPos = world.get(runnerPos);


                if (objectAtPos.getType() == TypeObjet.FRIC) {
                    world.getFricList().remove(objectAtPos);
                    world.change(runnerPos, new WorldObject());
                }

                simulateGravity(runnerPos);


            }
            world.printString();
//            System.out.println(world.getFricList() + " + " +world.getFricList().isEmpty());
//            System.out.println("--------------\n\n\n");


            world.setActualRunPos(runnerPos);
        }


        return world.get(runnerPos).getType() == TypeObjet.SORTIE && world.getFricList().isEmpty();
    }


    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public World getWorld() {
        return world;
    }

    public static void main(String[] args) {


        String[] grid1 = {
                "                             ",
                "                             ",
                "   $  $     $  &           S ",
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"};

        String[] grid2 = {
                "   $$$  ------    H       ",
                "@@@@@@@@@     @@@@H       ",
                "                 @H       ",
                "  S              @H   &   ",
                "@@@@@@@@@@@@@@@@@@@@@@@@@@"
        };


        ;
        /*String[] grid = {
                "          ",
                "   $ &  S ",
                "@@@@@@@@@@"
        };*/

        String[] grid = {
                "                          ",
                "                  X       ",
                "        ------    H       ",
                "@@@@@@@@@     @@@@H       ",
                "                 @H       ",
                "  S              @H       ",
                "@@@@@@@@@@@@@@@@@@@@@@@@@@"
        };
    /*  String[] grid = {
              "                 ",
              "$ $ ----     H   ",
              "####    #####H   ",
              "            #H   ",
              "S $         #H & ",
              "@@@@@@@@@@@@@@@@@"};

              */
        /*
        String[] grid = {
                "                 ",
                "                 ",
                "$ $ ----     H   ",
                "####    #####H   ",
                "            #H   ",
                "S $         #H $&",
                "@@@@@@@@@@@@@@@@@",
        };*/


        String[] grid6 = {
                "                          ",
                "                          ",
                "               H  #######H",
                "             $#H         H",
                "              #H         H",
                "        ------#H         H",
                "       $      #H  H      H",
//                "       $      #H& H      H",
                "#########     ####H     $H",
                "                 #H      H",
                " S               #H  &   H",
                "@@@@@@@@@@@@@@@@@@@@@@@@@@"
        };
        XdSimulation xdSimulation = new XdSimulation(grid6);
        Simulation sim = new Simulation(grid6);
        xdSimulation.getWorld().printString();
        xdSimulation.simulateXd(sim, 0);


//        xdSimulation.simulateXd(sim);
//
//
//        xdSimulation.simulate(xdSimulation.goToFric(sim));
//        xdSimulation.simulate(xdSimulation.goToLadder(sim));
//        xdSimulation.simulate(xdSimulation.goUpLadder(sim));
//            xdSimulation.simulate(xdSimulation.goToRope(sim));
//
//        System.out.println(xdSimulation.goDownOne(sim).isEmpty() );
//        xdSimulation.world.printString();
//        xdSimulation.goToRope(sim);
//        xdSimulation.world.printString();
//        System.out.println("GOROPE");
//        while (!xdSimulation.goToRope(sim).isEmpty()) {
//            xdSimulation.simulate(xdSimulation.goToRope(sim));
//            if (xdSimulation.goDownOne(sim).isEmpty()) {
//                xdSimulation.simulate(xdSimulation.goToRope(sim));
//            }
//        }
//        xdSimulation.simulate(xdSimulation.goDownOne(sim));
//
//        xdSimulation.simulate(xdSimulation.goToFric(sim));
//        xdSimulation.simulate(xdSimulation.goToLadder(sim));
//        xdSimulation.simulate(xdSimulation.goUpLadder(sim));
//        xdSimulation.simulate(xdSimulation.goToFric(sim));
//        System.out.println("ROPE" + xdSimulation.goToRope(sim));
//        xdSimulation.simulate(xdSimulation.goToRope(sim));
//        xdSimulation.simulate(xdSimulation.goToRope(sim));
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

    public boolean simulate2(Simulation sim) {
       /* tryGoDownXD(sim);
        tryGoToLadder(sim);
        tryGoUpLadder(sim);
        tryGoToLadder(sim);

        tryGoUpLadderNoStop(sim);
        tryDropGauche(sim);
        tryDropGauche(sim);
*/
        return tryToDecalise(sim);
    }

    public boolean simulateXd(Simulation sim, int step) {
        System.out.println(step);
        if (step == 0) {
            tryGoToLadder(sim);
            tryGoToLadder(sim);
        }

        if (step == 1) {
            tryGoDownXD(sim);

        }
        if(step == 2){
            tryGoToLadder(sim);
            tryGoToLadder(sim);
            world.printString();
            tryGoUpLadder(sim);
            world.printString();
            tryGoUpLadderNoStop(sim);

        }


//        tryGoUpLadderNoStop(sim);
//        tryGoToFric(sim);

//        int i = 0;
       /* while(i < 10) {
            tryGoToFric(sim);
            if (world.get(world.getActualRunPos()).getType() == TypeObjet.ECHELLE) {
                tryGoUpLadder(sim);
            }
            if(world.fricsAreDown()){

                System.out.println(" PPP" + tryGoDownOnw(sim));
                tryGoDownXD(sim);
                System.out.println(sim.goDownXD(world.getStringList(), world.getRunnerObject()));
                world.printString();
                return false;
//                tryDropGauche(sim);
            }
            i++;
        }

        return true;*/
/*

        int i = 0;
        while (!this.simulate(this.goToDoor(sim)) && i < 10) {
            if (this.simulate(this.goToFric(sim))) {
                i = 0;
            }
            if (this.simulate(this.goToDoor(sim))) {
                break;
            }
            if (this.simulate(this.goToLadder(sim))) {
                i = 0;
            }

            if (this.simulate(this.goUpLadder(sim))) {
                i = 0;
            }
//            if(!xdSimulation.simulate(xdSimulation.goToDoor(sim)) && ){
//                break;
//            }
//            System.out.println(this.simulate(this.goToDoor(sim)));

            i++;
        }


        System.out.println("PG" + i);
        if (i >= 10) {
            i = 0;
//            while (!this.goToRope(sim).isEmpty() && i < 10) {
            this.simulate(this.goToRope(sim));
            this.simulate(this.goDownOne(sim));
            if (this.simulate(this.goToDoor(sim))) {

                return true;
            } else {
                if (this.simulate(this.goToLadder(sim))) {
                    i = 0;
                }

                if (this.simulate(this.goUpLadder(sim))) {
                    i = 0;
                }
                if (!this.simulate(this.goToDoor(sim))) {
                    this.simulate(this.goDownOne(sim));
                    System.out.println("ASDF");
                    this.simulate(this.goToRope(sim));
                }
            }
            if (!world.getFricList().isEmpty()) {

                return simulate2(sim);
//                return simulateXd(sim, 0);
            } else {
                return decalissePlz(sim, 0);
            }
//            }

        }*/
        return false;
    }


    public boolean tryGoDownXD(Simulation sim) {
        return this.simulate(this.goDownXD(sim));
    }

    public boolean tryDropGauche(Simulation sim) {
        return this.simulate(this.goDropGauche(sim));
    }

    public boolean tryGoToFric(Simulation sim) {
        return this.simulate(this.goToFric(sim));
    }

    public boolean tryToDecalise(Simulation sim) {
        return this.simulate(this.goToDoor(sim));
    }

    public boolean tryGoToLadder(Simulation sim) {
        return this.simulate(this.goToLadder(sim));
    }

    public boolean tryGoUpLadderNoStop(Simulation sim) {
        return this.simulate(this.goUpLadderNoStop(sim));
    }

    public boolean tryGoUpLadder(Simulation sim) {
        return this.simulate(this.goUpLadder(sim));
    }

    public boolean tryGoToRope(Simulation sim) {
        return this.simulate(this.goToRope(sim));
    }

    public boolean tryGoDownOnw(Simulation sim) {
        return this.simulate(this.goDownOne(sim));
    }

    public boolean decalissePlz(Simulation sim, int step) {

//        while (!tryToDecalise(sim)) {
        if (tryToDecalise(sim)) {
            return true;
        }
        if (step == 0 && tryGoToLadder(sim)) {
            return decalissePlz(sim, step + 1);
        } else if (step == 1 && tryGoUpLadder(sim)) {
            return decalissePlz(sim, step + 1);
        } else if (step == 2 && tryGoToRope(sim)) {
            return decalissePlz(sim, step + 1);
        } else if (step == 3 && tryGoDownOnw(sim)) {
            return decalissePlz(sim, step + 1);
        }

        return decalissePlz(sim, step + 1);
//        }

//        return true;
    }

    public ArrayList<Direction> goDropGauche(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goDropGauche(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goDownXD(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goDownXD(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goDownOne(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goDownOne(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goToFric(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goToFric(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goDownLadder(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goDownLadder(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goToDoor(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goToDoor(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goToRope(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goToRope(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goToLadder(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goToLadder(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goUpLadderNoStop(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goUpLadderNoStop(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public ArrayList<Direction> goUpLadder(Simulation sim) {
        ArrayList<Direction> direction1 = sim.goUpLadder(this.getGrid(), this.getWorld().getRunnerObject());
        return direction1;
    }

    public void reset() {

    }

    public RunnerObject getRunnerObject() {
        return getWorld().getRunnerObject();
    }

    public String[] getGrid() {

        return getWorld().getStringList();
    }
}

