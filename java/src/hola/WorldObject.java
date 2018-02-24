package hola;

public class WorldObject {

    private SVector3d position = new SVector3d();
    private TypeObjet type;

    public WorldObject(){

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
}
