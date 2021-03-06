package hola;

public class RunnerObject extends WorldObject implements  Cloneable{

    public RunnerObject(){
        super(TypeObjet.RUNNER, new SVector3d());
    }

    public RunnerObject(SVector3d position) {
        this();
        this.setPosition(position);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RunnerObject xd = new RunnerObject(getPosition());
        return xd;
    }


}
