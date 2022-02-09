public class Arithmetic {
    private int a;
    private  int b;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int sum (){
        return a+b;
    }

    public int product (){
        return a*b;
    }

    public int maxumum (){
        int result = 0;
        if (a>b){
            result= a;
        }
        if (a<b){
            result = b;
        }
        if (a==b){
            result = a;
        }
        return result;
    }

    public int minimum (){
        int result =0;
        if (a<b){
            result = a;
        }
        if (a>b){
            result = b;
        }
        if (a==b){
            result = a;
        }
        return result;
    }
}
