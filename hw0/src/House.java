/**
 * House class extends Building class
 */

public class House extends Building{

    private int rooms;
    private String color;
    private String owner;

    /**
     * @return returns room number of house
     */

    public int getRooms(){
        return rooms;
    }

    /**
     * Sets room number of house
     * @param rooms room number of house
     */

    public void setRooms(int rooms){
        this.rooms = rooms;
    }

    /**
     * @return returns color of house
     */

    public String getColor(){
        return color;
    }

    /**
     * Sets color of house
     * @param color color of house
     */

    public void setColor(String color){
        this.color = color;
    }

    /**
     * @return returns owner of house
     */

    public String getOwner() { return owner; }

    /**
     * Sets owner of house
     * @param owner owner of house
     */

    public void setOwner(String owner) { this.owner = owner; }

    /**
     * overridden method
     * @return owner of house
     */

    public String toString(){
        return this.owner + "'s house";
    }

    /**
     * House focuses owner of house
     */

    public void focus(){
        System.out.println(toString());
    }
}