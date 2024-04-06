package QuanLyPhongBan.Model;

public class PhongBan {
    private int idPhongBan;
    private String tenPhongBan;

    // Constructors
    public PhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public PhongBan(int idPhongBan, String tenPhongBan) {
        this.idPhongBan = idPhongBan;
        this.tenPhongBan = tenPhongBan;
    }

    // Getters and Setters
    public int getIdPhongBan() {
        return idPhongBan;
    }

    public void setIdPhongBan(int idPhongBan) {
        this.idPhongBan = idPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
