public class Main{

    public static void main(String [] args){

        FindPairs pairs = new FindPairs();
        FindPairsRecursive pairs2 = new FindPairsRecursive();
        int [] numbers = new int[10];

        numbers[0] = 3;
        numbers[1] = 1;
        numbers[2] = 3;
        numbers[3] = 6;
        numbers[4] = 8;
        numbers[5] = 9;
        numbers[6] = 5;
        numbers[7] = 2;
        numbers[8] = 4;
        numbers[9] = 7;

        /*numbers[0] = 3;
        numbers[1] = 1;
        numbers[2] = 29;
        numbers[3] = 6;
        numbers[4] = 8;
        numbers[5] = 9;
        numbers[6] = 5;
        numbers[7] = 2;
        numbers[8] = 4;
        numbers[9] = 7;
        numbers[10] = 32;
        numbers[11] = 5;
        numbers[12] = 25;
        numbers[13] = 34;
        numbers[14] = 12;
        numbers[15] = 23;
        numbers[16] = 14;
        numbers[17] = 15;
        numbers[18] = 17;
        numbers[19] = 19;*/


        long start = System.nanoTime();
        pairs.findPairs(numbers, 6);
        long end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds: "+ (double)(end-start)/1000000000 + "\n\n");

        long start2 = System.nanoTime();
        pairs2.findPairs(numbers, 6, 0, 1);
        long end2 = System.nanoTime();

        System.out.println("\nelapsed Time in seconds: "+ (double)(end2-start2)/1000000000);
    }
}