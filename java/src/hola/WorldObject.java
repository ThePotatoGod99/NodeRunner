package hola;

public class WorldObject implements  Cloneable{

    private SVector3d position = new SVector3d();
    private TypeObjet type;

    public WorldObject(){
        this.type = TypeObjet.ESPACE;

    }

    public WorldObject(SVector3d position){
        this.position = position;
        this.type = TypeObjet.ESPACE;
    }
    public WorldObject (TypeObjet type, SVector3d position) {
    	this.type = type;
    	this.position = position;
    }
    

    public TypeObjet getType() {
    	return type;
    }

    public SVector3d getPosition() {
        return position;
    }

    public void setPosition(SVector3d position) {
        this.position = position;
    }

    public void setType(TypeObjet type) {
        this.type = type;
    }

    public int getX(){
        return (int) position.getX();
    }
    public int getY(){
        return (int) position.getY();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        WorldObject xd = new WorldObject();
        xd.position = new SVector3d(position.getX(), position.getY());
        xd.type = this.getType();

        return xd;
    }
    
    public boolean equals (WorldObject objet) {
    	if(this.getType().equals(objet.getType()) && this.getX() == objet.getX() && this.getY() == objet.getY()) {
    		return true;
    	}
    	return false;
    }

    @Override
    public String toString() {
        switch (type) {
            case ECHELLE:
                return "H";
            case SORTIE:
                return "S";
            case FRIC:
                return "$";
            case CORDE:
                return "-";
            case ESPACE:
                return " ";
            case RUNNER:
                return "&";
            case BLOC_BRIQUE:
                return "#";
            case BLOC_PIERRE:
                return "@";
            case VISITED:
                return "X";
        }
        return super.toString();
    }
}
