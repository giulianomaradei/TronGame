package Main.Game.Player.Concrete;

import Main.Game.Player.Contracts.Player;

public class GreenPlayer extends Player {

    public GreenPlayer(int x, int y){
        super(x, y);
        setSprite("DashPlayer");
    }
}
