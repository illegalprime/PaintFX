import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;

/**
 * Wrapper class to hold the current Tool
 * This is needed because the lamba that is used in the
 * event handler calls the object's and not the references methods.
 * This will make switching tools easier
 * @author Michael Eden
 * @version 0.1
 */
public class CurrentTool extends BetterTool {

    /**
     * The current tool, defaults to the Dot Tool
     */
    private BetterTool currTool = ToolBox.getTool(ToolBox.Tools.DOT);

    /**
     * Wrapper function for the currTool's events
     * @param e On Press Events
     * @param g Context in which to draw
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        currTool.onPress(e, g);
    }

    /**
     * Wrapper function for the currTool's events
     * @param e On Drag Events
     * @param g Context in which to draw
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        currTool.onDrag(e, g);
    }

    /**
    * Wrapper function for the currTool's events
    * @param e On Release Events
    * @param g Context in which to draw
    */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        currTool.onRelease(e, g);
    }

    /**
     * Determines whether to first draw on the Preview
     * Canvas.
     * @return true if the current tool needs a preview
     */
    public boolean doPreview() {
        return currTool.doPreview();
    }

    /**
     * Gets the name of the current tool
     * @return the current tool's name
     */
    public String getName() {
        return currTool.getName();
    }

    /**
     * Important part: set a tool to be the current one
     * @param tool enum to specify the tool to become the next tool
     */
    public void setCurrTool(ToolBox.Tools tool) {
        currTool = ToolBox.getTool(tool);
    }
}
