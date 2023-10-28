package Main;

abstract public class TraceableObject extends GameObject{

    public TraceableObject(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y);
        this.lastPosition = new Point(x, y);
    }

    private Point lastPosition;

    public void setLastPosition(int x, int y){
        this.lastPosition.setX(x);
        this.lastPosition.setY(y);
    }

    public Point getLastPosition(){
        return this.lastPosition;
    }
}
