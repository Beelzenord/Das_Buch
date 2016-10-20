import java.util.ArrayList;

public class Model {

    private Dificulty BEGINNER;
    private Dificulty INTERMEDIATE;
    private Dificulty ADVANCED;
    private Dificulty dificulty;
    private Grid grid;

    private enum Dificulty {
        BEGINNER, INTERMEDIATE, ADVANCED; 
    }  

    public Model() {
        setBeginnerDificulty();
    }
    
    public void init() {
        
        int width = 0;
        int height = 0;
        int bombs = 0;
        
        // Configure Dificulty 
        switch (dificulty) {
            case BEGINNER:      width =  9; height = 9; bombs = 10; break;
            case INTERMEDIATE:  width =  16; height = 16; bombs = 40; break;
            case ADVANCED:      width =  30; height = 16; bombs = 99; break;
            default: // Exception
        }
        
        // Initialize Grid
        grid = new Grid(width, height, bombs);
    }    
    public Grid getGrid() {
        return grid;
    }    
    public ArrayList<Zone> getExplosiveZones() {
        return grid.getZones();
    }    
    public void setBeginnerDificulty() {
        dificulty = Dificulty.BEGINNER;
    }    
    public void setIntermediateDificulty() {
        dificulty = Dificulty.INTERMEDIATE;
    }    
    public void setAdvancedDificulty() {
        dificulty = Dificulty.ADVANCED;
    }   
    public void toggleFlag(int x, int y) {
        grid.getZone(x, y).toggleFlag();
    }
    public boolean isFlagged(int x, int y) {
        return grid.getZone(x, y).isFlagged();
    }
    public boolean hasBomb(int x, int y) {
        return grid.getZone(x, y).hasBomb();
    }
    public int getNearbyBombs(int x, int y) {
        return grid.getZone(x, y).getNearbyBombs();
    }
    public void reveal(int x, int y) {
        grid.getZone(x, y).show();
    }
}
