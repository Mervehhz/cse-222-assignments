public class FindPairs{

    public void findPairs(int [] numbers, int sum){

        for(int i=0;i<numbers.length;i++){
            for(int k=i+1;k<numbers.length;k++)
                if((numbers[i] + numbers[k]) == sum)
                    System.out.printf("sum is %d\n%d, %d \n", sum, numbers[i], numbers[k]);
        }
    }
}