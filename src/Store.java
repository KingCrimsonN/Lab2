import java.util.ArrayList;

public class Store {
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Product> cart = new ArrayList<>();

    public void add(String name) {
        if (checkUnique(name))
            departments.add(new Department(name));
    }

    public void addToCart(Product p, int amm){
        Product pCart = p;
        if ((p.getAmount()-amm)>0) {
            pCart.setAmount(amm);
            p.setAmount(p.getAmount()-amm);
            cart.add(pCart);
        }
    }

    public void edit(Department dep, String name) {
        if (checkUnique(name))
            for (int i = 0; i < departments.size(); i++) {
                if (departments.get(i).equals(dep.name))
                    departments.get(i).name = name;
            }
    }

    public void remove(Department dep) {
        departments.remove(dep);
    }

    public Department getDepartment(int index) {
        return departments.get(index);
    }

    public Department getDepartment(String name) {
        for (Department department : departments) {
            if (department.equals(name)) return department;
        }
        return null;
    }

    public int totalPrice() {
        int[] totalPrice = {0};
        departments.forEach(department -> totalPrice[0] += department.groupPrice());
        return totalPrice[0];
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        departments.forEach(department -> products.addAll(department.getProducts()));
        if (products.size() == 0) return null;
        return products;
    }

    public boolean checkUnique(String n) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).equals(n))
                return false;
        }
        return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < departments.size(); i++) {
            s += departments.get(i).toSaveString();
        }
        return s;
    }
}
