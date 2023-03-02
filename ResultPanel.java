import java.awt.*;

public class ResultPanel {
    public static final int panelWidth = 300;
    private PuzzleCombination puzzleAttempt;
    private PuzzleCombination solution;
    private boolean victory = false;
    private int xOffset;
    private int yOffset;

    private Font font = new Font("Comic Sans", Font.BOLD, 20);
    private String hitStr = " Hits: ";
    private String blowStr = " Blows: ";
    private int hit = 0;
    private int blows = 0;

    public ResultPanel(PuzzleCombination puzzleAttempt, PuzzleCombination solution, int xOffset, int yOffset) {
        this.puzzleAttempt = puzzleAttempt;
        this.solution = solution;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        validateAttempt();
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public void validateAttempt() {
        victory = false;

        int[] excessCounter = new int[PuzzleCombination.numOFColors];
        int[] unusedSolCounter = new int[PuzzleCombination.numOFColors];
        hit = 0;

        for(int i=0; i<PuzzleCombination.comboLength; i++) {
            if(solution.getCombo()[i] == puzzleAttempt.getCombo()[i]) {
                hit++;
            } else {
                excessCounter[puzzleAttempt.getCombo()[i]]++;
                unusedSolCounter[solution.getCombo()[i]]++;
            }
        }

        blows = 0;

        for(int i=0; i<PuzzleCombination.numOFColors; i++) {
            blows += Math.min(unusedSolCounter[i], excessCounter[i]);
        }

        if(hit == PuzzleCombination.comboLength) {
            victory = true;
        }
    }

    public boolean getVictory() {
        return victory;
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(hitStr + hit, xOffset, yOffset+17);
        g.drawString(blowStr + blows, xOffset, yOffset+37);
    }
}
