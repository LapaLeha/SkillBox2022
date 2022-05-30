import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox16_4";
        String user = "root";
        String pass = "4521Kfgfx26)";
        ArrayList<String> nameCourses = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");

            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                nameCourses.add(courseName);

            }
            String commandStart = "SELECT pl.course_name, pl.subscription_date FROM PurchaseList pl WHERE pl.course_name";
            for (int i = 0; i < nameCourses.size(); i++) {
                String coursesIter = nameCourses.get(i);
                String commandEnd = commandStart+ " = \"" + coursesIter + "\" ORDER BY pl.subscription_date";
                ResultSet resultSet2 = statement.executeQuery(commandEnd);
                double countPurchase = 1;
                while (resultSet2.next()){

                    countPurchase+=countPurchase;
                }
                countPurchase=countPurchase/12;
                System.out.println("Cреднее количество покупок в месяц за 2018 год для курса "+"\""+coursesIter+ "\""
                        +" - "+countPurchase);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
