public class Product {
    String name;
    int value, price;
    Product(String nName, int val, int nPrice){
        edit(nName, val, nPrice);
    }

    public void edit(String nName, int val, int nPrice){
        name = nName;
        value = val;
        price = nPrice;
    }
    public boolean equals(String n){
        if (name.equals(n))
            return true;
        return false;
    }
    public String toString(){
        return name + " " + price + " " + value + "\n";
    }
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getPrice() {
        return price;
    }
}
