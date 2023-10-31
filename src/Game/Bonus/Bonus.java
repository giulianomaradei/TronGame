package Game.Bonus;

import Main.Collidable;
import Main.GameObject;
import Main.Player.Player;
import Main.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Bonus extends GameObject {
    private String spriteUrl;

    public Bonus(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y);
        setSpriteUrl(spriteUrl);

    }

    public abstract void reaction(Player player);
}
