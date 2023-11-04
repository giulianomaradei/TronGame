package Main.Game.Scenes.Concrete;

public class RenderThread extends Thread {
    private GameFrame gameFrame;

    public RenderThread(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        while (true) {
            gameFrame.repaint();
            try {
                Thread.sleep(40); // Controle a taxa de atualização (40ms neste exemplo)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
