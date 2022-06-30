import java.util.ArrayList;
import java.util.Random;

/**
 * calculates all the possible combinations to fill this matrix by using snakes of length exactly n
 */
public class SnakeCombinationsInMatrix {

    private ArrayList<ArrayList<Integer>> allCombinations;

    public SnakeCombinationsInMatrix(){

        allCombinations = new ArrayList<>();
    }

    public void snakeCombinationsNxNMatrix(ArrayList<Integer> matrix, int n, int index, int snake_len, int snake_num){

        if(snake_num < n) {
            int d = generateRandomIndex(index, n);

            if (n == snake_len)
                snakeCombinationsNxNMatrix(matrix, n, generateRandomIndex(n, matrix), 0, snake_num + 1);
            else {
                if (d == 0 && matrix.get(index - 1) == 0) {
                    matrix.set(index, 1);
                    snakeCombinationsNxNMatrix(matrix, n, index - 1, snake_len + 1, snake_num);
                } else if (d == 1 && matrix.get(index + 1) == 0) {
                    matrix.set(index, 1);
                    snakeCombinationsNxNMatrix(matrix, n, index + 1, snake_len + 1, snake_num);
                } else if (d == 2 && matrix.get(index - n) == 0) {
                    matrix.set(index, 1);
                    snakeCombinationsNxNMatrix(matrix, n, index - n, snake_len + 1, snake_num);
                } else if (d == 3 && matrix.get(index + n) == 0) {
                    matrix.set(index, 1);
                    snakeCombinationsNxNMatrix(matrix, n, index + n, snake_len + 1, snake_num);
                }
            }
        }
        printMatrix(matrix, n);
    }

    /**
     * Left-> 0, Right-> 1, Up-> 2, Down-> 3
     * @return returns direction number
     */
    public int generateRandomIndex(int index, int n){
        Random rand = new Random();
        int rd = rand.nextInt(4);

        while ((rd == 0 && (index - 1 >= n * n || index - 1 < 0)) || (rd == 1 && (index + 1 >= n * n || index + 1 < 0))
               || (rd == 2 && (index - n >= n * n || index - n < 0)) || (rd == 3 && (index + n >= n * n || index + n < 0)))
            rd = rand.nextInt(4);

        return rd;
    }

    public int generateRandomIndex(int n, ArrayList<Integer> matrix){
        Random rand = new Random();
        int rd = rand.nextInt(n);

        while(matrix.get(rd) != 0)
            rd = rand.nextInt(n);

        return rd;
    }

    public void printMatrix(ArrayList<Integer> matrix, int n){

        for(int i=0;i<n*n;i++) {
            if(i % 5 == 0) System.out.print("\n");
            if (matrix.get(i) == 1)
                System.out.print(i);
        }
        System.out.print("\n");
    }

    /*private void putCombinationToArray(ArrayList<Integer> put){


    }

    private boolean checkDifferentCombination(ArrayList<Integer> comb){


    }*/
}









