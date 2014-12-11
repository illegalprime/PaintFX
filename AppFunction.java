import javafx.scene.paint.Color;

/**
 * Represents a function that will act on the Application GUI
 * based on what color was clicked.
 * @author Michael Eden
 * @version 0.1
 */
@FunctionalInterface
public interface AppFunction {
    /**
     * Calback for the event, this will be called when a clickable
     * segment of the application is clicked and the color clicked is
     * passed to it.
     * @param color the Color that was clicked
     */
    void fire(Color color);
}
