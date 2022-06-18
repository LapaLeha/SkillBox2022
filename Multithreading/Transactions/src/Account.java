public class Account {

    private long money;
    private String accNumber;
    boolean isBlocked;

    public Account(String accNumber, long money, boolean isBlocked) {
        this.accNumber = accNumber;
        this.money = money;
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }



}
