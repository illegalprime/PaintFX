import javafx.scene.image.Image;

/**
 * A class to manage every different Tool
 * @author Michael Eden
 * @version 0.1
 */
public class ToolBox {
    /**
     * Holds every tool
     */
    private static BetterTool[] tools = {
        new RectangleTool(),
        new DrawImageTool(new Image("emptyRect.png"), "Empty Rectangle Tool"),
        new DrawImageTool(new Image("emptyCirc.png"), "Empty Circle Tool"),
        new DotTool(),
        new LineTool(),
        new DrawImageTool(new Image("bucket.png"), "Paint Bucket Tool"),
        new SprayPaintTool(),
        new FreeformTool()
    };

    /**
     * Retreives the tool specified by the enum
     * @param id Enum representing a Tool
     * @return the object representing the Tool
     */
    public static BetterTool getTool(Tools id) {
        return tools[id.ordinal()];
    }

    /**
     * Enum to keep the indices of every tool in the toolbox,
     * this ensures no one is confused or has to remember indices.
     * @author Michael Eden
     * @version 0.1
     */
    public static enum Tools {
        RECTANGLE, EMPTY_RECTANGLE, EMPTY_CIRCLE, DOT, LINE, PAINT_BUCKET,
        SPRAY_PAINT, FREEFORM
    }
}
