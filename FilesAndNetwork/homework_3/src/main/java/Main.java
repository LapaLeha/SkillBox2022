import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Movements movements = new Movements("data/movementList.csv");
        movements.parse();
        System.out.print("Сумма расходов: ");
        System.out.println(movements.getExpenseSum());
        System.out.print("Сумма доходов: ");
        System.out.println(movements.getIncomeSum());
        System.out.println();
        System.out.println("Суммы расходов по организациям:");
        Set<String> company = movements.getCompanyOfExpense();

        for (String c:company){
            System.out.println(c);
        }
    }
}
