package model;
import java.time.LocalDate;

public class PhieuMuon {
    private int idPhieuMuon;
    private int idBanDoc;
    private int idSach;
    private int soLuong;
    private LocalDate ngayMuon;

    public PhieuMuon() {
    }

    public PhieuMuon(int idPhieuMuon, int idBanDoc, int idSach, int soLuong, LocalDate ngayMuon) {
        this.idPhieuMuon = idPhieuMuon;
        this.idBanDoc = idBanDoc;
        this.idSach = idSach;
        this.soLuong = soLuong;
        this.ngayMuon = ngayMuon;
    }

    public int getIdPhieuMuon() {
        return idPhieuMuon;
    }

    public int getIdBanDoc() {
        return idBanDoc;
    }

    public int getIdSach() {
        return idSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setIdPhieuMuon(int idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }

    public void setIdBanDoc(int idBanDoc) {
        this.idBanDoc = idBanDoc;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
    
    
}
