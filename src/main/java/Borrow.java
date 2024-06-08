/**
 * borrow class
 * ELL
 *
 * @since 2024/6/2 12:00
 **/
public class Borrow {
    private String book_id;
    private String reader_id;
    private String borrow_date;
    private String return_date;

    public Borrow(String book_id, String reader_id, String borrow_date, String return_date) {
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
    }

    public Borrow() {
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }
}
