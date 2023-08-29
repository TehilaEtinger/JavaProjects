import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {
    //attributes
    private Color c ;
    private Shape shape;
    private boolean chosenFill;
    private double prevX, prevY;
    @FXML
    private ToggleGroup ShapeTouggleGrop;
    @FXML
    private RadioButton blankButton;
    @FXML
    private ColorPicker color;
    @FXML
    private Pane drawingPane;
    @FXML
    private RadioButton ellipseButton;
    @FXML
    private RadioButton lineButton;
    @FXML
    private RadioButton rectangleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Circle e = new Circle();
        ellipseButton.setUserData(e);
        Line l = new Line();
        lineButton.setUserData(l);
        Rectangle r = new Rectangle();
        rectangleButton.setUserData(r);

        //default initialize
        c = Color.BLACK;
        shape = new Rectangle();
        chosenFill = true;
    }
    @FXML
    void colorPressed() {//set the color
      c=new Color(color.getValue().getRed(), color.getValue().getGreen(),
                 color.getValue().getBlue(),color.getValue().getOpacity());
    }
     @FXML
     void clearPressed() {
        drawingPane.getChildren().clear();
    }
     @FXML
     void mousePressed(MouseEvent event) {//save the mouse's coordinate of start dragging
         prevX = event.getX();
         prevY = event.getY();
     }
    @FXML
    void mouseRelease(MouseEvent event) {//when the mouse release, draw on the pane/
        Shape newShape = new Circle();
        if(shape instanceof Circle) {//if the user chose ellipse(I decided to do circle' because it's a special ellipse)
            double radius = Math.sqrt(Math.pow(event.getX() - prevX, 2) + Math.pow(event.getY() - prevY, 2)) / 2;
            newShape = new Circle((event.getX() + prevX) / 2, (event.getY() + prevY) / 2, radius,c);
        }

        if(shape instanceof Rectangle) {//if the user chose rectangle
            newShape = new Rectangle(Math.min(prevX,event.getX()),Math.min(prevY,event.getY()), Math.abs(event.getX()-prevX),Math.abs(event.getY()-prevY));
            newShape.setFill(c);
        }

        if(shape instanceof Line) {//if the user chose line
            newShape = new Line(prevX,prevY,event.getX(),event.getY());
            newShape.setStroke(c);
            blankButton.disabledProperty();

        }

        if(!chosenFill){
            newShape.setFill(null);
            newShape.setStroke(c);
        }
        drawingPane.getChildren().add(newShape);//add shape to screen

    }
    @FXML
    void shape() {
        shape = (Shape) ShapeTouggleGrop.getSelectedToggle().getUserData();
    }
    @FXML
    void filling() {
        chosenFill = !chosenFill;
    }
     @FXML
    void undoPressed() {
        int count = drawingPane.getChildren().size();
        if(count>0) {
            drawingPane.getChildren().remove(count - 1);
        }
    }
}
