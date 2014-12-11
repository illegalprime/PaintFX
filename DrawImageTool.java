import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Draws an image on the Canvas with a preview
 * @author Michael Eden
 * @version 0.1
 */
public class DrawImageTool extends BetterTool {
    private Image image;
    private WritableImage masked;
    private String name;

    private double startX;
    private double startY;

    /**
     * Create a DrawImageTool with a tool name and an image to draw
     * @param image Image to draw on the Canvas
     * @param name Tool name (for fake tools)
     */
    public DrawImageTool(Image image, String name) {
        this.image = image;
        this.name  = name;
    }

    /**
     * When the mouse button is pressed the image will be loaded and coated
     * with the current Fill Color.
     * @param e Mouse press event
     * @param g Context on which to draw on
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        masked =
            new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = masked.getPixelWriter();
        PixelReader reader = image.getPixelReader();
        Color fillColor;

        if (g.getFill() instanceof Color) {
            fillColor  = (Color) g.getFill();
        } else {
            return;
        }

        for (int i = 0; i < image.getWidth() * image.getHeight(); ++i) {
            int x = i % (int) image.getWidth();
            int y = i / (int) image.getWidth();
            if (reader.getColor(x, y).equals(Color.TRANSPARENT)) {
                writer.setColor(x, y, Color.TRANSPARENT);
            } else {
                writer.setColor(x, y, fillColor);
            }
        }

        startX = e.getX();
        startY = e.getY();
    }

    /**
     * Draws an image on the preview canvas
     * @param e Mouse press event
     * @param g Context on which to draw on
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        drawImage(e, g);
    }

    /**
     * Draws an image on the primary Canvas
     * @param e Mouse press event
     * @param g Context on which to draw on
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        drawImage(e, g);
    }

    /**
     * Draws an image on the specified Canvas starting at the
     * onPress coordinates and ending at the mouse's
     * @param e Mouse event whose coordinates this will use
     * @param g Context on which to draw on
     */
    private void drawImage(MouseEvent e, GraphicsContext g) {
        double width  = Math.abs(e.getX() - startX);
        double height = Math.abs(e.getY() - startY);
        double posX   = Math.min(startX, e.getX());
        double posY   = Math.min(startY, e.getY());
        g.drawImage(masked, posX, posY, width, height);
    }

    /**
     * Returns true
     * This tool needs to be drawn on the preview canvas before finally
     * being drawn on the primary one.
     * @return true
     */
    public boolean doPreview() {
        return true;
    }

    /**
     * Gets the tool's name that was specified in the constructor
     * @return name of the tool
     */
    public String getName() {
        return name;
    }
}
