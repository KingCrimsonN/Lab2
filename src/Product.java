public class Product {
    String name;
    int amount, price;
    Product(String nName, int val, int nPrice){
        edit(nName, val, nPrice);
    }

    public void edit(String nName, int val, int nPrice){
        name = nName;
        amount = val;
        price = nPrice;
    }
    public boolean equals(String n){
        if (name.equals(n))
            return true;
        return false;
    }
    public String toString(){
        return name + " " + price + " " + amount + "\n";
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

    public String getName() {
        return name;
    }

    public int getValue() {
        return amount;
    }

    public int getPrice() {
        return price;
    }
}
