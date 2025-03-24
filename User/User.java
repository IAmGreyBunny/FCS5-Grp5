package User;

public class User {
    private String name;
    private String nric;              //user id
    private int age;
    private boolean maritalStatus;
    private String password;

    public User(String name, String nric, int age, boolean maritalStatus,String password)
    {
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.password = password;
    }

    //Overloaded Method to create User with default values for attributes
    public User()
    {
        this.name = "";
        this.nric = "";
        this.age = 0;
        this.maritalStatus = false;
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // For testing purposes only
    public void printUser()
    {
        System.out.printf("%s %s %d %s %s\n", name,nric,age,maritalStatus,password);
    }
}
