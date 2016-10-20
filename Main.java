import java.util.ArrayList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Model;
import model.Grid;
import model.Zone;
import liquidsnake.view.Controller;
import liquidsnake.view.View;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Model model = new Model();
        model.init();
        View view = new View(primaryStage, model);
        Controller controller = new Controller(model, view); 
        view.addEventHandlers(controller);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
