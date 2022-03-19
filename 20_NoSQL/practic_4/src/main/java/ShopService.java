import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import model.Product;
import model.Shop;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;

public class ShopService {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Product> products;
    private MongoCollection<Document> productsCollection;
    private MongoCollection<Shop> shops;

    public ShopService() {
        this.client = MongoDbConfig.init();
        this.db = client.getDatabase("shop_db");
        this.products = db.getCollection("products", Product.class);
        this.shops = db.getCollection("shops", Shop.class);
        this.productsCollection = db.getCollection("shops");
    }


    public void addShop(String name) {
        Shop shop = new Shop(name, new ArrayList<>());
        shops.insertOne(shop);
        System.out.printf("\tShop %s added to db\n", shop.getName());
    }

    public void addProduct(String nameProduct, String nameShop) {
        Shop shop = shops.find(Filters.eq("name", nameShop)).first();
        Product productFromWarehouse = products.find(Filters.eq("name", nameProduct)).first();
        if (shop != null && productFromWarehouse != null) {
            shop.addProduct(productFromWarehouse.getName());
            shops.findOneAndReplace(Filters.eq("name", nameShop), shop);
            System.out.printf("\tProduct %s added to shop - %s\n", nameProduct, nameShop);
        } else {
            System.err.println("\tShop or product not exists\n");
        }
    }

    public void createProduct(String name, int price) {
        Product product = new Product(name, price);
        products.insertOne(product);
        System.out.printf("\tProduct %s added to db\n", product.getName());
    }

    public void productStatistic() {
        Bson lookup = lookup("products", "products", "name", "product_list");
        Bson group = group(
                "$name",
                avg("avgprice", "$product_list.price"),
                min("minprice", "$product_list.price"),
                max("maxprice", "$product_list.price"),
                new BsonField("count", new Document("$sum", 1)),
                new BsonField("less100", Document.parse("{$sum:{$cond:{ if: {$lt:[\"$product_list.price\", 100]}, then: 1, else: 0}}}"))
        );
        Bson unwind = unwind("$product_list"); // с этим параметром почему-то крашется
        List<Document> results = productsCollection.aggregate(Arrays.asList(lookup, unwind, group))
                .into(new ArrayList<>());
        StringBuffer buffer = new StringBuffer();
        buffer.append("Product statistic:\n");
        results.forEach(document -> {
            buffer
                    .append(String.format("\tShop name - %s\n", document.get("_id")))
                    .append(String.format("\t\tAverage product price is %f\n", document.get("avgprice")))
                    .append(String.format("\t\tMinimum price of the product is %d\n", document.get("minprice")))
                    .append(String.format("\t\tMaximum price of the product is %d\n", document.get("maxprice")))
                    .append(String.format("\t\tTotal number of products - %d\n", document.get("count")))
                    .append(String.format("\t\tNumber of products cheaper than 100 rubles - %d\n", document.get("less100")));
        });
        System.out.println(buffer);
    }
/*
* productStatistic
db.shops.aggregate([
    {
        $lookup:{
            from: "products",
                    localField: "products",
                    foreignField: "name",
                    as: "product_list"
        }
    }, {
        $unwind: {
            path: "$product_list"
        }
    }, {
        $group: {
            _id: {name: "$name"},
            avgprice: {$avg: "$product_list.price"},
            minprice: {$min: "$product_list.price"},
            maxprice: {$max: "$product_list.price"},
            count: { $count: {} },
            lessThen100count: {
                $sum:{$cond:{ if: {$lt:["$product_list.price", 100]}, then: 1, else: 0}}
            }
        }
    }
])
*/
}
