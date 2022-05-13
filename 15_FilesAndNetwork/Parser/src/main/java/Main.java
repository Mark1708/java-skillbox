import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String url = "https://lenta.ru/";

        try {
            Document doc  = Jsoup.connect(url).get();
            Elements imges = doc.select("img.g-picture");

            imges.stream().filter(
                    element ->
                            element.attr("abs:src")
                                    .substring(element.attr("abs:src").length() - 3).equals("jpg")
            ).forEach(element -> {
                String imgUrl = element.attr("abs:src");
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new URL(imgUrl));
                    File target = new File("images/" + imgUrl.substring(imgUrl.lastIndexOf("/") + 1));
                    ImageIO.write(image, "jpg", target);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(imgUrl.substring(imgUrl.lastIndexOf("/") + 1));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
