import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtilit {

    public static void sortByName(ArrayList list){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (list.get(0) instanceof Product){
                    return String.CASE_INSENSITIVE_ORDER.compare(((Product)o1).getName(),((Product)o2).getName());
                }
                return String.CASE_INSENSITIVE_ORDER.compare(((Department)o1).getName(),((Department)o2).getName());
            }
        };
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

