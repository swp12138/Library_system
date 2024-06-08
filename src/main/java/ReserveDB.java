/**
 * reserve
 * ELL
 *
 * @since 2024/6/3 19:25
 **/
public class ReserveDB {
    private String name;
    private String bid;
    private String reserveDate;

    public ReserveDB(String name, String bid, String reserveDate) {
        this.name = name;
        this.bid = bid;
        this.reserveDate = reserveDate;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }
}
