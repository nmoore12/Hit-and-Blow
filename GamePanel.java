import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener {
    public enum GameState {Playing, GameWon, GameLost}

    private static final int maxAttepmts = 12;
    public static final int panelHeight = maxAttepmts * PuzzleCombination.cellSize;

    private List<AttemptPanel> attempts;
    private AttemptPanel currentAttempt;
    private PuzzleCombination solution;
    private Random rand;

    private List<SimpleButton> buttons;
    private SimpleTextButton submitButton;
    private GameState gameState;
    private GameEndedPanel gameEndedPanel;

    private boolean showCheat = false;

    public GamePanel() {
        setPreferredSize(new Dimension(AttemptPanel.panelWidth, panelHeight));
        setBackground(new Color(255, 176, 176));
        rand = new Random();
        createButtons();
        gameEndedPanel = new GameEndedPanel();
        restart();
        addMouseListener(this);
    }

    public void restart() {
        attempts = new ArrayList<>();
        currentAttempt = null;
        generalSolution();
        addNewAttempt();
        gameState = GameState.Playing;
    }

    public void addNewAttempt() {
        if(currentAttempt == null) {
            currentAttempt = new AttemptPanel(solution, panelHeight-PuzzleCombination.cellSize);
        } else {
            for(AttemptPanel attemptPanel : attempts) {
                attemptPanel.moveUp();
            }

            currentAttempt = new AttemptPanel(new PuzzleCombination(attempts.get(attempts.size() - 1).getPuzzleCombo()), attempts.size() + 1, solution, panelHeight - PuzzleCombination.cellSize);
        }
        attempts.add(currentAttempt);
    }

    public void generalSolution() {
        int[] solutionValues = new int[PuzzleCombination.comboLength];
        for(int i=0; i<solutionValues.length; i++) {
            solutionValues[i] = rand.nextInt(PuzzleCombination.numOFColors);
        }
        solution = new PuzzleCombination(solutionValues, AttemptPanel.panelWidth/2 - PuzzleCombination.panelWidth/2, panelHeight/2);
        gameEndedPanel.setSolution(solution);
    }

    public void toggleCheat() {
        showCheat = !showCheat;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        for(int i=1; i < maxAttepmts; i++){
            g.drawLine(0, i*PuzzleCombination.cellSize, AttemptPanel.panelWidth, i*PuzzleCombination.cellSize);
        }

        for(AttemptPanel attemptPanel : attempts) {
            attemptPanel.paint(g);
        }

        if(gameState == GameState.Playing) {
            submitButton.paint(g);
        } else {
            gameEndedPanel.paint(g);
        }
        if(showCheat) {
            solution.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gameState != GameState.Playing) return;

        int x = e.getX();
        int y = e.getY();

        if(submitButton.isClicked(x,y)) {
            handleSubmitClicked();
        } else {
            checkForCycleButton(x,y,e.getButton() == MouseEvent.BUTTON1);
        }
        repaint();
    }

    private void createButtons() {
        buttons = new ArrayList<>();
        int y = panelHeight - PuzzleCombination.cellSize;
        submitButton = new SimpleTextButton(AttemptPanel.textOffsetWidth + PuzzleCombination.panelWidth, y, ResultPanel.panelWidth, PuzzleCombination.cellSize, Color.BLACK, true, -1, "submit", Color.white, new Font("Comic Sans", Font.BOLD, 40));
        int x = AttemptPanel.textOffsetWidth;

        for(int i=0; i<PuzzleCombination.comboLength; i++){
            buttons.add(new SimpleButton(x,y,PuzzleCombination.cellSize, PuzzleCombination.cellSize, Color.pink, true, i));
            x += PuzzleCombination.cellSize;
        }
    }

    //Checks if current attmept matches solution
    private void handleSubmitClicked() {
        if(currentAttempt.getisWon()) {
            gameState = GameState.GameWon;
            gameEndedPanel.setVictory(true);
        } else if(attempts.size() < maxAttepmts) {
            addNewAttempt();
        } else {
            gameState = GameState.GameLost;
            gameEndedPanel.setVictory(false);
        }
    }

    private void checkForCycleButton(int x, int y, boolean isLeft) {
        for(SimpleButton button : buttons) {
            if(button.isClicked(x,y)) {
                currentAttempt.cycleCombo(button.getActionCode(), isLeft);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
