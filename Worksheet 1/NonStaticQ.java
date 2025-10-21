public class NonStaticQ {
    public void greet(){
        System.out.println("HELLO WORLD!");
    }
    public static void main(String args[]){
        NonStaticQ obj= new NonStaticQ();
        obj.greet();
    }
}

/*
We have to use static when the method logic does not depend on object state — e.g., utility functions, Math.max().
We have to use non-static when the method needs to work with object-specific data.
 */