import java.awt.*;

public class SimpleButton {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Color bgColor;
    protected boolean draw;
    protected int actionCode;

    public SimpleButton(int x, int y, int width, int height, Color bgColor, boolean draw, int actionCode){
        this.x = x;
        this. y = y;
        this.width = width;
        this.height = height;
        this.bgColor = bgColor;
        this.draw = draw;
        this.actionCode = actionCode;
    }

    //tests if mouse coord is in bounds of button
    public boolean isClicked(int mouseX, int mouseY) {
        return !(mouseX < x || mouseY < y || mouseX > x+width || mouseY > y+height);
    }

    public int getActionCode() {
        return actionCode;
    }

    public void paint(Graphics g) {
        if(!draw) {
            return;
        }
        g.setColor(bgColor);
        g.fillRect(x,y,width,height);
    }
}