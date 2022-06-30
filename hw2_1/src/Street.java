import java.util.LinkedList;

/**
 * This class keeps all building data in street
 */

public class Street{

    private int LENGTH;
    private LinkedList<House> houses;
    private LinkedList<Office> offices;
    private LinkedList<Market> markets;
    private LinkedList<PlayGround> playGrounds;
    /** leftPositions array keeps occupancy information of left of street*/
    private boolean [] leftPositions;
    /** rightPositions array keeps occupancy information of right of street*/
    private boolean [] rightPositions;
    /** keeps remaining land on the street */
    private int remainingLand;

    /**
     * Constructor initializes building linkedLists, and initializes street positions to false because of there is no building
     * @param length street length for one side
     */

    public Street(int length){

        LENGTH = 2*length;
        remainingLand = LENGTH;
        playGrounds = new LinkedList<>();
        leftPositions = new boolean[LENGTH/2];
        rightPositions = new boolean[LENGTH/2];
        houses = new LinkedList<>();
        markets = new LinkedList<>();
        offices = new LinkedList<>();

        for(int i=0;i<LENGTH/2;i++) {
            leftPositions[i] = false;
            rightPositions[i] = false;
        }
    }

    /**
     * Checks collision with specific location while adding a building
     * @param building building that will add
     * @return boolean expression, if there is collision returns true
     */

    public boolean checkCollision(Building building){

        for(int i=building.getPosition();i<building.getLENGTH()+building.getPosition()+1;i++) {
            if(building.getSide() == 'L')
                if (leftPositions[i])
                    return true;
            else
                if (rightPositions[i])
                    return true;
        }
        return false;
    }

    /**
     * adds new building to linkedList according to type of building
     * @param building building that will add
     */

    public void addBuilding(Building building){

        try{
            if (!checkCollision(building)) {
                if (building instanceof House)
                    houses.add((House) building);

                else if (building instanceof Office)
                    offices.add((Office) building);

                else if(building instanceof PlayGround)
                    playGrounds.add((PlayGround) building);

                else
                    markets.add((Market) building);

                for (int i = building.getPosition(); i < building.getPosition() + building.getLENGTH(); i++) {
                    if(building.getSide() == 'L')
                        leftPositions[i] = true;
                    else
                        rightPositions[i] = true;
                }
                remainingLand(building.getLENGTH(), true);
            }
            else
                throw new Exception();
        }
        catch(Exception e){
            System.out.println("There is no space in street for this building..");
        }
    }

    /**
     * deletes building from linkedList according to type of building
     * @param position position of building that will delete
     * @param side side of building that will delete
     */

    public void deleteBuilding(int position, char side){

        Building building = null;
        boolean flag = false;

        try{
            for(House temp : houses)
                if(temp.getPosition() == position && temp.getSide() == side) {
                    houses.remove(temp);
                    building = temp;
                    flag = true;
                }

            for(Office temp : offices)
                if(temp.getPosition() == position && temp.getSide() == side) {
                    offices.remove(temp);
                    building = temp;
                    flag = true;
                }

            for(Market temp : markets)
                if(temp.getPosition() == position && temp.getSide() == side) {
                    markets.remove(temp);
                    building = temp;
                    flag = true;
                }

            for(PlayGround temp : playGrounds)
                if(temp.getPosition() == position && temp.getSide() == side) {
                    playGrounds.remove(temp);
                    building = temp;
                    flag = true;
                }

            if(flag){
                remainingLand(building.getLENGTH(), false);

                for (int i = building.getPosition(); i < building.getPosition() + building.getLENGTH(); i++) {
                    if (building.getSide() == 'L')
                        leftPositions[i] = false;
                    else
                        rightPositions[i] = false;
                }
            }
            else
                throw new Exception();
        }
        catch (Exception e){
            System.out.println("There is no such building...");
        }
    }

    /**
     * Calculates remaining land at street
     * @param length length of added/deleted building
     * @param flag flag gives information about that building is deleted or added
     */

    public void remainingLand(int length, boolean flag){

        if(flag)
            remainingLand -= length;
        else
            remainingLand += length;
    }

    /**
     * @return remaining land at street
     */

    public int displayRemainingLand(){
        return remainingLand;
    }

    /**
     * Calculates total playground length at street
     * @return total playground length
     */

    public int totalPlayGroundLength(){

        int totalLen=0;

        for(PlayGround pg : playGrounds)
            totalLen += pg.getLENGTH();

        return totalLen;
    }

    /**
     * Displays occupied length by houses, markets and offices
     */

    public void displayOccupiedLengthByBuildings(){

        int houseLen=0, marketLen=0, officeLen=0;

        for(House temp : houses)
            houseLen += temp.getLENGTH();

        for(Market temp : markets)
            marketLen += temp.getLENGTH();

        for(Office temp : offices)
            officeLen += temp.getLENGTH();

        System.out.printf("\nTotal length of street occupied by the houses is %d\n", houseLen);
        System.out.printf("Total length of street occupied by the markets is %d\n", marketLen);
        System.out.printf("Total length of street occupied by the offices is %d\n", officeLen);
    }

    /**
     * Displays position, length, height and side of all building
     */

    public void displayBuildings(){

        System.out.print("\n\n");
        for(House temp : houses)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", temp.getPosition(), temp.getLENGTH(), temp.getHEIGHT(), temp.getSide());

        for(Office temp : offices)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", temp.getPosition(), temp.getLENGTH(), temp.getHEIGHT(), temp.getSide());

        for(Market temp : markets)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", temp.getPosition(), temp.getLENGTH(), temp.getHEIGHT(), temp.getSide());

        for(PlayGround temp : playGrounds)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", temp.getPosition(), temp.getLENGTH(), temp.getHEIGHT(), temp.getSide());

        System.out.print("\n\n");
    }

    /**
     * @return returns number of playgrounds at street
     */

    public int numberOfPlaygrounds(){
        return playGrounds.size();
    }

    /**
     * @return returns ratio of length of playgrounds in the street
     */

    public double ratioOfPlaygrounds(){
        return (double) totalPlayGroundLength()/(double) LENGTH;
    }

    /**
     * finds maximum height in street
     * @return maximum height
     */

    public int findMaxHeight(){

        int max=0;

        for(House temp : houses)
            if(temp.getHEIGHT()>max)
                max = temp.getHEIGHT();

        for(Market temp : markets)
            if(temp.getHEIGHT()>max)
                max = temp.getHEIGHT();

        for(Office temp : offices)
            if(temp.getHEIGHT()>max)
                max = temp.getHEIGHT();

        return max;
    }

    /**
     * Checks if there is a building at the given x position, if there is more than one building, it returns the larger one
     * @param x x axis value
     * @return returns building at x
     */

    public Building isThereBuilding(int x){

        boolean flag = false;
        Building building = null;

        for(House temp : houses){
            if(temp != null && temp.getPosition() <= x && x <= temp.getPosition() + temp.getLENGTH()){
                if(!flag) {
                    building = temp;
                    flag = true;
                }
                else if(temp.getHEIGHT() > building.getHEIGHT())
                    building = temp;
            }
        }

        for(Office temp : offices){
            if (temp != null && temp.getPosition() <= x && x <= temp.getPosition() + temp.getLENGTH()) {
                if (!flag){
                    building = temp;
                    flag = true;
                }
                else if(temp.getHEIGHT() > building.getHEIGHT())
                    building = temp;
            }
        }

        for(Market temp : markets){
            if (temp != null && temp.getPosition() <= x && x <= temp.getPosition() + temp.getLENGTH()){
                if (!flag){
                    building = temp;
                    flag = true;
                }
                else if(temp.getHEIGHT() > building.getHEIGHT())
                    building = temp;
            }
        }
        return building;
    }

    /**
     * Draws skyline silhouette
     */

    public void skylineSilhouette(){

        Building building, previous = null;

        for(int i=findMaxHeight();i>-1;i--){
            for(int k=0;k<LENGTH/2;k++){
                building = isThereBuilding(k);

                if(k > 0 && isThereBuilding(k-1) != building)
                    previous = isThereBuilding(k-1);

                if(building != null && i == building.getHEIGHT())
                    System.out.print('-');

                else if(building == null || i>building.getHEIGHT() || (k>building.getPosition() && k<building.getPosition() + building.getLENGTH())
                       || (previous != null && previous.getPosition() <= building.getPosition() && building.getPosition() <= previous.getPosition() + previous.getLENGTH()
                           && i <= previous.getHEIGHT() && k != building.getPosition() + building.getLENGTH()))
                    System.out.print(' ');

                else if(i<building.getHEIGHT() && (k == building.getPosition()))
                    System.out.print('|');

                else if(k == building.getPosition() + building.getLENGTH())
                    System.out.print('|');
            }
            System.out.print("\n");
        }
    }
}