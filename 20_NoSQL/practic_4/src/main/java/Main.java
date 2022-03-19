import command.Command;
import command.CommandBuilder;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //  Create admin user and change MongoDbConfig
        //  db.createUser({user: "adminuser", pwd: "admin", roles: [ "root" ]})
        Scanner sc = new Scanner(System.in);
        ShopService shopService = new ShopService();
        for (;;) {
            System.out.print(">  ");
            String line = sc.nextLine();
            Command command = new CommandBuilder().parseLine(line).build();

            switch (command.getOpcode()) {
                case ADD_SHOP:
                    shopService.addShop(command.getArgs()[0]);
                    break;
                case ADD_PRODUCT:
                    shopService.addProduct(command.getArgs()[0], command.getArgs()[1]);
                    break;
                case CREATE_PRODUCT:
                    shopService.createProduct(command.getArgs()[0], Integer.parseInt(command.getArgs()[1]));
                    break;
                case PRODUCTS_STATISTIC:
                    shopService.productStatistic();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    System.out.println("\tADD_SHOP - Команда добавления магазина \tПример: ADD_SHOP Девяточка\n" +
                            "\tCREATE_PRODUCT - Команда добавления товара \tПример: CREATE_PRODUCT Вафли 54\n" +
                            "\tADD_PRODUCT - Команда добавления товара в магазин \tПример: ADD_PRODUCT Вафли Девяточка\n" +
                            "\tPRODUCTS_STATISTIC - Команда получения информации о товарах во всех магазинах\n" +
                            "\tEXIT - Команда завершения работы приложения");
                    break;
            }
        }
    }
}
