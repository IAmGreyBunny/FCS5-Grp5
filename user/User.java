package user;

/**
 * Represents a user in the system with attributes such as UId, name, age, and marital status.
 */
public class User {
    private int uid;
    private String name;            //user id
    private int age;
    private boolean maritalStatus;

    /**
     * Constructs a new user with the provided details.
     * @param uid The unique identifier for the user.
     * @param name The name of the user.
     * @param age The age of the user.
     * @param maritalStatus The marital status of the user (true for married/false for unmarried).
     */
    public User(int uid, String name, int age, boolean maritalStatus)
    {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
    }

    /**
     * Constructs a new user with default values for attributes.
     * Default values are uid = -1, name = "", age = 0, and maritalStatus = false.
     */
    //Overloaded Method to create User with default values for attributes
    public User()
    {
        this.uid = -1;
        this.name = "";
        this.age = 0;
        this.maritalStatus = false;
    }

    /**
     * Gets the unique identifier UId of the user.
     * @return the UId of the user.
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets UId of the user.
     * @param uid The UId to set for the user.
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the user.
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     * @param age The age to set for the user.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the marital status of the user.
     * @return The marital status of the user (true for married/false for unmarried).
     */
    public boolean getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the marital status of the user.
     * @param maritalStatus The marital status to set for the user (true for married/false for unmarried).
     */
    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


    // For testing purposes only
    public void printUser()
    {
        System.out.printf("%d %s %d %s\n", uid,name,age,maritalStatus);
    }
}