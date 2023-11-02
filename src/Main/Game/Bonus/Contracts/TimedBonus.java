package Main.Game.Bonus.Contracts;

abstract public class TimedBonus extends Bonus {

    int durationTime = 0;
    public TimedBonus(int x, int y, int durationTime) {
        super(x, y);
        this.durationTime = durationTime;
    }

    public int getDurationTime(){
        return this.durationTime;
    }
}
