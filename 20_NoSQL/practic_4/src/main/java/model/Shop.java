package model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class Shop {
    @BsonProperty(value = "_id")
    private ObjectId id;
    private String name;
    private List<String> products;

    public Shop(String name, List<String> products) {
        this.name = name;
        this.products = products;
    }

    public Shop(ObjectId id, String name, List<String> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Shop() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void addProduct(String product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
