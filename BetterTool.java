/**
 * A Tool that can report whether it needs a preview or not.
 * @author Michael Eden
 * @version 0.1
 */
public abstract class BetterTool implements Tool {
    /**
     * Sets whether the tool output needs to first be rendered
     * to the preview canvas before finally to the primary one.
     * @return true if the tool should be rendered to preview first
     * this causes onPress and onDrag to have get a preview context
     * while on release will always get the primary one.
     */
    abstract boolean doPreview();
}

