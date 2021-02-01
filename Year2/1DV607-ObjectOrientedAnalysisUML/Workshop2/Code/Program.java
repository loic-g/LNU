import Controller_Package.Controller;
import View_Package.View;


public class Program {
    public static void main (String[] args) {
        Controller br = new Controller();
        View view = new View();
        //br.createDatabase();
        boolean bool = true;
        while (bool){
           view.menu(br);

        }
    }
}
