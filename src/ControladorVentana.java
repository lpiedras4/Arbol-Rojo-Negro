import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import jdk.jshell.EvalException;

import java.awt.event.ActionEvent;

public class ControladorVentana {

    @FXML private Pane lienzoArbol;
    @FXML private TextField txtAgregar;
    @FXML private TextField txtEliminar;
    @FXML private TextField txtBuscar;
    @FXML private Button agregarBtn;
    @FXML private Button eliminarBtn;
    @FXML private Button buscarBtn;
    @FXML private Label lblTxtBuscar;

    //Se crea arbol/////////////////
    private RedBlackTree<Integer> arbol = new RedBlackTree<>();


    /// Metodo para dibujar árbol//////////////
    private void dibujarArbol() {
        lienzoArbol.getChildren().clear();

        var nodos = arbol.getNodeViews(70, 40);

        // Primero dibujamos líneas padre–hijo
        for (var nv : nodos) {
            if (nv.parentIndex != -1) {
                var padre = nodos.get(nv.parentIndex);

                Line line = new Line(
                        padre.x, padre.y,
                        nv.x, nv.y
                );

                line.setStroke(Color.BLACK);
                line.setStrokeWidth(2);
                lienzoArbol.getChildren().add(line);
            }
        }

        // Luego los nodos (círculos + texto)
        for (var nv : nodos) {

            Circle c = new Circle(nv.x, nv.y, 18);
            c.setStroke(Color.BLACK);
            c.setStrokeWidth(2);

            if (nv.red) c.setFill(Color.RED);
            else c.setFill(Color.BLACK); ////////////////////////////////////////////

            Text t = new Text(nv.x - 6, nv.y + 4, String.valueOf(nv.value));
            t.setFill(Color.WHITE);

            lienzoArbol.getChildren().addAll(c, t);
        }
    }

    // ======================================================
    // BOTÓN: AGREGAR
    // ======================================================
    @FXML
    private void onAgregar() {
        try {
            int valor = Integer.parseInt(txtAgregar.getText());
            arbol.insertar(valor);
            dibujarArbol();
            txtAgregar.clear();
        } catch (NumberFormatException e) {
            System.out.println("Valor no válido");
        }
    }

    // ======================================================
    // BOTÓN: ELIMINAR
    // ======================================================
    @FXML
    private void onEliminar() {
        try {
            int valor = Integer.parseInt(txtEliminar.getText());

            boolean eliminado = arbol.eliminarM(valor); // usa tu función lógica

            if (eliminado) {
                dibujarArbol();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Eliminación exitosa");
                alert.setHeaderText(null);
                alert.setContentText("El valor " + valor + " fue eliminado del árbol.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No encontrado");
                alert.setHeaderText(null);
                alert.setContentText("El valor " + valor + " NO existía en el árbol.");
                alert.showAndWait();
            }

            txtEliminar.clear();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese un número válido.");
            alert.showAndWait();
        }
    }


    // ======================================================
    // BOTÓN: BUSCAR
    // ======================================================
    @FXML
    private void onBuscar() {
        int valorBuscado = Integer.parseInt(txtBuscar.getText());

        boolean encontrado = arbol.buscar(valorBuscado);

        if (encontrado) { //buscar devuelve true que recibe el valor buscado
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Búsqueda");
            alert.setHeaderText(null);
            alert.setContentText("El valor " + valorBuscado + " se encuentra en el árbol.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Búsqueda");
            alert.setHeaderText(null);
            alert.setContentText("El valor " + valorBuscado + " no se encuentra en el árbol.");
            alert.showAndWait();
        }
    }
}
