import java.io.IOException;
import java.util.ArrayList;

public class Department {
    String name;
    ArrayList<Product> products = new ArrayList<>();

    /**
     * A department constructor with customized name
     * @param nName
     */
    Department(String nName) {
        name = nName;
    }

    /**
     * Adds a product to department if it is unique
     * @param prod - product to be added
     */
    public void add(Product prod){
        if (checkUnique(prod.getName()))
        products.add(prod);
    }

    /**
     * Adds a new product to department if it's name is unique
     * @param nName - new product name
     * @param price - new product price
     * @param am - new product amount
     * @param image - new product image
     * @param desc - new product description
     */
    public void add(String nName, int price, int am, String image, String desc) {
        if (checkUnique(nName))
            products.add(new Product(nName, price, am, this,image,desc));
    }

    public void edit(Product prod, String name, int val, int price, String img, String desc) {
        if (checkUnique(name))
            for (int i = 0; i < products.size(); i++) {
                if (prod.getName().equals(products.get(i)))
                    products.get(i).edit(name, price, val, this , img ,desc);
            }
    }

    public void remove(Product prod) {
        products.remove(prod);
    }

    /**
     * creates a string with information about a department and all it's products used for saving store configuration
     * @return a string with info for export to a config file
     */
    public String toSaveString() {
        String s = "/g " + name + "\n";
        for (int i = 0; i < products.size(); i++) {
            s += products.get(i).toString();
        }
        return s;
    }
    public String toString(){
        return name;
    }

    /**
     *
     * @return the summary price of all products in a department
     */
    public int groupPrice() {
        int[] groupPrice = {0};
        products.forEach(product -> groupPrice[0] += product.getAmount() * product.getPrice());
        return groupPrice[0];
    }

    /**
     * Compares a string with department's name (useful for checking if a new name is unique)
     * @param n - name to check with department's one
     * @return the result of comparison (boolean)
     */
   public boolean equals(String n) {
        if (n==null)
            return false;
        return n.equals(name);
    }

    /**
     * checks if a name is already assigned for one of the products in a department
     * @param n - name to be checked
     * @return the result of check (boolean)
     */
    public boolean checkUnique(String n) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(n))
                return false;
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
