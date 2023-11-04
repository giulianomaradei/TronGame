package Main.Game.Player.Concrete;

import Main.Game.Player.Contracts.Player;

public class DashPlayer extends Player {

    public DashPlayer(int x, int y){
        super(x, y);
        setSprite("DashPlayer");
    }

    public void reaction(Player player){

    }
    
}
