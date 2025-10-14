public class EmployeeProdural {
    public static void main(String args[]){
        String names[]={"Nayaj","Virat","Rohit","Jasprit"};
        double salaries[]={440000.5,760000.6,480000.6,900000.0};

        double maxSalary=salaries[0];
        String highestPaid=names[0];

        for(int i=1; i<salaries.length;i++){
            if(salaries[i]>maxSalary){
                maxSalary=salaries[i];
                highestPaid=names[i];
            }
        }
        System.out.println("Highest Paid: "+highestPaid+" with ₹"+maxSalary);
    }
}
