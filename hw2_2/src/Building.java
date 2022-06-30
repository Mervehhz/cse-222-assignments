/**
 * Base class for buildings
 */

public class Building{

    /** length of the building */
    private int LENGTH;
    /** height of the building */
    private int HEIGHT;
    /** position of the building */
    private int position;
    /** side of the building in street */
    private char side;

    /**
     * Constructor initializes data fields LENGTH and HEIGHT to 0
     */

    public Building(){
        LENGTH = 0;
        HEIGHT = 0;
    }

    /**
     * Constructor initializes data fields according to parameters
     * @param length length of building
     * @param height height of building
     * @param position position of building
     * @param side side of building
     */

    public Building(int length, int height, int position, char side){
        LENGTH = length;
        HEIGHT = height;
        this.position = position;
        this.side = side;
    }

    /**
     * Sets length
     * @param length length of building
     */

    public void setLENGTH(int length){
        LENGTH = length;
    }

    /**
     * @return returns length of building
     */

    public int getLENGTH(){
        return LENGTH;
    }

    /**
     * Sets height of building
     * @param height height of building
     */

    public void setHEIGHT(int height){
        HEIGHT = height;
    }

    /**
     * @return height of building
     */

    public int getHEIGHT(){
        return HEIGHT;
    }

    /**
     * Sets position of building
     * @param position position of building
     */

    public void setPosition(int position){
        this.position = position;
    }

    /**
     * @return returns position of building
     */

    public int getPosition(){
        return position;
    }

    /**
     * Sets side of building
     * @param side side of building
     */

    public void setSide(char side){ this.side = side; }

    /**
     * @return side of building
     */

    public char getSide(){ return side; }

    public void focus(){
        System.out.print("");
    }
}