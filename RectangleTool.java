import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * Creates a filled rectangle of arbitrary size
 * @author Michael Eden
 * @version 0.1
 */
public class RectangleTool extends BetterTool {
    private double startX;
    private double startY;

    /**
     * Records the starting position of the Rectangle
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startX = e.getX();
        startY = e.getY();
    }

    /**
     * Creates a rectangle on the preview canvas
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        drawRect(e, g);
    }

    /**
     * Creates a rectangle on the primary canvas
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        drawRect(e, g);
    }

    /**
     * Draws a rectangle, finding its relative width and height
     * each time.
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    private void drawRect(MouseEvent e, GraphicsContext g) {
        double width  = Math.abs(e.getX() - startX);
        double height = Math.abs(e.getY() - startY);
        double posX   = Math.min(startX, e.getX());
        double posY   = Math.min(startY, e.getY());
        g.fillRect(posX, posY, width, height);
    }

    /**
     * Rectangle Tool needs to be previewed.
     * @return true
     */
    public boolean doPreview() {
        return true;
    }

    /**
     * Returns the name of the tool
     * @return the name of Rectangle Tool
     */
    public String getName() {
        return "Rectangle Tool";
    }
}
