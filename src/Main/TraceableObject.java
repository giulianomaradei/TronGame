package Main;

abstract public class TraceableObject extends GameObject{
    private Point lastPosition;
    private int lastAngle = 270, currentAngle = 270;
    public TraceableObject(int x, int y) {
        super(x, y);
        this.lastPosition = new Point(x, y);
    }

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
