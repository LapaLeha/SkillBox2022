import java.util.HashMap;

public class Main {

    public final static int bankAccountQ = 30;
    public final static int numTransfers = 10;

    public static void main(String[] args) {

        HashMap<String, Account> bankAccounts1 = new HashMap<>();
        HashMap<String, Account> bankAccounts2 = new HashMap<>();
        HashMap<String, Account> bankAccounts3 = new HashMap<>();

        Bank bank1 = new Bank(bankAccounts1);
        Bank bank2 = new Bank(bankAccounts2);
        Bank bank3 = new Bank(bankAccounts3);

        bank1.start();
        bank2.start();
        bank3.start();
    }
}
