/**
 * 书本类
 * ELL
 *
 * @since 2024/6/2 11:40
 **/
public class Book {
    private String name;
    private String author;
    private String id;
    private float price;
    private int bStatus;
    private int borrowTimes;
    private int reserveTimes;

    public Book() {

    }

    public Book(String name, String author, String id, float price, int bStatus, int borrowTimes, int reserveTimes) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.price = price;
        this.bStatus = bStatus;
        this.borrowTimes = borrowTimes;
        this.reserveTimes = reserveTimes;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReserveTimes() {
        return reserveTimes;
    }

    public void setReserveTimes(int reserveTimes) {
        this.reserveTimes = reserveTimes;
    }

    public int getBorrowTimes() {
        return borrowTimes;
    }

    public void setBorrowTimes(int borrowTimes) {
        this.borrowTimes = borrowTimes;
    }

    public int getBStatus() {
        return bStatus;
    }

    public void setBStatus(int bStatus) {
        this.bStatus = bStatus;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
