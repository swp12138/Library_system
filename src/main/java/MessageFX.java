/**
 * ELL
 *
 * @since 2024/6/5 16:50
 **/
public class MessageFX {
    private String name;
    private String id;
    private String borrowTime;
    private String returnTime;

    public MessageFX(String name, String id, String borrowTime, String returnTime) {
        this.name = name;
        this.id = id;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
