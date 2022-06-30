/**
 * Market class extends Building class
 */

public class Market extends Building{

    /** opening time of the market */
    private int openTime;
    /** closing time of the market */
    private int closeTime;
    /** owner of the market */
    private String owner;

    /**
     * @return open time of market
     */

    public int getOpenTime(){
        return openTime;
    }

    /**
     * Sets open time of market
     * @param open open time of market
     */

    public void setOpenTime(int open){
        openTime = open;
    }

    /**
     * @return returns close time of market
     */

    public int getCloseTime(){
        return closeTime;
    }

    /**
     * Sets close time of market
     * @param close close time of market
     */

    public void setCloseTime(int close){
        closeTime = close;
    }

    /**
     * @return returns owner of market
     */

    public String getOwner() { return owner; }

    /**
     * Sets owner of market
     * @param owner owner of market
     */

    public void setOwner(String owner) { this.owner = owner; }

    /**
     * overridden method
     * @return close time of market
     */

    public String toString(){
        return "Market that closes at " + this.closeTime;
    }

    /**
     * Market focuses close time
     */

    public void focus(){
        System.out.println(toString());
    }
}