package Backend;

import java.time.LocalDateTime;

public abstract class Voucher {
    private int ID;
    private String code;
    private String status;
    private LocalDateTime use;
    private String email;
    private int CampID;
    private String voucherStatusType;
    private enum VoucherStatusType {
        USED,
        UNUSED
    }

    public Voucher(int ID, String code, String status, LocalDateTime use, String email, int campID) {
        this.ID = ID;
        this.code = code;
        this.status = status;
        this.use = use;
        this.email = email;
        CampID = campID;
    }

    public Voucher() {
    }

    public String getVoucherStatusType() {
        return voucherStatusType;
    }

    public void setVoucherStatusType(String voucherStatusType) {
        this.voucherStatusType = voucherStatusType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUse() {
        return use;
    }

    public void setUse(LocalDateTime use) {
        this.use = use;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCampID() {
        return CampID;
    }

    public void setCampID(int campID) {
        CampID = campID;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "ID=" + ID +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", use=" + use +
                ", email='" + email + '\'' +
                ", CampID=" + CampID +
                ", voucherStatusType='" + voucherStatusType + '\'' +
                '}';
    }
}
