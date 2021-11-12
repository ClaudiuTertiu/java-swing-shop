package Backend;

import java.time.LocalDateTime;

public class LoyalityVoucher extends Voucher {
    private float downsize;

    public LoyalityVoucher(int ID, String code, String status, LocalDateTime use, String email, int campID, float downsize) {
        super(ID, code, status, use, email, campID);
        this.downsize = downsize;
    }

    public float getDownsize() {
        return downsize;
    }

    public void setDownsize(float downsize) {
        this.downsize = downsize;
    }

    @Override
    public String toString() {
        return "LoyalityVoucher{" +
                "downsize=" + downsize +
                '}';
    }
}
