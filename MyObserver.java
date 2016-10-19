
import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer{
    
    @Override
    public void update(Observable o, Object arg) { // Whenever there is a change
       System.out.println("This shit is updated!");
    }
    
}
