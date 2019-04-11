import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtilit {

    public static void sortByName(ArrayList list){
        Comparator comparator;
        if (list.get(0) instanceof Product) comparator = Comparator.comparing(Product::getName);
        else comparator = Comparator.comparing(Department::getName);
        Collections.sort(list,comparator);
    }
    public static void sortByValue(ArrayList<Product> products, boolean byAmount){
        Comparator comparator;
        if (byAmount)comparator = Comparator.comparing(Product::getAmount);
        else comparator = Comparator.comparing(Product::getPrice);
        Collections.sort(products,comparator);
    }
    public static void sortByAmount(ArrayList<Product> products){
        sortByValue(products,true);
    }
    public static void sortByPrice(ArrayList<Product> products){
        sortByValue(products,false);
    }
}

