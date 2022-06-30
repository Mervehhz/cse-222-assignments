/**
 * calculates all the possible configurations to fill this array with colored-blocks
 * with length at least 3
 */

public class ColoredBlocks {

    /**
     * it starts from the first index and continues by shifting the indexes
     * at a certain block length, then after suppressing all combinations,
     * it increases the block length and continues in a recursive manner.
     * @param emptyArr array with filled zero
     * @param index index
     * @param blockLength block length
     * @param blockNum block number
     */
    public void possibleColoredBlocks(int [] emptyArr, int index, int blockLength, int blockNum){

        if(index < emptyArr.length && blockLength+index <= emptyArr.length) {

            helper(emptyArr, index, blockLength, blockNum);

            if (index + blockLength < emptyArr.length)
                possibleColoredBlocks(emptyArr, index += 1, blockLength, 1);
            else
                possibleColoredBlocks(emptyArr, 0, blockLength += 1, 1);
        }
    }

    /**
     * prints all combinations for certain index and block length
     * @param emptyArr array with filled zero
     * @param index index
     * @param blockLength block length
     * @param blockNum block number
     */
    private void helper(int [] emptyArr, int index, int blockLength, int blockNum){

        int i,k;
        if(index < emptyArr.length && blockLength+index <= emptyArr.length) {
            for (i = index; i < blockLength + index && blockLength + index <= emptyArr.length; i++)
                emptyArr[i] = 1;

            System.out.print("[");
            for(k=0;k<emptyArr.length;k++) {
                System.out.print(emptyArr[k]);
                if(k+1 != emptyArr.length) System.out.print(",");
            }
            System.out.println("]");

            if (blockNum < emptyArr.length / (blockLength + 1))
                helper(emptyArr, index + blockLength + 1, blockLength, blockNum += 1);
        }
        for(k=0;k<emptyArr.length;k++) emptyArr[k] = 0;
    }
}

















