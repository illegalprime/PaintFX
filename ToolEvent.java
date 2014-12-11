import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * Functional interface to define a tool's callback method
 * @author Michael Eden
 * @version 0.1
 */
@FunctionalInterface
public interface ToolEvent {
    /**
     * A generic event handler for any tool using the tool interface
     * @param e Mouse event that is fired
     * @param g Graphics Context to draw on
     */
    void fire(MouseEvent e, GraphicsContext g);
}
