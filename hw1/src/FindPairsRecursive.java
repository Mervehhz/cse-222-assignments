public class FindPairsRecursive{

    public void findPairs(int [] numbers, int sum, int c_index, int next_index){

        if(c_index == numbers.length-1)
            return;

        if(numbers[c_index] + numbers[next_index] == sum)
            System.out.printf("sum is %d\n%d, %d \n", sum, numbers[c_index], numbers[next_index]);

        if(next_index == numbers.length-1)
            findPairs(numbers, sum, c_index+1, next_index-numbers.length+2);

        else
            findPairs(numbers, sum, c_index, next_index+1);
    }

}