class Account{
    private double balance;
    public Account(double bal){
        this.balance=bal;
    }
    public double getBalance(){
        return balance;
    }
}
public class BankingSystemUsingOOPS{
    public static void main(String args[]){
        Account[] accounts={
            new Account(47647.3),
            new Account(48478.9),
            new Account(48863.9)
        };
        double total=0;
        for(Account ac:accounts){
            total+=ac.getBalance();
        }
        System.out.println("TOTAL BALANCE : "+total);
    }
}