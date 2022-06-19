import java.util.HashMap;
import java.util.Random;

public class Bank extends Thread {

    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(HashMap<String, Account> accountHashMap, String fromAccountNum, String toAccountNum, long amount) {
        long moneySource = accountHashMap.get(fromAccountNum).getMoney();
        long moneyTarget = accountHashMap.get(toAccountNum).getMoney();
        boolean isBlocked = false;

        if (amount <= 0) {
            System.out.println("Wrong transfer amount");
        }
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accountHashMap.get(fromAccountNum).setBlocked(true);
                    accountHashMap.get(toAccountNum).setBlocked(true);
                    isBlocked = true;
                    System.out.println("Operation is illegal, bank accounts are blocked!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (moneySource < amount) {
            System.out.println("Not enough money at source account");
        }
        if (moneySource >= amount && !isBlocked&&!fromAccountNum.equals(toAccountNum)) {
            synchronized (toAccountNum) {
                synchronized (fromAccountNum) {
                    accountHashMap.get(fromAccountNum).setMoney(moneySource - amount);
                    accountHashMap.get(toAccountNum).setMoney(moneyTarget + amount);
                    System.out.println("Transfer of " + amount + " from account # " + fromAccountNum
                            + " to account # " + toAccountNum + " is completed\n");
                }
            }
        } else {
            System.out.println("Cannot complete the operation\n");
        }
    }

    public void createRandomTransfers(Bank bank, HashMap bankAccounts, int transfersNumber) {
        for (int i = 0; i < transfersNumber; i++) {
            String accFrom = ("" + (int) (Math.random() * 500));
            String accTo = ("" + (int) (Math.random() * 500));
            long amountRnd = (long) (Math.random() * 55000 + 1);
            System.out.println("Try to transfer " + amountRnd + " from account #" + accFrom + " to account #" + accTo);
            synchronized (accTo) {
                synchronized (accFrom) {
                    bank.transfer(bankAccounts, accFrom, accTo, amountRnd);
                }
            }
        }
    }

    public HashMap createAccountCollection(int accountsQuantity) {
        HashMap<String, Account> bankAccounts = new HashMap<>();
        for (int i = 0; i < accountsQuantity; i++) {
            Account account = new Account(Integer.toString(i), (long) (Math.random() * 120000), false);
            bankAccounts.put(Integer.toString(i), account);
            System.out.println(account.getAccNumber() + " " + account.getMoney() + " unblocked: " + !account.isBlocked());
        }
        return bankAccounts;
    }


    @Override
    public void run() {
        HashMap<String, Account> bankAccounts = new HashMap<>();
        Bank bank = new Bank(bankAccounts);

        bankAccounts = bank.createAccountCollection(Main.bankAccountQ);
        System.out.println(bank.getSumAllAccounts(bankAccounts) + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        bank.createRandomTransfers(bank, bankAccounts, Main.numTransfers);
        System.out.println(bank.getSumAllAccounts(bankAccounts) + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String num = ("" + (int) (Math.random() * 3000));
        System.out.println(num + "  " + bank.getBalance(bankAccounts, num) + "\n");
        System.out.println("Thread is finished!\n");

    }

    public long getBalance(HashMap<String, Account> accountHashMap, String accountNum)
            throws NullPointerException {
        long moneyOnAccount = accountHashMap.get(accountNum).getMoney();
        return moneyOnAccount;
    }

    public long getSumAllAccounts(HashMap<String, Account> account) {
        long sum = 0;
        for (HashMap.Entry<String, Account> entry : account.entrySet()) {
            sum = sum + entry.getValue().getMoney();
        }
        return sum;
    }
}
