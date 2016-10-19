public class MainO {
    public static void main(String[] args){
        ObservableItem p1  = new ObservableItem("Tommy Jones", 19);// Just two data members
        ObservableItem p2  = new ObservableItem("Revolver Ocelot",88);// These objects are 'observable'
        
        MyObserver  obs = new MyObserver(); 
        p1.addObserver(obs);  // adds observer, keeps lookout for changes
        p1.changeAge(6);   // here's a change
        
       
        
        
        
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
