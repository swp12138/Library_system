/**
 * 预约
 * ELL
 *
 * @since 2024/6/2 11:58
 **/
public class Reserve {
    private String book_id;
    private String user_id;
    private String reserve_time;
    private String take_time;

    public Reserve(String book_id, String user_id, String reserve_time, String take_time) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.reserve_time = reserve_time;
        this.take_time = take_time;
    }

    public Reserve() {
    }

    public String getBook_id() {
        return book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getReserve_time() {
        return reserve_time;
    }

    public String getTake_time() {
        return take_time;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
    }

    public void setReserve_time(String reserve_time) {
        this.reserve_time = reserve_time;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
