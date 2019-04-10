import java.util.ArrayList;

public class SearchUtilit {

    public static ArrayList findByName(ArrayList list, String namePart){
        ArrayList found = new ArrayList();
        list.forEach(element -> {
            if (element instanceof Product){
                if (((Product) element).getName().contains(namePart))
                    found.add(element);
            }
            else if (((Department)element).getName().contains(namePart))
                found.add(element);
        });
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
        ArrayList<Product> foundProducts = new ArrayList<>();
        allProducts.forEach(product -> {
            int valOrPrice;
            if (byValue) valOrPrice = product.getValue();
            else valOrPrice = product.getPrice();
            if(valOrPrice>=lowest&&valOrPrice<=highest)
                foundProducts.add(product);
        });
        if(foundProducts.size()==0)return null;
        // I'm not sure if automatic sort of search result by the same parameter they were found by is needed
        // maybe I'll have to DELET THIS
        SortUtilit.sortByMoney(foundProducts,byValue);
        return foundProducts;
    }

    private static ArrayList<Product> findProdByMoney(ArrayList<Product> allProducts, int amountOfMoney, boolean byValue){
        ArrayList<Product> foundProducts = new ArrayList<>();
        allProducts.forEach(product -> {
            int valOrPrice;
            if (byValue) valOrPrice = product.getValue();
            else valOrPrice = product.getPrice();
            if(valOrPrice==amountOfMoney)
                foundProducts.add(product);
        });
        if(foundProducts.size()==0)return null;
        // I'm not sure if automatic sort of search result by the same parameter they were found by is needed
        // maybe I'll have to DELET THIS
        SortUtilit.sortByMoney(foundProducts,byValue);
        return foundProducts;
    }

}
