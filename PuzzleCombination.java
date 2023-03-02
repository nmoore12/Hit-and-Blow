import java.awt.*;

public class PuzzleCombination {
    public static final int cellSize = 40;
    private static final Color[] idToColor = {Color.white, Color.black, Color.red, Color.orange, Color.blue, Color.green};
    public static final int numOFColors = idToColor.length;
    public static int comboLength = 4;
    public static final int panelWidth = comboLength * cellSize;
    private int[] combination;
    private int xOffset;
    private int yOffset;

    public PuzzleCombination(int[] combination, int xOffset, int yOffset) {
        this.combination = combination;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public PuzzleCombination(PuzzleCombination puzzleCombo) {
        this.combination = new int[puzzleCombo.combination.length];

        for(int i=0; i<combination.length; i++) {
            combination[i] = puzzleCombo.combination[i];
        }

        this.xOffset = puzzleCombo.xOffset;
        this.yOffset = puzzleCombo.yOffset;
    }

    public void cycleCombo(int position, boolean cycleUp) {
        if(cycleUp) {
            combination[position]++;
            combination[position] = combination[position] % numOFColors;
        } else {
            combination[position]--;
            if(combination[position] < 0) {
                combination[position] = numOFColors + 1;
            }
        }
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int[] getCombo() {
        return combination;
    }

    public void paint(Graphics g) {
        for(int i=0; i<combination.length; i++) {
            g.setColor(idToColor[combination[i]]);
            g.fillOval(xOffset + i * cellSize, yOffset, cellSize, cellSize);
        }
    }
}
