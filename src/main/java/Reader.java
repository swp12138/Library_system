/**
 * 读者类
 * ELL
 *
 * @since 2024/6/2 11:47
 **/
public class Reader {
    private String name;
    private String id;
    private int age;
    private String phone;
    private String password;
    private final int state = 0;

    public Reader(String name, String id, int age, String phone, String password) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.phone = phone;
        this.password = password;
    }

    public Reader() {
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
