class Book{
    String title;
    String author;
    double price;

    Book(String title, String author, double price){
        this.title=title;
        this.author=author;
        this.price=price;
    }

    double discountedPrice(double disPercentage){
        return this.price - (this.price * disPercentage/100);
    }
}

public class BookDiscount {
    public static void main(String args[]){
        Book b1= new Book("SRM", "Nayaj", 1000);
        System.out.println("FINAL PRICE AFTER DISCOUNT : "+b1.discountedPrice(10));
    }
}