import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;
import java.util.LinkedList;

/**
 * Tool to draw Freeform polygons of as many sides
 * as there are mouse drag events. Basically a custom fill.
 * @author Michael Eden
 * @version 0.1
 */
public class FreeformTool extends BetterTool {
    private List<Double> coordinatesX;
    private List<Double> coordinatesY;
    private int points;

    /**
     * Creates a list which will contain all points to be drawn
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        coordinatesX = new LinkedList<>();
        coordinatesY = new LinkedList<>();
        coordinatesX.add(e.getX());
        coordinatesY.add(e.getY());
        points = 1;
    }

    /**
     * Adds to the list of points and draws the polygon in the
     * preview canvas.
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        coordinatesX.add(e.getX());
        coordinatesY.add(e.getY());
        ++points;
        g.fillPolygon(toArr(coordinatesX), toArr(coordinatesY) , points);
    }

    /**
     * Draws to the final Canvas a freeform blob.
     * @param e Mouse Event that was fired
     * @param g Graphics context on which to draw
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.fillPolygon(toArr(coordinatesX), toArr(coordinatesY) , points);
    }

    /**
     * Creates an array of primitive types out of a list.
     * This is done because of Java's type erasure.
     * @param list list to convert
     * @return new array of double with the contents of list
     */
    private double[] toArr(List<Double> list) {
        double[] converted = new double[points];
        int i = 0;

        for (Double d : list) {
            converted[i++] = d;
        }
        return converted;
    }

    /**
     * Returns true, this tool needs a preview canvas
     * @return true
     */
    public boolean doPreview() {
        return true;
    }

    /**
     * Returns the name of the tool
     * @return name of the tool
     */
    public String getName() {
        return "Freeform Tool";
    }
}
