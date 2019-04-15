import java.io.IOException;
import java.util.ArrayList;

public class Department {
    String name;
    ArrayList<Product> products = new ArrayList<>();

    Department(String nName) {
        name = nName;
    }

    public void add(String nName, int am, int price, String image) {
        if (checkUnique(nName))
            products.add(new Product(nName, am, price,image));
    }

    public void edit(Product prod, String name, int val, int price, String img) {
        if (checkUnique(name))
            for (int i = 0; i < products.size(); i++) {
                if (prod.name.equals(products.get(i)))
                    products.get(i).edit(name, val, price,img);
            }
    }

    public void remove(Product prod) {
        products.remove(prod);
    }

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

    public int groupPrice() {
        int[] groupPrice = {0};
        products.forEach(product -> groupPrice[0] += product.getAmount() * product.getPrice());
        return groupPrice[0];
    }

    public boolean equals(String n) {
        return n.equals(name);
    }

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
