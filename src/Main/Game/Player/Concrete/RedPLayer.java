package Main.Game.Player.Concrete;

import Main.Game.Player.Contracts.Player;

public class RedPLayer extends Player {

    public RedPLayer(int x, int y){
        super(x, y);
        setSprite("TeleportPlayer");
    }
}
