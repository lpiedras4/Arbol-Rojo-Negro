import javafx.application.Application;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.IOException;
class Main extends Application {
    public Main(){
        super();
    }
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("VentanaP.fxml"));
        Scene scene = new Scene (fxmlLoader.load());
        stage.setTitle("Arbol Rojo-Negro");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }



}
