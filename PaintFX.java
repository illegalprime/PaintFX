import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;

/**
 * This entire program was drawn and written by the author,
 * all of its resources are free to distribute freely.
 * To learn more about Software Licensing, visit the LUG@GT
 * Klaus 2456 at 7:00 PM!
 * @author Michael Eden
 * @version 0.1
 */
public class PaintFX extends Application {

    private static final double INT_WIDTH  = 822;
    private static final double INT_HEIGHT = 700;
    private static final double WIN_WIDTH  = 876;
    private static final double WIN_HEIGHT = 761;

    private Canvas  primary      = new Canvas(INT_WIDTH, INT_HEIGHT);
    private Canvas  preview      = new Canvas(INT_WIDTH, INT_HEIGHT);
    private Canvas  colorPeek;
    private Pane    controls;
    private ToolBox tools           = new ToolBox();
    private CurrentTool currentTool = new CurrentTool();
    private Color   currentColor;
    private StackPane uiStack;
    private ImageView windowDecoration;

    private double initX;
    private double initY;

    /**
     * Main construction of the cutting-edge GUI
     * This method might be considered patent-able technology
     * @param primaryStage stage on which to build UI
     */
    private void init(Stage primaryStage) {
        uiStack = new StackPane();
        uiStack.setAlignment(Pos.TOP_LEFT);
        uiStack.getChildren().addAll(
            primary,
            preview,
            createControls(primaryStage)
        );
        uiStack.setOnMousePressed(toolEvent(currentTool::onPress));
        uiStack.setOnMouseDragged(toolEvent(currentTool::onDrag));
        uiStack.setOnMouseReleased((MouseEvent me) -> {
                currentTool.onRelease(me, primary.getGraphicsContext2D());
            });
        setCurrColor(Color.CHARTREUSE);

        StackPane winStack = new StackPane();
        resizeBorder(winStack);
        winStack.setMargin(uiStack, new Insets(32, 26, 29, 28));
        winStack.getChildren().addAll(createWinDecor(), uiStack);

        windowDecoration.setOnMousePressed((MouseEvent me) -> {
                initX = me.getScreenX() - primaryStage.getX();
                initY = me.getScreenY() - primaryStage.getY();
            });
        windowDecoration.setOnMouseDragged((MouseEvent me) -> {
                primaryStage.setX(me.getScreenX() - initX);
                primaryStage.setY(me.getScreenY() - initY);
            });

        Scene scene = new Scene(winStack, WIN_WIDTH, WIN_HEIGHT,
            Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setMinWidth(WIN_WIDTH);
        primaryStage.setMinHeight(WIN_HEIGHT);
        primaryStage.setScene(scene);
    }

    /**
     * Creates the window decorations, or 'The Blue Box of Greatness'
     * as some people in the MS-Paint community refer to it.
     * @return A new image holding the decoration.
     */
    private ImageView createWinDecor() {
        windowDecoration = new ImageView("border.png");
        windowDecoration.setFitWidth(WIN_WIDTH);
        windowDecoration.setFitHeight(WIN_HEIGHT);
        return windowDecoration;
    }

    /**
     * Creates the main controls of the App. This app uses a new
     * incredible paradigm which handles clicks by the color
     * of what was clicked!
     * @param primaryStage stage to create controls on
     */
    private Pane createControls(Stage primaryStage) {
        AppFunction closeWindow = color -> {
            primaryStage.close();
        };
        AppFunction minimizeWindow = color -> {
            primaryStage.setIconified(true);
        };
        AppFunction fullscreenWindow = color -> {
            if (primaryStage.isFullScreen()) {
                primaryStage.setFullScreen(false);
            } else {
                primaryStage.setFullScreen(true);
            }
        };
        ByColorButton windowCtrl = new ByColorButton("winctl.png");
        windowCtrl.setDefaultAction(System.out::println);
        windowCtrl.getMappings().put(Color.rgb(236, 229, 35, 1.0),
            minimizeWindow);
        windowCtrl.getMappings().put(Color.rgb(55,  56,  50, 1.0),
            minimizeWindow);
        windowCtrl.getMappings().put(Color.rgb(0,   96, 255, 1.0),
            fullscreenWindow);
        windowCtrl.getMappings().put(Color.rgb(71,  76,  82, 1.0),
            fullscreenWindow);
        windowCtrl.getMappings().put(Color.rgb(254,  0,   0, 1.0),
            closeWindow);
        windowCtrl.getMappings().put(Color.rgb(58,  49,  50, 1.0),
            closeWindow);

        ImageView logo = new ImageView("logo.png");
        BorderPane headerCont = new BorderPane();
        headerCont.setLeft(logo);
        headerCont.setRight(windowCtrl);

        colorPeek = new Canvas(93, 93);
        ByColorButton pallete = new ByColorButton("pallete.png");
        pallete.setDefaultAction(color -> {
                setCurrColor(color);
            });
        pallete.setOnMouseDragged(pallete::onClick);

        ByColorButton toolbox = new ByColorButton("toolbox.png");
        toolbox.getMappings().put(Color.rgb(0, 0, 0),
            setTool(ToolBox.Tools.RECTANGLE));
        toolbox.getMappings().put(Color.rgb(0, 0, 1),
            setTool(ToolBox.Tools.EMPTY_RECTANGLE));
        toolbox.getMappings().put(Color.rgb(0, 1, 0),
            setTool(ToolBox.Tools.EMPTY_CIRCLE));
        toolbox.getMappings().put(Color.rgb(0, 1, 1),
            setTool(ToolBox.Tools.DOT));
        toolbox.getMappings().put(Color.rgb(1, 0, 0),
            setTool(ToolBox.Tools.LINE));
        toolbox.getMappings().put(Color.rgb(1, 0, 1),
            setTool(ToolBox.Tools.PAINT_BUCKET));
        toolbox.getMappings().put(Color.rgb(1, 1, 1),
            setTool(ToolBox.Tools.SPRAY_PAINT));
        toolbox.getMappings().put(Color.rgb(0, 0, 2),
            setTool(ToolBox.Tools.FREEFORM));

        ImageView clearCanvas = new ImageView("newCanvas.png");
        clearCanvas.setOnMouseClicked((MouseEvent me) -> {
                resetCanvas(primary);
            });

        HBox palleteCont = new HBox();
        palleteCont.getChildren().addAll(pallete, colorPeek);
        HBox toolCont    = new HBox();
        toolCont.getChildren().addAll(toolbox, clearCanvas);
        BorderPane footerCont = new BorderPane();
        footerCont.setRight(palleteCont);
        footerCont.setLeft(toolCont);

        BorderPane controlCenter = new BorderPane();
        controlCenter.setTop(headerCont);
        controlCenter.setBottom(footerCont);
        return controlCenter;
    }

    /**
     * Wrapper function to create a valid mouse event callback
     * from a Tool's set of events.
     * @param e A Reference to a Tool's event
     * @return the converted event in EventHandler-MouseEvent form.
     */
    private EventHandler<MouseEvent> toolEvent(ToolEvent e) {
        return (MouseEvent me) -> {
            if (currentTool.doPreview()) {
                resetCanvas(preview);
                e.fire(me, preview.getGraphicsContext2D());
            } else {
                e.fire(me, primary.getGraphicsContext2D());
            }
        };
    }

    /**
     * Convenience method to create a method to change to a tool
     * based on the Tool's enum.
     * @param tool Tool to change to
     * @return a function that when called changes the current tool to tool
     */
    private AppFunction setTool(ToolBox.Tools tool) {
        return color -> {
            currentTool.setCurrTool(tool);
        };
    }

    /**
     * Clears the canvas from any color
     * @param canvas canvas to clear
     */
    private void resetCanvas(Canvas canvas) {
        canvas.getGraphicsContext2D()
            .clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Adds listeners to the container to resize the window decorations
     * when the app is set to full screen.
     * @param container container to listen to
     */
    private void resizeBorder(Pane container) {
        container.widthProperty().addListener((observable, old, now) -> {
                windowDecoration.setFitWidth(now.doubleValue());
            });
        container.heightProperty().addListener((observable, old, now) -> {
                windowDecoration.setFitHeight(now.doubleValue());
            });
    }

    /**
     * Sets the current color to all the canvases fill color.
     * And sets the preview color.
     * @param color Color to the set the current one to.
     */
    private void setCurrColor(Color color) {
        currentColor = color;
        GraphicsContext prevContx = preview.getGraphicsContext2D();
        prevContx.setFill(color);
        prevContx.setStroke(color);

        GraphicsContext primContx = primary.getGraphicsContext2D();
        primContx.setFill(color);
        primContx.setStroke(color);

        GraphicsContext peekGC = colorPeek.getGraphicsContext2D();
        peekGC.setFill(color);
        peekGC.fillRect(0, 0, colorPeek.getWidth(), colorPeek.getHeight());
    }

    /**
     * Main entry point after the application is constructed
     * @param primaryStage Stage to start drawing the UI on.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * Main method!
     * Main entry point for the app
     * @param args Arguments for the app, there are none.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
