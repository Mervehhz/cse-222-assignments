import java.util.Scanner;

/**
 * Driver class
 */

public class Driver{

    static Scanner input = new Scanner(System.in);

    public static void main(String [] args){

        Street street = new Street(1000);
        House house = new House();
        House house1 = new House();
        Office office = new Office();
        Office office1 = new Office();
        Market market = new Market();
        PlayGround playGround = new PlayGround();

        boolean flag = false, flag2;

        int select0, select1;


        /*long start = System.nanoTime();

        for(int i=0;i<10;i+=5) {
            house.setLENGTH(i);
            house.setPosition(i);
            house.setHEIGHT(i+5);
            house.setColor("red");
            house.setOwner("merve");
            house.setRooms(i);
            house.setSide('L');
            street.addBuilding(house);
        }

        long end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 10 elements: "+ (double)(end-start)/1000000000 + "\n\n");


        start = System.nanoTime();

        for(int i=0;i<100;i+=5) {
            house.setLENGTH(i);
            house.setPosition(i);
            house.setHEIGHT(i+5);
            house.setColor("red");
            house.setOwner("merve");
            house.setRooms(i);
            house.setSide('R');
            street.addBuilding(house);
        }

        end = System.nanoTime();

        System.out.println("\nelapsed Time in seconds while adding 100 elements: "+ (double)(end-start)/1000000000 + "\n\n");*/

        house.setLENGTH(5);
        house.setPosition(5);
        house.setHEIGHT(5);
        house.setColor("red");
        house.setOwner("merve");
        house.setRooms(3);
        house.setSide('L');

        house1.setLENGTH(6);
        house1.setPosition(8);
        house1.setHEIGHT(8);
        house1.setColor("green");
        house1.setOwner("semih");
        house1.setRooms(3);
        house1.setSide('R');

        playGround.setLENGTH(9);
        playGround.setPosition(30);
        playGround.setSide('L');

        market.setHEIGHT(9);
        market.setLENGTH(5);
        market.setPosition(15);
        market.setOwner("beyza");
        market.setOpenTime(8);
        market.setCloseTime(22);
        market.setSide('L');

        office.setHEIGHT(15);
        office.setOwner("rana");
        office.setLENGTH(7);
        office.setPosition(21);
        office.setJobType("Software");
        office.setSide('L');

        office1.setHEIGHT(3);
        office1.setOwner("emirhan");
        office1.setLENGTH(6);
        office1.setPosition(40);
        office1.setJobType("AR-GE");
        office1.setSide('R');

        street.addBuilding(house);
        street.addBuilding(house1);
        street.addBuilding(office);
        street.addBuilding(market);
        street.addBuilding(playGround);
        street.addBuilding(office1);

        System.out.println("\n*****skyline silhouette*****\n");
        street.skylineSilhouette();

        System.out.print("\nRemaining length of land on street: ");
        System.out.println(street.displayRemainingLand());

        street.displayBuildings();

        street.displayOccupiedLengthByBuildings();

        System.out.printf("\nNumber of playgrounds in the street is %d\n", street.numberOfPlaygrounds());
        System.out.printf("Ratio of length of playgrounds in the street is %f\n\n", street.ratioOfPlaygrounds());

        house.focus();
        office.focus();
        market.focus();
        playGround.focus();

        street.deleteBuilding(8, 'R');

        street.displayBuildings();

        System.out.println("\n*****skyline silhouette after deleting building that position 15*****\n");
        street.skylineSilhouette();



        System.out.println("\n--------------Welcome to City Planning Software---------------\n");

        do {
            try {
                System.out.print("\n1) editing mode\n2) viewing mode\n0) exit\nselect one-> ");
                select0 = input.nextInt();

                switch (select0) {
                    case 1:
                        flag2 = false;
                        do {
                            select1 = editingModeMenu();
                            switch (select1) {
                                case 1: flag2 = true;
                                        buildingFeatures(street);
                                break;
                                case 2: flag2 = true;
                                        deletedPosition(street);
                                break;
                                default:
                                    System.out.println("error...");
                            }
                        }while (!flag2);
                        break;
                    case 2:
                        select1 = viewingModeMenu();

                        switch (select1) {
                            case 1:
                                System.out.printf("Remaining length of land on street: %d\n", street.displayRemainingLand());
                                break;
                            case 2:
                                street.displayBuildings();
                                break;
                            case 3:
                                System.out.printf("Number of playgrounds in the street is %d\n", street.numberOfPlaygrounds());
                                System.out.printf("Ratio of length of playgrounds in the street is %f\n", street.ratioOfPlaygrounds());
                                break;
                            case 4:
                                street.displayOccupiedLengthByBuildings();
                                break;
                            case 5:
                                street.skylineSilhouette();
                                break;
                            default:
                        }
                        break;
                    case 0:
                        flag = true;
                        break;
                    default:
                        throw new Exception();

                }
            }catch (Exception e){
                System.out.println("There is no such an option !\n");
            }
        } while (!flag);
    }

    /**
     * Menu for editing mode
     * @return select of user
     */

    public static int editingModeMenu(){

        System.out.print("\n1) add building\n2) delete building\nselect one-> ");
        return input.nextInt();
    }

    /**
     * Menu for viewing mode
     * @return select of user
     */

    public static int viewingModeMenu(){

        System.out.print("\n1) display the total remaining length of lands on the street.\n" +
                "2) display the list of buildings on the street.\n" +
                "3) display the number and ratio of lenth of playgrounds in the street.\n" +
                "4) calculate the total length of street occupied by the markets, houses or offices.\n" +
                "5) display the skyline silhouette of the street.\nselect one-> ");

        return input.nextInt();
    }

    /**
     * Takes building features from user
     * @param street street
     */

    public static void buildingFeatures(Street street){

        int select;
        boolean flag;
        do {
            flag = false;
            try {
                System.out.print("\nWhat type of building that will add?\n1) House\n2) Office\n3) Market\n4) Playground\nPlease select-> ");
                select = input.nextInt();

                switch (select) {
                    case 1:
                        flag = true;
                        House house = new House();
                        System.out.print("\nlength -> ");
                        house.setLENGTH(input.nextInt());
                        System.out.print("position -> ");
                        house.setPosition(input.nextInt());
                        System.out.print("height -> ");
                        house.setHEIGHT(input.nextInt());
                        System.out.print("color -> ");
                        house.setColor(input.next());
                        System.out.print("owner -> ");
                        house.setOwner(input.next());
                        System.out.print("rooms -> ");
                        house.setRooms(input.nextInt());
                        house.setSide(side());
                        street.addBuilding(house);
                        break;

                    case 2:
                        flag = true;
                        Office office = new Office();
                        System.out.print("\nlength -> ");
                        office.setLENGTH(input.nextInt());
                        System.out.print("position -> ");
                        office.setPosition(input.nextInt());
                        System.out.print("height -> ");
                        office.setHEIGHT(input.nextInt());
                        System.out.print("owner -> ");
                        office.setOwner(input.next());
                        System.out.print("job type -> ");
                        office.setJobType(input.next());
                        office.setSide(side());
                        street.addBuilding(office);
                        break;

                    case 3:
                        flag = true;
                        Market market = new Market();
                        System.out.print("\nlength -> ");
                        market.setLENGTH(input.nextInt());
                        System.out.print("position -> ");
                        market.setPosition(input.nextInt());
                        System.out.print("height -> ");
                        market.setHEIGHT(input.nextInt());
                        System.out.print("owner -> ");
                        market.setOwner(input.next());
                        market.setSide(side());
                        System.out.print("open time -> ");
                        market.setOpenTime(input.nextInt());
                        System.out.print("close time -> ");
                        market.setCloseTime(input.nextInt());
                        street.addBuilding(market);
                        break;

                    case 4:
                        flag = true;
                        PlayGround playGround = new PlayGround();
                        System.out.print("\nlength -> ");
                        playGround.setLENGTH(input.nextInt());
                        System.out.print("position -> ");
                        playGround.setPosition(input.nextInt());
                        playGround.setHEIGHT(0);
                        playGround.setSide(side());
                        street.addBuilding(playGround);
                        break;

                    default:
                        throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("\nerror...");
            }
        }while (!flag);
    }

    /**
     * Takes side of building from user and checks that if input is correct(L/R)
     * @return side of building
     */

    public static char side(){

        char side;

        do {
            System.out.print("side -> ");
            side = input.next().charAt(0);

            if(side != 'R' && side != 'r' && side != 'L' && side != 'l')
                System.out.println("error...\n");

        }while(side != 'R' && side != 'r' && side != 'L' && side != 'l');
        return side;
    }

    /**
     * Takes delete position and side of building that will delete
     * @param street street
     */

    public static void deletedPosition(Street street){

        int deleted;
        char side;

        System.out.print("\nPlease enter that will delete position-> ");
        deleted = input.nextInt();

        System.out.print("\nPlease enter that will delete side-> ");
        side = input.next().charAt(0);

        street.deleteBuilding(deleted, side);

        System.out.println("Building is deleted successfully..");
    }
}