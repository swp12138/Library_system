/**
 * 管理员类
 * ELL
 *
 * @since 2024/6/2 11:52
 **/
public class Admins {
    private String name;
    private String password;
    private String id;
    private int state;
    public AdminsDB adminsDB = new AdminsDB();

    public Admins(String name, String password, String id, int state) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.state = state;
    }

    public Admins() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

