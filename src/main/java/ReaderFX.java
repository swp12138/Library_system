/**
 * readerfx
 * ELL
 *
 * @since 2024/6/2 15:02
 **/
public class ReaderFX {
    private String name;
    private String id;
    private int age;
    private String phone;
    private final int state = 0;

    public ReaderFX(String name, String id, int age, String phone) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.phone = phone;
    }

    public ReaderFX() {
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
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
