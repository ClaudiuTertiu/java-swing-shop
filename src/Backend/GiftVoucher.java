package Backend;

import java.time.LocalDateTime;

public class GiftVoucher extends Voucher {
    private float downsize;

    public GiftVoucher(int ID, String code, String status, LocalDateTime use, String email, int campID, float value) {
        super(ID, code, status, use, email, campID);
        this.downsize = value;
    }

    public GiftVoucher() {
    }

    public float getDownsize() {
        return downsize;
    }

    public void setDownsize(float downsize) {
        this.downsize = downsize;
    }

    @Override
    public String toString() {
        return "GiftVoucher{" +
                "downsize=" + downsize +
                '}';
    }
}
