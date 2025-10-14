public class BankingSystemUsingProcedure {
    public static void main(String args[]){
        double balance[]={2344.00,3224.0,3763.0,3673.00};
        double total=0;
        for(int i=0; i<balance.length;i++){
            total+=balance[i];
        }
        System.out.println("TOTAL BALANCE : "+total);
    }
}
