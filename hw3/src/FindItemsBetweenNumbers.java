/**
 * class that find the number of items in the array between two given integer values
 */

public class FindItemsBetweenNumbers {

    /**
     * find the number of items in the array between two given integer values
     * @param arr array that will include numbers
     * @param num1 first boundary
     * @param num2 second boundary
     * @return returns number of items
     */
    public int findItems(int []arr,int num1,int num2){

        int i, items=0;
        if (arr == null ) return -1;
        int index1 = helper(arr,0,arr.length,num1);
        int index2 = helper(arr,0,arr.length,num2);

        if(index2 > index1) {
            for (i = index1 + 1; i < index2; i++) items++;
            if(arr[index2] < num2) items++;
        }
        else {
            for (i = index2 + 1; i < index1; i++) items++;
            if(arr[index1] < num1) items++;
        }
        return items;
    }

    /**
     * recursive algorithm
     * @param arr array that will include numbers
     * @param start start index that will search
     * @param end end index that will search
     * @param num target number
     * @return returns the index of number if exist, if not exist returns boundary index of target
     */
    private int helper(int []arr,int start,int end,int num){

        if(start > end ) return -1;
        int middle = (start+end)/2;
        if (arr[middle] == num) return middle;
        if(arr[middle] < num && middle+1 >= arr.length) return middle;
        if (arr[middle] < num && num < arr[middle + 1]) return middle;
        if (arr[middle] > num)
            return helper(arr, start, middle - 1, num);
        if (arr[middle] < num)
            return helper(arr, middle + 1, end, num);

        return -1;
    }
}

















