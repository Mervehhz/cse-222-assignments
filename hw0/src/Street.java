/**
 * This class keeps all building data in street
 */

public class Street{

    private int LENGTH;
    private House [] houses;
    private Office [] offices;
    private Market [] markets;
    private PlayGround [] playGrounds;
    private boolean [] leftPositions;
    private boolean [] rightPositions;
    private static int houseNum = 0;
    private static int officeNum = 0;
    private static int marketNum = 0;
    private static int playGroundNum = 0;
    private int remainingLand;
    private static int stripNum = 0;

    /**
     * Constructor initializes building arrays with street length, and initializes street positions to false because of there is no building
     * @param length street length for one side
     */

    public Street(int length){

        LENGTH = 2*length;
        remainingLand = LENGTH;
        playGrounds = new PlayGround[LENGTH];
        leftPositions = new boolean[LENGTH/2];
        rightPositions = new boolean[LENGTH/2];
        houses = new House[LENGTH];
        markets = new Market[LENGTH];
        offices = new Office[LENGTH];

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
     * adds new building to array according to type of building
     * @param building building that will add
     */

    public void addBuilding(Building building){

        try{
            if (!checkCollision(building)) {
                if (building instanceof House) {
                    houses[houseNum] = (House) building;
                    houseNum++;
                } else if (building instanceof Office) {
                    offices[officeNum] = (Office) building;
                    officeNum++;
                } else if(building instanceof PlayGround){
                    playGrounds[playGroundNum] = (PlayGround) building;
                    playGroundNum++;
                }
                else {
                    markets[marketNum] = (Market) building;
                    marketNum++;
                }

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
     * Checks that existing of building that given as parameter
     * @param building building
     * @return index of building if such building is exist, else returns -1
     */

    public int isBuildingExist(Building building){

        int i;

        if(building instanceof House) {
            for (i = 0; i < houseNum; i++)
                if (houses[i].getLENGTH() == building.getLENGTH() && houses[i].getPosition() == building.getPosition() && houses[i].getSide() == building.getSide())
                    return i;
        }
        else if(building instanceof Office) {
            for (i = 0; i < officeNum; i++)
                if (offices[i].getLENGTH() == building.getLENGTH() && offices[i].getPosition() == building.getPosition() && houses[i].getSide() == building.getSide())
                    return i;
        }
        else if(building instanceof PlayGround){
            for (i = 0; i < playGroundNum; i++)
                if (playGrounds[i].getLENGTH() == building.getLENGTH() && playGrounds[i].getPosition() == building.getPosition() && houses[i].getSide() == building.getSide())
                    return i;
        }
        else {
            for (i = 0; i < marketNum; i++)
                if (markets[i].getLENGTH() == building.getLENGTH() && markets[i].getPosition() == building.getPosition() && houses[i].getSide() == building.getSide())
                    return i;
        }
        return -1;
    }

    /**
     * deletes building from array according to type of building
     * @param building building that will delete
     */

    public void deleteBuilding(Building building){

        int index = isBuildingExist(building);
        try{
            if(index != -1){
                if(building instanceof House) {
                    shiftArray(houses, index, houseNum);
                    houseNum--;
                }
                else if(building instanceof Office) {
                    shiftArray(offices, index, officeNum);
                    officeNum--;
                }
                else if(building instanceof PlayGround){
                    shiftArray(playGrounds, index, playGroundNum);
                    playGroundNum--;
                }
                else {
                    shiftArray(markets, index, marketNum);
                    marketNum--;
                }
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
     * building at the end of the buildings array sets instead of index that will delete
     * @param buildings buildings array that will shift
     * @param index index of deleted building
     * @param number number of buildings
     */

    public void shiftArray(Building [] buildings, int index, int number){

        for(int i=0;i<number-1;i++)
            if(i == index)
                buildings[index] = buildings[number-1];

        buildings[number-1] = null;
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

        int temp=0;

        for(int i=0;i<playGroundNum;i++)
            temp += playGrounds[i].getLENGTH();

        return temp;
    }

    /**
     * Displays occupied length by houses, markets and offices
     */

    public void displayOccupiedLengthByBuildings(){

        System.out.printf("\nTotal length of street occupied by the houses is %d\n", occupiedLengthByBuildings(houses, houseNum));
        System.out.printf("Total length of street occupied by the markets is %d\n", occupiedLengthByBuildings(markets, marketNum));
        System.out.printf("Total length of street occupied by the offices is %d\n", occupiedLengthByBuildings(offices, officeNum));
    }

    /**
     * Calculates length of specific building
     * @param building array of buildings that only one type
     * @param number number of buildings
     * @return returns total length of specific buildings
     */

    public int occupiedLengthByBuildings(Building [] building, int number){

        int total=0;

        for(int i=0;i<number;i++)
            total += building[i].getLENGTH();

        return total;
    }

    /**
     * Displays position, length, height and side of all building
     */

    public void displayBuildings(){

        int i;

        System.out.print("\n\n");
        for(i=0;i<houseNum;i++)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", houses[i].getPosition(), houses[i].getLENGTH(), houses[i].getHEIGHT(), houses[i].getSide());

        for(i=0;i<officeNum;i++)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", offices[i].getPosition(), offices[i].getLENGTH(), offices[i].getHEIGHT(), offices[i].getSide());

        for(i=0;i<marketNum;i++)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", markets[i].getPosition(), markets[i].getLENGTH(), markets[i].getHEIGHT(), markets[i].getSide());

        for(i=0;i<playGroundNum;i++)
            System.out.printf("(Position, Length, Height, Side)-> (%d, %d, %d, %c)\n", playGrounds[i].getPosition(), playGrounds[i].getLENGTH(), playGrounds[i].getHEIGHT(), playGrounds[i].getSide());

        System.out.print("\n\n");
    }

    /**
     * @return returns number of playgrounds at street
     */

    public int numberOfPlaygrounds(){
        return playGroundNum;
    }

    /**
     * @return returns ratio of length of playgrounds in the street
     */

    public double ratioOfPlaygrounds(){
        return (double) totalPlayGroundLength()/(double) LENGTH;
    }

    /**
     * determines building according to position
     * @param position position at street
     * @param side side of building
     */

    public void determineBuildingAccordingPosition(int position, char side){

        Building building = new Building();
        int i;

        for(i=0;i<houseNum;i++)
            if(houses[i].getPosition() == position && houses[i].getSide() == side)
                building = houses[i];

        for(i=0;i<officeNum;i++)
            if(offices[i].getPosition() == position && offices[i].getSide() == side)
                building = offices[i];

        for(i=0;i<marketNum;i++)
            if(markets[i].getPosition() == position && markets[i].getSide() == side)
                building = markets[i];

        for(i=0;i<playGroundNum;i++)
            if(playGrounds[i].getPosition() == position && playGrounds[i].getSide() == side)
                building = playGrounds[i];

        deleteBuilding(building);
    }

    /**
     * finds maximum height in street
     * @return maximum height
     */

    public int findMaxHeight(){

        int i,max=0;

        for(i=0;i<houseNum;i++)
            if(houses[i].getHEIGHT()>max)
                max = houses[i].getHEIGHT();

        for(i=0;i<marketNum;i++)
            if(markets[i].getHEIGHT()>max)
                max = markets[i].getHEIGHT();

        for(i=0;i<officeNum;i++)
            if(offices[i].getHEIGHT()>max)
                max = offices[i].getHEIGHT();

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

        for(int i=0, k=0, j=0;i<houseNum || k<officeNum || j<marketNum;i++, k++, j++) {
            if (i<houseNum && houses[i].getPosition() <= x && x <= houses[i].getPosition() + houses[i].getLENGTH()) {
                if(!flag) {
                    building = houses[i];
                    flag = true;
                }
                else if(houses[i].getHEIGHT() > building.getHEIGHT())
                    building = houses[i];
            }

            if (k<officeNum && offices[k].getPosition() <= x && x <= offices[k].getPosition() + offices[k].getLENGTH()) {
                if (!flag){
                    building = offices[k];
                    flag = true;
                }
                else if(offices[k].getHEIGHT() > building.getHEIGHT())
                    building = offices[k];
            }

            if (j<marketNum && markets[j].getPosition() <= x && x <= markets[j].getPosition() + markets[j].getLENGTH()){
                if (!flag){
                    building = markets[j];
                    flag = true;
                }
                else if(markets[j].getHEIGHT() > building.getHEIGHT())
                    building = markets[j];
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