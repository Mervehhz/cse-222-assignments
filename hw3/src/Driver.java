import java.util.ArrayList;

/**
 * Driver class
 */

public class Driver {

    /**
     * main
     * @param args command-line argument
     */
    public static void main(String[] args) {

        IthOccurrenceSubstring subStr = new IthOccurrenceSubstring();
        FindItemsBetweenNumbers num = new FindItemsBetweenNumbers();
        ContiguousSubarrays subArr = new ContiguousSubarrays();
        ColoredBlocks clrBlock = new ColoredBlocks();
        SnakeCombinationsInMatrix snake = new SnakeCombinationsInMatrix();
        Q4 q4 = new Q4();

        ArrayList<Integer> matrix = new ArrayList<>();

        System.out.println("query string: batuhan");
        System.out.println("big string: dgdshmervejdfmervedjflsmervedkjfsfldkfayse\n");
        System.out.println("if the query string is not in the big string returns " + subStr.findIthOccurrence(1, 0, "batuhan", "dgdshmervejdfmervedjflsmervedkjfsfldkfayse"));

        System.out.println("\nquery string: merve");
        System.out.println("big string: dgdshmervejdfmervedjflsmervedkjfsfldkfayse\n");
        System.out.println("if the number of occurrences of query string is less than i returns " + subStr.findIthOccurrence(6, 0, "merve", "dgdshmervejdfmervedjflsmervedkjfsfldkfayse"));


        System.out.println("\nif length of query string is 0 returns " + subStr.findIthOccurrence(6, 0, "", "dgdshmervejdfmervedjflsmervedkjfsfldkfayse"));
        System.out.println("\nif length of big string is 0 returns " + subStr.findIthOccurrence(6, 0, "merve", ""));

        System.out.println("\nquery string: batuhan");
        System.out.println("big string: dgdshmervejdfmervedbatuhanjflsmervedkjfsfldkfayse\n");
        System.out.println("index in normal condition " + subStr.findIthOccurrence(1, 0, "batuhan", "dgdshmervejdfmervedbatuhanjflsmervedkjfsfldkfayse"));

        System.out.println("\n");

        int [] arr = {1,2,3,14,15,16,17,19,23,24,25,26,27};
        System.out.println("{1,2,3,14,15,16,17,19,23,24,25,26,27}->boundary numbers(2,23) in the array, item number : " + num.findItems(arr, 2, 23));
        System.out.println("{1,2,3,14,15,16,17,19,23,24,25,26,27}->boundary numbers(7,22) are not in the array, item number : " + num.findItems(arr, 7, 22));
        System.out.println("{1,2,3,14,15,16,17,19,23,24,25,26,27}->boundary numbers(7,30) are overflow from the array, item number : " + num.findItems(arr, 7, 30));


        int [] arr2 = {1,2,3,14,7,4,16,5,3,5,22,26,27};
        System.out.println("sub array -> {1,2,3,14,7,4,16,5,3,5,22,26,27}");
        System.out.println("\nif sum is not equal to num it prints nothing");
        subArr.isSumOfElementsEqualToNum(arr2,16,0,0, 0);
        System.out.println("\n\n");
        System.out.println("\ncontiguous sub array under normal condition");
        subArr.isSumOfElementsEqualToNum(arr2,25,0,0, 0);

        System.out.println("\nmultiplication : " + q4.foo(155, 140));

        int [] arr3 = {0,0,0,0,0,0,0};

        System.out.println("\npossible colored blocks");
        clrBlock.possibleColoredBlocks(arr3, 0, 3, 0);

        /*for(int i=0;i<25;i++)
            matrix.add(i, 0);

        snake.snakeCombinationsNxNMatrix(matrix, 5, 0, 0, 0);*/


    }
}














