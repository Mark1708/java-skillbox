import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "mArk2001";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

//            Statement statement = connection.createStatement();

            String SQL = "SELECT PurchaseList.course_name,COUNT(PurchaseList.course_name)/12 AS qty FROM PurchaseList WHERE YEAR(PurchaseList.subscription_date) = (?) GROUP BY PurchaseList.course_name;";

//            ResultSet resultSet = statement.executeQuery(SQL);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, 2018);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                System.out.printf("| %30s | %2.3f |\n", resultSet.getString(1), Double.parseDouble(resultSet.getString(2)));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
