import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.HashMap;

/**
 * An abstraction of image that is able to perform actions based
 * on what color is clicked when the image is clicked.
 * @author Michael Eden
 * @version 0.1
 */
public class ByColorButton extends ImageView {
    private Map<Color, AppFunction> capabilities;
    private AppFunction defaultAction;

    /**
     * Creates an empty ByColorButton
     */
    public ByColorButton() {
        super();
        init();
    }

    /**
     * Creates a ByColorButton off of an image URL
     * @param url image path
     */
    public ByColorButton(String url) {
        super(url);
        init();
    }

    /**
     * Creates a ByColorButton out of an image Object
     * @param image an Image object
     */
    public ByColorButton(Image image) {
        super(image);
        init();
    }

    /**
     * Method for initialization standard across all
     * constructors
     */
    private void init() {
        capabilities = new HashMap<>();
        setOnMouseClicked(this::onClick);
    }

    /**
     * Default on click method that is called when this image is pressed
     * @param me the Mouse Event in this context.
     */
    public void onClick(MouseEvent me) {
        try {
            Color currArgb =
                getImage().getPixelReader().getColor(
                    (int) (me.getX() - getX()),
                    (int) (me.getY() - getY()));
            AppFunction action = capabilities.get(currArgb);
            if (action != null) {
                action.fire(currArgb);
            } else if (defaultAction != null) {
                defaultAction.fire(currArgb);
            }
        } catch (IndexOutOfBoundsException err) {
            // Mouse dragged out of window bounds.
            return;
        }
    }

    /**
     * Get the Color-to-AppFunction mappings to edit.
     * @return a HashMap connecting color clicked to a function
     */
    public Map<Color, AppFunction> getMappings() {
        return capabilities;
    }

    /**
     * Changes the function that is fired when a color is clicked that
     * has no mapping.
     * @param action Action to set to the default one
     */
    public void setDefaultAction(AppFunction action) {
        defaultAction = action;
    }
}
