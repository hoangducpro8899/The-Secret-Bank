import java.util.Scanner;

public abstract class Account implements AccountAction{
    private String accountID, accountName, password;
    private double money;
    Scanner sc = new Scanner(System.in);

    //Constructors
    public Account() {
    }

    public Account(String accountID, String accountName, String password, double money) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.password = password;
        money = 0;
    }

    //Getters and Setters
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return accountID + " " + accountName + " " + password + " " + money;
    }

    @Override
    public void show() {
        System.out.printf("%-4s %-8s %-8s %.1f\n", accountID, accountName, password, money);
        System.out.println(toString());
    }

    @Override
    public void goodBye() {
        System.out.println("--------------- GOOD BYE ---------------\n");
    }
    
}
