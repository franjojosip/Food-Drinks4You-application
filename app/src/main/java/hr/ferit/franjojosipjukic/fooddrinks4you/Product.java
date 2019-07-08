package hr.ferit.franjojosipjukic.fooddrinks4you;

public class  Product{
    private String name;
    private double price;
    private String image;

    public Product(){}
    public Product(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() { return image; }
}
