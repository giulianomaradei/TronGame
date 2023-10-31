package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class GameObject extends Point implements Collidable, Renderable {

    private String spriteUrl;
    private BufferedImage sprite;

    public GameObject(String spriteUrl, int x, int y){
        super(x, y);
        setX(x);
        setY(y);

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

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void render(Graphics g){
        int x = this.getX();
        int y = this.getY();
        g.drawImage(sprite, x, y, null);
    }
}
