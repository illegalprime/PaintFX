import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

/**
 * Creates random points in the vicinity of the mouse click
 * @author Michael Eden
 * @version 0.1
 */
public class SprayPaintTool extends BetterTool {
    private static final Random PRNG = new Random();

    /**
     * Create random dots in a vicinity of 10 pixels
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        sprayPaint(e, g);
    }

    /**
     * Create random dots in a vicinity of 10 pixels
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        sprayPaint(e, g);
    }

    /**
     * Stops the spray painting.
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        return;
    }

    /**
     * Get the current position and draw 25 random pixels in an
     * area of 100 (10 x 10).
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    private void sprayPaint(MouseEvent e, GraphicsContext g) {
        double currX = e.getX() - 10;
        double currY = e.getY() - 10;

        for (int i = 0; i < 25; ++i) {
            drawPoint(currX + PRNG.nextInt(20), currY + PRNG.nextInt(20), g);
        }
    }

    /**
     * Draw a singular point in the context's fill color
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @param g Graphics context on which to draw
     */
    private void drawPoint(double x, double y, GraphicsContext g) {
        g.fillRect(x, y, 1, 1);
    }

    /**
     * Spray painting does not need a preview
     * @return false
     */
    public boolean doPreview() {
        return false;
    }

    /**
     * Get the name of the tool
     * @return the tool's name
     */
    public String getName() {
        return "Spray Paint Tool";
    }
}
