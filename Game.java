import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    public static void main(String[] args) {
        Game game = new Game();
    }

    private GamePanel gamePanel;

    public Game() {
        JFrame frame = new JFrame("Hit and Blow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        gamePanel = new GamePanel();
        frame.getContentPane().add(gamePanel);

        frame.addKeyListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    //R to restart, Esp to quit, C for cheat mode
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_R) {
            gamePanel.restart();
            gamePanel.repaint();
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if(e.getKeyCode() == KeyEvent.VK_C){
            gamePanel.toggleCheat();
            gamePanel.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
