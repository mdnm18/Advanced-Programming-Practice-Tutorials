class BankAccount{
    int accountNumber;
    String accountHolder;
    double balance;

    BankAccount(int no, String name, double b){
        this.accountNumber=no;
        this.accountHolder=name;
        this.balance=b;
    }

    void displayBalance(){
        System.out.println("BANK ACCOUNT NUMBER : "+this.accountNumber+", ACCOUNT HOLDER NAME : "+this.accountHolder+", AVAILABLE BALANCE : "+this.balance);
    }
}

public class BankAcDetails{
    public static void main(String args[]){
        BankAccount ac1 = new BankAccount(18, "Nayaj", 5000);
        ac1.displayBalance();
    }
}