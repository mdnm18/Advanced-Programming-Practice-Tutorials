
class Circle {

    double circle;

    Circle(double c) {
        this.circle = c;
    }

    double calculateArea() {
        return Math.PI * circle * circle;
    }
}

public class CircleRadius {

    public static void main(String args[]) {
        Circle c1 = new Circle(45);
        System.out.println(c1.calculateArea());
    }
}
