import java.util.ArrayList;

public class Store {
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Product> cart = new ArrayList<>();

    public void add(String name) {
            departments.add(new Department(name));
    }

    public void addProduct(String depName, String prodName, int am, int price,String img){
        Department dep;
        if (checkUnique(depName))
            add(depName);
        dep = getDepartment(depName);
        dep.add(prodName, am, price,img);
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
    
    public Department searchByName(String sName){
        Department searchDepartment = new Department("Search Results");
        searchDepartment.products = SearchUtilit.findByName(getAllProducts(),sName);
        return searchDepartment;
    }
    
    public Department searchByName(Department dep, String sName){
        Department searchDepartment = new Department("Search Results");
        searchDepartment.products = SearchUtilit.findByName(dep.products,sName);
        return searchDepartment;
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
        SortUtilit.sortByName(products);
        return products;
    }

    public boolean checkUniqueProduct(String n){
        for (Department department : departments)
            if (!department.checkUnique(n)) return false;
        return true;
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
