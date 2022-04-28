import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Movements {
    String pathMovementsCsv;
    private final List<Double> income = new ArrayList<>();
    private final List<Double> expense = new ArrayList<>();
    private final Map<String, Double> companyOfExpense = new HashMap<>();

    public Movements(String pathMovementsCsv) {
        this.pathMovementsCsv = pathMovementsCsv;
        parse();
        getCompanyOfExpense();
    }

    public void parse() {
        try {
            List<String> lines = Files.readAllLines(Path.of(pathMovementsCsv));
            for (int i = 0; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                if (lines.get(i).startsWith("Тип")) {
                    continue;
                }

                income.add(Double.parseDouble(fragments[6].replace(",", ".")
                        .replace("\"", " ")));

                double expense1 = Double.parseDouble(fragments[7]
                        .replace(",", ".").replace("\"", ""));
                expense.add(expense1);

                if (fragments.length != 8) {
                    System.out.println("Error:" + lines.get(i));
                    continue;
                }

                if (expense1!=0) {
                    String key;
                    if (!fragments[5].contains("\\")) {
                        key = fragments[5].substring(fragments[5].indexOf("/")).substring(0, fragments[5].indexOf("/"));
                    } else {
                        key = fragments[5].substring(fragments[5].indexOf("\\")).substring(0, fragments[5].indexOf("\\"));
                    }
                    if (companyOfExpense.containsKey(key)) {
                        companyOfExpense.put(key, companyOfExpense.get(key) + expense1);
                    } else {
                        companyOfExpense.put(key, expense1);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getExpenseSum() {
        double expenseSum = 0.0;
        for (Double e : expense) {
            expenseSum += e;
        }
        return expenseSum;
    }

    public double getIncomeSum() {
        double incomeSum = 0.0;
        for (Double i : income) {
            incomeSum += i;
        }
        return incomeSum;
    }

    public Set<String> getCompanyOfExpense() {
        TreeSet<String> allCompany = new TreeSet<>();
        for (String key : companyOfExpense.keySet()) {
            allCompany.add(key + "\t" + companyOfExpense.get(key));
        }
        return allCompany;
    }
}
