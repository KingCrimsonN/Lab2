import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchUtilit {
    /**
     * This method searches for products or departments with names that contain or equal a certain string
     * @param list - the list to search in
     * @param namePart - a name or its part for matches with which the method is searching
     * @return the result of search as an ArrayList
     */
    @SuppressWarnings("all")
    public static ArrayList findByName(ArrayList list, String namePart){
        if (list==null)return null;
        if (list.size()==0)return null;
        List foundList = (List)list.stream().filter(element ->
                ((element instanceof Product)&&((Product) element).getName().contains(namePart))||
                        (element instanceof Department&&((Department)element).getName().contains(namePart)))
                .collect(Collectors.toList());
        ArrayList found = new ArrayList(foundList);
        if (found.size()==0)return null;
        SortUtilit.sortByName(found);
        return found;
    }

    /**
     * Uses findProdByValue for filtering by amount
     * @param allProducts - list to filter
     * @param lowest - the lower limit for amount
     * @param highest - the higher limit for amount
     * @return the result of search as an ArrayList
     */
    public static ArrayList<Product> findByAmount(ArrayList<Product> allProducts, int lowest, int highest){
        return findProdByValue(allProducts,lowest,highest,true);
    }

    /**
     * Uses findProdByValue for searching by amount
     * @param allProducts - list to search in
     * @param val - value of amount searched for
     * @return the result of search as an ArrayList
     */
    public static ArrayList<Product> findByAmount(ArrayList<Product> allProducts, int val){
        return findProdByValue(allProducts,val,true);
    }

    /**
     * Uses findProdByValue for filtering by price
     * @param allProducts - list to filter
     * @param lowest - the lower limit for price
     * @param highest - the higher limit for price
     * @return the result of search as an ArrayList
     */
    public static ArrayList<Product> findByPrice(ArrayList<Product> allProducts, int lowest, int highest){
        return findProdByValue(allProducts,lowest,highest,false);
    }

    /**
     * Uses findProdByValue for searching by price
     * @param allProducts - list to search in
     * @param price - value of price searched for
     * @return the result of search as an ArrayList
     */
    public static ArrayList<Product> findByPrice(ArrayList<Product> allProducts, int price){
        return findProdByValue(allProducts,price,false);
    }

    /**
     * this method allows to filter products in an ArrayList and returns only those that match the lower and higher limit
     * @param allProducts - list to filter
     * @param lowest - the lower limit for some numeric value
     * @param highest - the higher limit for some numeric value
     * @param byAmount - defines if products are sorted by price or amount
     * @return the ArrayList of products that match the limits
     */
    private static ArrayList<Product> findProdByValue(ArrayList<Product> allProducts, int lowest, int highest, boolean byAmount){
        if (allProducts==null)return null;
        ArrayList<Product> foundProducts = new ArrayList<>(allProducts.stream().filter(
                product -> (byAmount&&product.getAmount()>=lowest&&product.getAmount()<=highest)||
                        (!byAmount&&product.getPrice()>=lowest&&product.getPrice()<=highest)
        ).collect(Collectors.toList()));
        if(foundProducts.size()==0)return null;
        SortUtilit.sortByValue(foundProducts,byAmount);
        return foundProducts;
    }

    /**
     * This method finds  all the products in an ArrayList that have a certain value of amount or price
     * @param allProducts - the list to search in
     * @param value - the value that must equal the one that product has
     * @param byAmount - defines if the value used for search is amount or price
     * @return the result of search as an ArrayList
     */
    private static ArrayList<Product> findProdByValue(ArrayList<Product> allProducts, int value, boolean byAmount){
        if (allProducts==null)return null;
        ArrayList<Product> foundProducts = new ArrayList<>(allProducts.stream().filter(
                product -> (byAmount&&product.getAmount()==value)||
                        (!byAmount&&product.getPrice()==value)
        ).collect(Collectors.toList()));
        if(foundProducts.size()==0)return null;
        SortUtilit.sortByName(foundProducts);
        return foundProducts;
    }

}
