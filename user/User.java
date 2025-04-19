package user;

public class User {
    private int uid;
    private String name;            //user id
    private int age;
    private boolean maritalStatus;

    public User(int uid, String name, int age, boolean maritalStatus)
    {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.maritalStatus = maritalStatus;
    }

    //Overloaded Method to create User with default values for attributes
    public User()
    {
        this.uid = -1;
        this.name = "";
        this.age = 0;
        this.maritalStatus = false;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // For testing purposes only
    public void printUser()
    {
        System.out.printf("%d %s %d %s\n", uid,name,age,maritalStatus);
    }
}