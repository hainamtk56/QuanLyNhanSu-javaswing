package QuanLyDuAn.Model;

import java.time.LocalDate;

public class DuAn {
    private int idNhanVien;
    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh; // Sử dụng String thay vì Enum để đơn giản hóa
    private String tinhTrang; // Sử dụng String thay vì Enum để đơn giản hóa
    private String Duan;
    private String soDienThoai;
    private String email;
    private int idChucVu;
    private int idPhongBan;

    // Constructors
    public DuAn() {}

    public DuAn(int idNhanVien, String hoTen, LocalDate ngaySinh, String gioiTinh, String tinhTrang,String Duan , String soDienThoai, String email, int idChucVu, int idPhongBan) {
        this.idNhanVien = idNhanVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.tinhTrang = tinhTrang;
        this.Duan = Duan;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.idChucVu = idChucVu;
        this.idPhongBan = idPhongBan;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    public String getDuan() {
        return Duan;
    }

    public void setDuan(String Duan) {
        this.Duan = Duan;
    }


    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(int idChucVu) {
        this.idChucVu = idChucVu;
    }

    public int getIdPhongBan() {
        return idPhongBan;
    }

    public void setIdPhongBan(int idPhongBan) {
        this.idPhongBan = idPhongBan;
    }
}

