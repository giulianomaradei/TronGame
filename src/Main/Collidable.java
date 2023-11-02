package Main;

import Main.Player.Player;

public interface Collidable {
    public void reaction (Player player);
    public boolean collisionDetected(GameObject other);
}
