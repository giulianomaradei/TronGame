package Main;

import Main.Game.Game;

import javax.imageio.ImageIO;
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

        this.setPositionInGrid(x, y);

        try {
            sprite = ImageIO.read(new File(spriteUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(String spriteUrl) {
        try{
            sprite = ImageIO.read(new File(spriteUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePositionInGrid(int x, int y, int new_x, int new_y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        int ii = new_x / Game.cellSize;
        int jj = new_y / Game.cellSize;

        Game.grid[ii][jj] = Game.grid[i][j];
        Game.grid[i][j] = null;
    }

    public void setPositionInGrid(int x, int y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        Game.grid[i][j] = this;
    }
}
