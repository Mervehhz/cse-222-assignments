/**
 * Office class extends Building class
 */

public class Office extends Building{

    /** job type of the office */
    private String jobType;
    /** owner of the office */
    private String owner;

    /**
     * @return returns job type of office
     */

    public String getJobType(){
        return jobType;
    }

    /**
     * Sets job type of office
     * @param jobType job type of office
     */

    public void setJobType(String jobType){
        this.jobType = jobType;
    }

    /**
     * @return owner of office
     */

    public String getOwner() { return owner; }

    /**
     * Sets owner of office
     * @param owner owner of office
     */

    public void setOwner(String owner) { this.owner = owner; }

    /**
     * overridden method
     * @return job type of office
     */

    public String toString(){
        return this.jobType + "'s office";
    }

    /**
     * Office focuses job type
     */

    public void focus(){
        System.out.println(toString());
    }
}