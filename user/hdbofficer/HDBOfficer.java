package user.hdbofficer;

public class HDBOfficer extends User {

    public HDBOfficer(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    public HDBOfficer(User user) {
        super(user.getUid(), user.getName(), user.getAge(), user.isMaritalStatus());
    }
}
