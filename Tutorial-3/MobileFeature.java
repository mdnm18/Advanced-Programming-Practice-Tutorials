class Mobile{
    String brand;
    String model;
    double price;

    Mobile(String brand, String model, double price){
        this.brand=brand;
        this.model=model;
        this.price=price;
    }

    void showDetails(){
        System.out.println("BRAND : "+this.brand+", MODEL : "+this.model+", PRICE : "+this.price);
    }
}

public class MobileFeature {
    public static void main(String args[]){
        Mobile m1= new Mobile("Lava", "Agni 2", 200000);
       Mobile m2= new Mobile("Samsung", "s24", 1000000);
       Mobile m3= new Mobile("Nothing", "Phone(3)", 500000); 

        m1.showDetails();
        m2.showDetails();
        m3.showDetails();
    }
}
