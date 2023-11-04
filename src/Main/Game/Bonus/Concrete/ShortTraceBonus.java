package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Game.Game;
import Main.Game.Player.Contracts.Player;

public class ShortTraceBonus extends TimedBonus{

    public ShortTraceBonus(int x, int y) {
        super(x, y,5000);
        setSprite("ShortTraceBonus");
    }

    @Override
    public void reaction(Player player) {
        super.reaction();
        if(player == Game.player1){
            Game.player2.shortTraceBonusReaction(1, durationTime);
        }else{
            Game.player1.shortTraceBonusReaction(1, durationTime);
        }
    }
}
