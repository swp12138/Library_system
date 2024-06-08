/**
 * borrow
 * ELL
 *
 * @since 2024/6/3 19:24
 **/
public class BorrowDB {
    private String name;
    private String id;
    private String borrowDate;
    private String returnDate;

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowDB(String name, String id, String borrowDate) {
        this.name = name;
        this.id = id;
        this.borrowDate = borrowDate;
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

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}
