
import java.util.Observable;

/**
 *
 * 
 */
public class ObservableItem extends Observable{
    private String name;
    private int age;
    
    ObservableItem(){
        this.name = "James von Motherfucker";
        this.age = 69;
    }
    ObservableItem(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void changeAge(int age){  // Here's the main focus
        if(age!=this.age){
            setChanged();   // inbuilt method from superclass
            notifyObservers(age);  //alerts observable
        }
    }
    @Override
    public String toString(){
        return "this is " + name + " age: " +age;
    }
}
