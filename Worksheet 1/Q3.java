class Test{
    private int value=10;

    public void showValue(){
        System.out.println(value);
    }

    public void setValue(int v){
        this.value=v;
    }
}

public class Q3 {
       public static void main(String args[]){
        Test T1= new Test();
        // value=19;
        T1.setValue(20);
        T1.showValue();
       }
}