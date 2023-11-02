package Main;

import Main.Game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class GameObject extends Point implements Collidable, Renderable {

    private String spriteName;

    public GameObject(int x, int y){
        super(x, y);
        setX(x);
        setY(y);
    }

    public void updatePositionInGrid(int x, int y, int new_x, int new_y){

        int i= x / Game.cellSize;
        int j = y / Game.cellSize;

        int ii = new_x / Game.cellSize;
        int jj = new_y / Game.cellSize;

        if(ii >= Game.gridWidth || jj >= Game.gridHeight || ii < 0 || jj < 0){
            return;
        }

        Game.grid[ii][jj] = Game.grid[i][j];
        Game.grid[i][j] = null;
    }

    public void setPositionInGrid(int x, int y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(i >= Game.gridWidth || j >= Game.gridHeight || i < 0 || j < 0){
            return;
        }

        Game.grid[i][j] = this;
    }

    public void removePositionInGrid(int x, int y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        Game.grid[i][j] = null;
    }

    public void setSprite(String spriteName){
        this.spriteName = spriteName;
    }

    public BufferedImage getSprite(){
        return Game.imageCache.get(spriteName);
    }

    public String getSpriteName(){
        return spriteName;
    }

    public void render(Graphics g){
        int x = this.getX();
        int y = this.getY();
        BufferedImage sprite = this.getSprite();
        g.drawImage(sprite, x, y, null);
    }
}
