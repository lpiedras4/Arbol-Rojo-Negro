import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;



import java.awt.*;

public class ControladorVentana {
    @FXML
    private Pane pane;
    @FXML
    private TextField txtAgregar;
    @FXML
    private TextField txtEliminar;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Shape cirucloNodo;

    private ArbolRN arbolRN;
    private Node root;
    @FXML
    private Text valorNodo;



    @FXML
    private void onAgregar(){
        //Texto que se recibe en TextField
        String valor = txtAgregar.getText();
        //Crear círculo para el nodo
        Circle nodo = new  Circle();
        nodo.setRadius(5);
        nodo.setRadius(50);
        nodo.setCenterX(150);
        nodo.setCenterY(150);
        nodo.setFill(Color.BLACK);
        nodo.setStroke(Color.WHITE);
        nodo.setStrokeWidth(2);

        //Texto para nodo
        Text texto = new Text(valor);
        texto.setX(150 - texto.getLayoutBounds().getWidth() / 2);
        texto.setY(150 + texto.getLayoutBounds().getHeight() / 4);
        texto.setFill(Color.BLACK);

        pane.getChildren().addAll(nodo, texto);
        txtAgregar.clear();

    }

    @FXML
    private void onEliminar(){

    }

    @FXML
    private void onBuscar(){

    }


}
