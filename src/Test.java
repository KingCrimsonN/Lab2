import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Store store = FileInput.readConfig("config.ttt");
//        store.add(new Department("seeds"));
//        store.getDepartment(0).add(new Product("Potat", 10,10));
//        store.add(new Department("not seeds"));
//        store.getDepartment(1).add(new Product("Bike", 200,1));
//        store.getDepartment(1).add(new Product("Joint", 4,4));
        System.out.println(store);
        FileInput.saveFile(store.toString());
    }
}
