
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.Model;
import model.Zone;

public final class View extends BorderPane implements Observer{
    
    private Stage stage;
    private final Model model;
    private final VBox vbox = new VBox();
    private final ToolBar toolbar = new ToolBar();
    private final VBox subVBox = new VBox();
    private Tile[][] tile;
    private GridPane gridPane;
    private HBox Hbox = new HBox();
    
   
    private MenuItem optionBeginner; 
    private MenuItem optionIntermediate; 
    private MenuItem optionAdvanced; 
    
    private Button newButton = new Button("Test Button");
    
    public View(Stage someStage, Model model_) {
        model = model_;
        stage = someStage;
        this.setStyle("-fx-background-color: #D8BFD8;");   
        Scene scene = new Scene(this);
        initGridInterface();
        this.initMenu(stage); 
        this.initToolbar();
       
        
        stage.setTitle("Minesweepers");
        stage.setScene(scene);
        stage.show();    
    }
    public void initToolbar(){
         Hbox.setPadding(new Insets(10, 20, 20, 50));
         Hbox.setSpacing(10);
         Hbox.getChildren().addAll(new Text("Hello"),new Text("Hello Again"));
        //vbox.setSpacing(10);
         toolbar.getItems().add(Hbox);
       // 
         vbox.getChildren().add(toolbar);
        
    }
    
    public void addEventHandlers(Controller controller) {
   
        optionBeginner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleBeginnerOption();
            }
        });
        
        optionIntermediate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleIntermediateOption();
            }
        });
        
        optionAdvanced.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleAdvancedOption();
            }
        });
        
    }
    
    public void initMenu(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 450, 350);            // this has to be configured later?
        scene.setFill(Color.OLDLACE);
        MenuBar menuBar = new MenuBar();
        
        
        Menu menuFile = new Menu("File");
        Menu menuOptions = new Menu("Options");
        
        Menu menuDificulty = new Menu("Dificulty Settings");
   
        // Dificulty Settings
        optionBeginner = new MenuItem("Beginner");
        optionIntermediate = new MenuItem("Intermediate");
        optionAdvanced = new MenuItem("Advanced");
        menuDificulty.getItems().addAll(optionBeginner, optionIntermediate, optionAdvanced);
        menuOptions.getItems().add(menuDificulty);        

        
        menuBar.getMenus().addAll(menuFile, menuOptions);
        vbox.getChildren().add(menuBar);
        
        //getChildren().add(vbox);
        this.setTop(vbox);
        //vbox.getChildren().add(subHBox);
        
       // this.setCenter(toolbar);
    }

    public void initGridInterface(){
        System.out.println("InitGridInterface");
        int xMax = model.getGrid().getNumColumns();
        int yMax = model.getGrid().getNumRows();
        if (gridPane == null) 
            gridPane = new GridPane();
        else {
            this.getChildren().remove(gridPane);
            gridPane = new GridPane();
        }
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        // Configure this to match the dimensions of the grid
        gridPane.setMinSize(1500, 600);
        
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        tile = new Tile[xMax][yMax];
        for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                tile[x][y] = new Tile(30, 30);
                this.setDefaultTile(x, y);
                gridPane.add(tile[x][y], x, y);
            }
        }
        this.setBottom(gridPane);
    }
    public void updateTime(){
        
    }
    public void updateFace(){
        
    }
    public void updateScore(){
        
    }
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    private class Tile extends StackPane{
        private Rectangle tile;
        private double height;
        private double width;
        private boolean bringText;
        private Text text;
        
        public Tile(String string){
          tile = new Rectangle(30,30);
          tile.setStroke(Color.ORANGE);
          this.getChildren().add(tile);
        }
        
        public Tile(double x, double y){
            tile = new Rectangle(x,y);
            tile.setFill(Color.ORANGE);
            this.getChildren().add(tile);
        }
        
        public void setHeight(double height){
            this.height = height;
        }
        
        public double getheight(){
            return height;
        }
        public void setWidth(double width){
            this.width = width;
        }
        
        public double width(){
            return width;
        }
        
        public void validateText(){
            this.bringText = true;
        }
        
        public void setColor(Color color){
            tile.setFill(color);
        }
    }*/
    
    
    private void setDefaultTile(int x, int y) {
        tile[x][y].setColor(Color.AZURE);
        tile[x][y].setEffect(new InnerShadow());
    }
    
    public Tile getTile(int x, int y) {
        return tile[x][y];
    }
    
    public void detonateTile(int x, int y) {
        tile[x][y].setColor(Color.RED);
    }
    
    public void flagTile(int x, int y) {
        tile[x][y].setColor(Color.BLUE);
    }
    
    public void unflagTile(int x, int y) {
        this.setDefaultTile(x, y);
    }
    
    public void revealTile(int x, int y) {
         int numberOfBombs = model.getNearbyBombs(x, y);
         if (numberOfBombs != 0) {
          
          tile[x][y].viewText(""+numberOfBombs);
         }
          tile[x][y].setColor(Color.BROWN);
         
         
         
    }
    
    
    public void updateTile(int x, int y) {
        Zone zone = model.getGrid().getZone(x, y);
        if (!model.isFlagged(x,y)) {
            //
            zone.show();
            //
            if  (zone.hasBomb()) {
                gridPane.add(new Label("*"), x, y);
            }
            else {
                int nearbyBombs = zone.getNearbyBombs();
                 if (nearbyBombs == 0)  {
                    ArrayList<Zone> zones = zone.getAdjacentZones();
                    for (Zone z : zones) {
                        if (!z.isShown()) {
                            updateTile(z.getX(), z.getY());
                        }
                    }
                }
                else {
                    gridPane.add(new Label(" " + nearbyBombs + " "), x, y);
                 }
                
            }  
        }
        else {
            System.out.println("Flag!");
        }
      //  tile[x][y].setFill(Color.BLUE);
        gridPane.getChildren().remove(tile[x][y]);
    }
    
}
