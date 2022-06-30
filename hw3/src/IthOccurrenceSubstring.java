import java.lang.String;

/**
 * search a given string in another given bigger string
 */
public class IthOccurrenceSubstring {

    /**
     * search a given string in another given bigger string
     * @param i occurence number that will find
     * @param index to search index by index
     * @param queryString string that will find
     * @param bigString the searched query string in this string
     * @return returns the index of the i th occurrence of the query string and return -1
     * when the query string doesn’t occur in the big string or the number of occurences is less
     * than i.
     */
    public int findIthOccurrence(int i, int index, String queryString, String bigString){

        if(i == 0 || index == bigString.length() || index+queryString.length() > bigString.length() || queryString.length() == 0 || bigString.length() == 0)
            return -1;

        if(i == 1 && bigString.substring(index, index+queryString.length()).equals(queryString))
            return index;

        else if(i > 1 && bigString.substring(index, index+queryString.length()).equals(queryString))
            return findIthOccurrence(i-=1, index+=1, queryString, bigString);

        else
            return findIthOccurrence(i, index+=1, queryString, bigString);
    }
}