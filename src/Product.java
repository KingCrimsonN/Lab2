public class Product {
    private String name, image, description;
    private int amount, price;
    private Department department;

    Product(String nName, int nPrice, int val, Department dep, String nImage, String desc) {
        edit(nName, nPrice, val, dep, nImage, desc);
    }

    public String getDescription() {
        return description;
    }

    public Department getDepartment() {
        return department;
    }

    public void edit(String nName, int nPrice, int val, Department dep, String nImage, String desc) {
        name = nName;
        price = nPrice;
        amount = val;
        department = dep;
        image = nImage;
        description = desc;
    }

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