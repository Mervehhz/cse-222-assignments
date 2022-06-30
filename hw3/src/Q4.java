/**
 * class for multiplication
 */

public class Q4 {

    public int foo(int integer1,int integer2){

        if ((integer1< 10) || (integer2 < 10)) return integer1*integer2;

        int n = Math.max(number_of_digits(integer1),number_of_digits(integer2));
        int half = (int)n/2;
        int int1 = split_integer(integer1,half);
        int int2 = split_integer2(integer1,half);
        int int3 = split_integer(integer2,half);
        int int4 = split_integer2(integer2,half);

        int sub0 = foo(int2,int4);
        int sub1 = foo((int2+int1),(int4+int3));
        int sub2 = foo(int1,int3);

        return (int) (sub2*Math.pow(10,2*half)) + (int)((sub1-sub2-sub0)*Math.pow(10,half))+(sub0);
    }

    private static int split_integer(int integer, int half){
        return (int)(integer / Math.pow(10,half)) ;
    }

    private static int split_integer2(int integer,int half){
        return (int)(integer % Math.pow(10,half)) ;
    }

    private static int number_of_digits(int integer){
        return String.valueOf(integer).length();
    }
}