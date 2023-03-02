import java.awt.*;

/*
 * Attempt Panel Class:
 *    Single Line that contains attempt number, attempt,
 *    and results of attempt.
 */

public class AttemptPanel {
    public static final int textOffsetWidth = 50; 
    public static final Font font = new Font("Comic Sans", Font.BOLD, 40);
    public static final int panelWidth = textOffsetWidth + PuzzleCombination.panelWidth + ResultPanel.panelWidth;

    private PuzzleCombination puzzleCombo;
    private ResultPanel resultPanel;
    private String attemptNum;
    private int yOffset;

    //Create Panel with all elements to be drawn
    public AttemptPanel(PuzzleCombination puzzleCombo, int attemptNum, PuzzleCombination solution, int yOffset) {
        this.puzzleCombo = puzzleCombo;
        puzzleCombo.setYOffset(yOffset);

        resultPanel = new ResultPanel(puzzleCombo, solution, textOffsetWidth+PuzzleCombination.panelWidth, yOffset);

        this.attemptNum = String.valueOf(attemptNum);
        this. yOffset = yOffset;
    }

    //Shorter constructer to be used when there is no previous attempt
    public AttemptPanel(PuzzleCombination solution, int yOffset) {
        this(new PuzzleCombination(new int[PuzzleCombination.comboLength], textOffsetWidth, yOffset), 1, solution, yOffset);
    }

    //Cycles puzzle combination at the specified index by one, then validates the result in the result panel
    public void cycleCombo(int index, boolean cycleUp) {
        puzzleCombo.cycleCombo(index, cycleUp);
        resultPanel.validateAttempt();
    }

    public PuzzleCombination getPuzzleCombo() {
        return puzzleCombo;
    }

    public void moveUp() {
        yOffset -= PuzzleCombination.cellSize;
        puzzleCombo.setYOffset(yOffset);
        resultPanel.setYOffset(yOffset);
    }

    //tests if game has been won
    public boolean getisWon() {
        return resultPanel.getVictory();
    }

    //draws number, puzzle combo, and results
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(attemptNum, 5, yOffset+35);
        puzzleCombo.paint(g);
        resultPanel.paint(g);
    }
}
