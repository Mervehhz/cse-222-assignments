/**
 * class that find contiguous subarray/s that the sum of its/theirs items is equal to a given integer value
 */

public class ContiguousSubarrays {

    /**
     * recursive algorithm that find contiguous subarray/s that the sum of its/theirs items is equal to a given integer value
     * @param arr array that will include the numbers
     * @param num check the sum is equal to this number
     * @param index cursor index 1
     * @param sum add each arr[index] to this variable
     * @param index_temp cursor index 2
     */
    public void isSumOfElementsEqualToNum(int [] arr, int num, int index, int sum, int index_temp){

        if(index < arr.length) {

            if (arr[index] < num && sum < num && index_temp < arr.length) {
                sum += arr[index_temp];

                if (sum == num) {
                    System.out.print("[");
                    for (int i = index_temp ; sum > 0 && i>-1; i--) {
                        System.out.print(arr[i]);
                        if(sum - arr[i] > 0)
                            System.out.print(",");
                        sum -= arr[i];
                    }
                    System.out.println("]");
                }
                isSumOfElementsEqualToNum(arr, num, index, sum, index_temp += 1);
            }
            else
                isSumOfElementsEqualToNum(arr, num, index += 1, sum = 0, index_temp = index);
        }
    }
}