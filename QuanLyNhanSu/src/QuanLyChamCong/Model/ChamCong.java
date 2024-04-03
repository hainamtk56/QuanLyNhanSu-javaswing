package QuanLyChamCong.Model;

import java.sql.Date;
import java.sql.Time;

public class ChamCong {
    private int idChamCong;
    private int idNhanVien;
    private Date ngayLamViec;
    private Time gioBatDau;
    private Time gioKetThuc;

    public ChamCong() {
    }

    public ChamCong(int idChamCong, int idNhanVien, Date ngayLamViec, Time gioBatDau, Time gioKetThuc) {
        this.idChamCong = idChamCong;
        this.idNhanVien = idNhanVien;
        this.ngayLamViec = ngayLamViec;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public int getIdChamCong() {
        return idChamCong;
    }

    public void setIdChamCong(int idChamCong) {
        this.idChamCong = idChamCong;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "idChamCong=" + idChamCong +
                ", idNhanVien=" + idNhanVien +
                ", ngayLamViec=" + ngayLamViec +
                ", gioBatDau=" + gioBatDau +
                ", gioKetThuc=" + gioKetThuc +
                '}';
    }
}

