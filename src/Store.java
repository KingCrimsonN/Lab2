import java.util.ArrayList;

public class Store {
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Product> cart = new ArrayList<>();

    public void add(String name) {
        departments.add(new Department(name));
    }
    
     public void addToCart(Product prod){
        cart.add(prod);
        prod.addToCart();
    }

    public void removeFromCart(Product prod){
        cart.remove(prod);
        prod.removeFromCart();
    }
    
     public void purchase(){
        ArrayList<Product> prods = getAllProducts();
        for (Product cartProduct:cart){
            for (Product product:prods){
                if (cartProduct.equals(product.getName())){
                    product.setAmount(product.getAmount() - cartProduct.getAmount());
                    cartProduct.setAmount(0);
                    cart.remove(cartProduct);
                }
            }
        }
    }
    
     public int cartPrice(){
        int res=0;
        for (int i=0; i<cart.size(); i++){
            res+=cart.get(i).getPrice()*cart.get(i).getAmount();
        }
        return res;
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    public void addProduct(String depName, String prodName, int price, int am ,String img, String desc){
        Department dep;
        if (checkUnique(depName))
            add(depName);
        dep = getDepartment(depName);
        dep.add(prodName, price, am,img,desc);
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

   public ArrayList<Product> searchByName(String sName){
        return SearchUtilit.findByName(getAllProducts(),sName);
    }

    public ArrayList<Product> searchByName(Department dep, String sName){
        return SearchUtilit.findByName(dep.products,sName);
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
