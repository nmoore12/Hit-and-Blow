import java.awt.*;

public class SimpleTextButton extends SimpleButton {
    private Font font;
    private String text;
    private Color textColor;

    public SimpleTextButton(int x, int y, int width, int height, Color bgColor, boolean draw, int actionCode, String text, Color textColor, Font font) {
        super(x,y,width,height,bgColor,draw,actionCode);
        this.text = text;
        this.textColor = textColor;
        this.font = font;
    }

    @Override
    public void paint(Graphics g) {
        if(!draw) {
            return;
        }
        super.paint(g);
        g.setColor(textColor);
        g.setFont(font);
        int fontWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x+width/2-fontWidth/2, y+35);
    }
}