import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * Tool that draws lines
 * @author Michael Eden
 * @version 0.1
 */
public class LineTool extends BetterTool {
    /**
     * Creates a new path and moves to the mouse coordinates
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        g.beginPath();
        g.moveTo(e.getX(), e.getY());
    }

    /**
     * Redraws the line at each step, adding the new points to the
     * path.
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.lineTo(e.getX(), e.getY());
        g.stroke();
    }

    /**
     * ends the drawing
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.closePath();
    }

    /**
     * Line Tool does not need to be previewed.
     * @return false
     */
    public boolean doPreview() {
        return false;
    }

    /**
     * Get name
     * @return The name of this tool
     */
    public String getName() {
        return "Line Tool";
    }
}
