public class Product {
    String name, image;
    int amount, price;

    Product(String nName, int val, int nPrice, String nImage) {
        edit(nName, val, nPrice, nImage);
    }

    public void edit(String nName, int val, int nPrice, String img) {
        name = nName;
        amount = val;
        price = nPrice;
        image = img;
    }

    public boolean equals(String n) {
        if (name.equals(n))
            return true;
        return false;
    }

    public String toString() {
        return name + " " + price + " " + amount + " " + image + "\n";
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
