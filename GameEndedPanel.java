import java.awt.*;

public class GameEndedPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean won;
    private PuzzleCombination solution;
    private Font gameEndFont = new Font("Comic Sans", Font.BOLD, 20);

    private String win = "you won!";
    private String loss = "you lost!";
    private String endLine = "Press R to restart";
    private String endLine2 = "Solution: ";

    public GameEndedPanel() {
        this.x = AttemptPanel.panelWidth/2;
        this.y = GamePanel.panelHeight/2;
        this.width = 200;
        this.height = 100;
    }

    public void setVictory(boolean victory) {
        won = victory;
    }

    public void setSolution(PuzzleCombination solution) {
        this.solution = solution;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(255, 176, 176));
        g.fillRect(x - width/2, y - height/2 - 30, width, height+30);
        g.setFont(gameEndFont);

        int strWidth;
        if(won) {
            g.setColor(Color.white);
            strWidth = g.getFontMetrics().stringWidth(win);
            g.drawString(win, x - strWidth/2, y - 55);
        } else {
            g.setColor(Color.white);
            strWidth = g.getFontMetrics().stringWidth(loss);
            g.drawString(loss, x - strWidth/2, y - 55);
        }

        g.setColor(Color.black);
        strWidth = g.getFontMetrics().stringWidth(endLine);
        g.drawString(endLine, x - strWidth/2, y - 30);
        strWidth = g.getFontMetrics().stringWidth(endLine2);
        g.drawString(endLine2, x - strWidth/2, y - 5);
        solution.paint(g);
    }
}


