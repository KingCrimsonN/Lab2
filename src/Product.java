public class Product {
    private String name, image, description;
    private int amount, price;
    private Department department;

    public boolean isInCart() {
        return isInCart;
    }

    private boolean isInCart;

    /**
     * A  fully customized product constructor
     * @param nName new name
     * @param nPrice new price
     * @param val new amount
     * @param dep new department
     * @param nImage new image
     * @param desc new description
     */
    Product(String nName, int nPrice, int val, Department dep, String nImage, String desc) {
        edit(nName, nPrice, val, dep, nImage, desc);
    }

    public boolean checkCart(){
        return isInCart;
    }

    public void addToCart(){
        isInCart=true;
    }

    public void removeFromCart(){
        isInCart=false;
    }

    public String getDescription() {
        return description;
    }

    public Department getDepartment() {
        return department;
    }

    /**
     * A fully customized change of product field values
     * @param nName new name
     * @param nPrice new price
     * @param val new amount
     * @param dep new department
     * @param nImage new image
     * @param desc new description
     */
    public void edit(String nName, int nPrice, int val, Department dep, String nImage, String desc) {
        name = nName;
        price = nPrice;
        amount = val;
        department = dep;
        image = nImage;
        description = desc;
    }

    /**
     * Compares a string with product's name (useful for checking if a new name is unique)
     * @param n - name to check with product's one
     * @return the result of comparison (boolean)
     */
    public boolean equals(String n) {
        if (name.equals(n))
            return true;
        return false;
    }

    public String toString() {
        return name + " " + price + " " + amount + " " + image + " " + description + "\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
