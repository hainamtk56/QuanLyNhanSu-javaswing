package QuanLyLuong.Model;

public class Luong {
    private int idLuong, idNhanVien, nam, thang, soGioLam, thuong, thucLanh;
    public Luong() {
    }
    public Luong(int idLuong, int idNhanVien, int nam, int thang, int soGioLam, int thuong, int thucLanh) {
        this.idLuong = idLuong;
        this.idNhanVien = idNhanVien;
        this.nam = nam;
        this.thang = thang;
        this.soGioLam = soGioLam;
        this.thuong = thuong;
        this.thucLanh = thucLanh;
    }

    public int getIdLuong() {
        return idLuong;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public int getNam() {
        return nam;
    }

    public int getThang() {
        return thang;
    }
    public int getSoGioLam() {
        return soGioLam;
    }

    public int getThuong() {
        return thuong;
    }

    public int getThucLanh() {
        return thucLanh;
    }

    public void setIdLuong(int idLuong) {
        this.idLuong = idLuong;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }
    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }

    public void setThuong(int thuong) {
        this.thuong = thuong;
    }

    public void setThucLanh(int thucLanh) {
        this.thucLanh = thucLanh;
    }
}

