import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * A simple class to draw dots on an App
 * @author Michael Eden
 * @version 0.1
 */
public class DotTool extends BetterTool {
    /**
     * Creates a dot at the current mouse location
     * @param e On Release Events
     * @param g Context in which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        drawDot(e, g);
    }

    /**
     * Draws a dot at the current mouse position
     * @param e On Release Events
     * @param g Context in which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        drawDot(e, g);
    }

    /**
     * Stops this dot nonsense, does not draw anything.
     * @param e On Release Events
     * @param g Context in which to draw
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        return;
    }

    /**
     * Draws a singular dot on the canvas at the mouses coordinates
     * @param e On Release Events
     * @param g Context in which to draw
     */
    private void drawDot(MouseEvent e, GraphicsContext g) {
        g.fillOval(e.getX(), e.getY(), 5, 5);
    }

    /**
     * The dot tool does not need a preview
     * @return false
     */
    public boolean doPreview() {
        return false;
    }

    /**
     * Get the name of this tool
     * @return the name of the tool
     */
    public String getName() {
        return "Dot Tool";
    }
}
