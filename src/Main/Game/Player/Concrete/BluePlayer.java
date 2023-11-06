package Main.Game.Player.Concrete;

import Main.Game.Player.Contracts.Player;

public class BluePlayer extends Player {

    public BluePlayer(int x, int y){
        super(x, y);
        setSprite("JumpPlayer");
    }

    public void reaction(Player player){

    }
    
}
