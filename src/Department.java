import java.util.ArrayList;

public class Department {
    String name;
    ArrayList<Product> products = new ArrayList<>();

    Department(String nName) {
        name = nName;
    }

    public void add(String nName, int am, int price) {
        products.add(new Product(nName, am, price));
    }

    public void edit(Product prod, String name, int val, int price) {
        for (int i = 0; i < products.size(); i++) {
            if (prod.name.equals(products.get(i)))
                products.get(i).edit(name, val, price);
        }
    }

    public void remove(Product prod) {
        products.remove(prod);
    }

    public String toString() {
        String s = "/g " + name + "\n";
        for (int i = 0; i < products.size(); i++) {
            s += products.get(i).toString();
        }
        return s;
    }

    public boolean equals(String n) {
        if (n.equals(name))
            return true;
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
