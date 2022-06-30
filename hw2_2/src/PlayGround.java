/**
 * Playground class extends Building class
 */

public class PlayGround extends Building{

    /**
     * Constructor sets length of playground to default number
     */

    public PlayGround(){
        super.setLENGTH(25);
    }

    /**
     * overridden method
     * @return length of playground
     */

    public String toString(){
        return "Playground that have length " + this.getLENGTH();
    }

    /**
     * Playground focuses length
     */

    public void focus(){
        System.out.println(toString());
    }
}