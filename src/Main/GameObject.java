package Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class GameObject extends Point implements Collidable {

    private String spriteUrl;
    private BufferedImage sprite;

    public GameObject(String spriteUrl, int x, int y){
        setXY(x, y);

        try {
            sprite = ImageIO.read(new File(spriteUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
