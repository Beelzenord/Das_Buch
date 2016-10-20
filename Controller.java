
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Zone;

public final class Controller {
    
    private final View view;
    private final Model model;
    
    public Controller(Model model_, View view_) {
        view = view_;
        model = model_;
        this.addGridSelectionHandles();
    }  
    
    public void handleBeginnerOption() {
        model.setBeginnerDificulty();
        this.reset();
    }
    
    public void handleIntermediateOption() {
        model.setIntermediateDificulty();
        this.reset();
    }
    
    public void handleAdvancedOption() {
        model.setAdvancedDificulty();
        this.reset();
    }
    
    private void reset() {
        model.init();
        view.initGridInterface();
        this.addGridSelectionHandles();
    }
   
    
    public void addGridSelectionHandles() {
        int xMax = model.getGrid().getNumColumns();
        int yMax = model.getGrid().getNumRows();
         for (int y = 0; y < yMax; y++) {
            for (int x = 0; x < xMax; x++) {
                Tile t = view.getTile(x, y);
                t.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Integer x = GridPane.getColumnIndex(t);
                        Integer y = GridPane.getRowIndex(t);
                        // Flag
                        if (event.getButton() == MouseButton.SECONDARY) {
                            if (!model.isFlagged(x, y)) {
                                model.toggleFlag(x, y);
                                view.flagTile(x, y);   
                            }
                            else {
                                model.toggleFlag(x, y);
                                view.unflagTile(x, y);
                            }
                        }
                        
                        else if (event.getButton() == MouseButton.PRIMARY){
                            if (model.hasBomb(x, y)) {
                                for (Zone z: model.getExplosiveZones()) {
                                    view.detonateTile(z.getX(), z.getY());
                                }   
                            }
                            else {
                                System.out.println("Reveal!");
                                model.reveal(x, y);
                                view.revealTile(x, y);
                            }
                        }
                    }
                });
            }
        }
    }
}
