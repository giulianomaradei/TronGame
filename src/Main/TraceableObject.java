package Main;

abstract public class TraceableObject extends GameObject{

    public TraceableObject(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y);
        this.lastPosition = new Point(x, y);
    }

    private Point lastPosition;
    private int lastAngle = 0, currentAngle = 0;

    public void setLastPosition(int x, int y){
        this.lastPosition.setX(x);
        this.lastPosition.setY(y);
    }

    public Point getLastPosition(){
        return this.lastPosition;
    }

    public int getLastAngle(){
        return this.lastAngle;
    }

    public int getCurrentAngle(){
        return this.currentAngle;
    }

    public void setCurrentAngle(int angle){
        this.lastAngle = this.currentAngle;
        this.currentAngle = angle;
    }
}
