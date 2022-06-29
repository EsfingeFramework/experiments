package user.app;

public class User {

    private String companyName;
    private String name;
    private int age;
    private double balance;

    public User(String companyName, String name, int age,double balance) {
        this.companyName = companyName;
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getBalance(){
        return balance;
    }

    public double addFunds(double amount){
        this.balance += amount;
        return balance;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
