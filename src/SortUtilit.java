import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortUtilit {
    /**
     * This method gets an ArrayList as a parameter and sorts it in alphabetic order of names of objects 
     * if they are products or departments
     * The sort is case insensitive
     * @param list - the list of departments ot products to be sorted
     */
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

    /**
     * This method allows to sort an ArrayList of products by a numeric value like their amount or price
     * @param products - the list to be sorted
     * @param byAmount - defines if products are sorted by amount or price
     */
    public static void sortByValue(ArrayList<Product> products, boolean byAmount){
        Comparator comparator;
        if (byAmount)comparator = Comparator.comparing(Product::getAmount);
        else comparator = Comparator.comparing(Product::getPrice);
        Collections.sort(products,comparator);
    }

    /**
     * this method sorts an ArrayList of products by their amount using sortByValue
     * @param products - the list of products to be sorted
     */
    public static void sortByAmount(ArrayList<Product> products){
        sortByValue(products,true);
    }
    /**
     * this method sorts an ArrayList of products by their price using sortByValue
     * @param products - the list of products to be sorted
     */
    public static void sortByPrice(ArrayList<Product> products){
        sortByValue(products,false);
    }
}

