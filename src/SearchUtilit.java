import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchUtilit {
    
    @SuppressWarnings("all")
    public static ArrayList findByName(ArrayList list, String namePart){
        if (list==null)return null;
        if (list.size()==0)return null;
        List foundList = (List)list.stream().filter(element ->
                ((element instanceof Product)&&((Product) element).getName().contains(namePart))||
                        (element instanceof Department&&((Department)element).getName().contains(namePart))).collect(Collectors.toList());
        ArrayList found = new ArrayList(foundList);
        if (found.size()==0)return null;
        // I'm not sure if automatic sort of search result by the same parameter they were found by is needed
        // maybe I'll have to DELET THIS
        SortUtilit.sortByName(found);
        return found;
    }

    public static ArrayList<Product> findByVal(ArrayList<Product> allProducts, int lowest, int highest){
        return findProdByMoney(allProducts,lowest,highest,true);
    }

    public static ArrayList<Product> findByVal(ArrayList<Product> allProducts, int val){
        return findProdByMoney(allProducts,val,true);
    }

    public static ArrayList<Product> findByPrice(ArrayList<Product> allProducts, int lowest, int highest){
        return findProdByMoney(allProducts,lowest,highest,false);
    }

    public static ArrayList<Product> findByPrice(ArrayList<Product> allProducts, int price){
        return findProdByMoney(allProducts,price,false);
    }

    private static ArrayList<Product> findProdByMoney(ArrayList<Product> allProducts, int lowest, int highest, boolean byValue){
        if (allProducts==null)return null;
        ArrayList<Product> foundProducts = new ArrayList<>(allProducts.stream().filter(
                product -> (byValue&&product.getValue()>=lowest&&product.getValue()<=highest)||
                        (!byValue&&product.getPrice()>=lowest&&product.getPrice()<=highest)
        ).collect(Collectors.toList()));
        if(foundProducts.size()==0)return null;
        // I'm not sure if automatic sort of search result by the same parameter they were found by is needed
        // maybe I'll have to DELET THIS
        SortUtilit.sortByMoney(foundProducts,byValue);
        return foundProducts;
    }

    private static ArrayList<Product> findProdByMoney(ArrayList<Product> allProducts, int amountOfMoney, boolean byValue){
        if (allProducts==null)return null;
        ArrayList<Product> foundProducts = new ArrayList<>(allProducts.stream().filter(
                product -> (byValue&&product.getValue()==amountOfMoney)||
                        (!byValue&&product.getPrice()==amountOfMoney)
        ).collect(Collectors.toList()));
        if(foundProducts.size()==0)return null;
        // I'm not sure if automatic sort of search result by the same parameter they were found by is needed
        // maybe I'll have to DELET THIS
        SortUtilit.sortByName(foundProducts);
        return foundProducts;
    }
    
}
