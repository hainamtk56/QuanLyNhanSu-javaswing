-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS quanlynhansu;

-- Sử dụng cơ sở dữ liệu
USE quanlynhansu;

-- Tạo bảng chức vụ
CREATE TABLE ChucVu (
    idChucVu INT AUTO_INCREMENT PRIMARY KEY,
    tenChucVu VARCHAR(100) NOT NULL,
    tienCongMotGio DECIMAL(10, 2) NOT NULL, -- Thay đổi từ INT sang DECIMAL
    UNIQUE (tenChucVu)
);

-- Tạo bảng phòng ban
CREATE TABLE PhongBan (
    idPhongBan INT AUTO_INCREMENT PRIMARY KEY,
    tenPhongBan VARCHAR(100) NOT NULL,
    UNIQUE (tenPhongBan)
);

-- Tạo bảng nhân viên
CREATE TABLE NhanVien (
    idNhanVien INT AUTO_INCREMENT PRIMARY KEY,
    hoTen VARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    gioiTinh ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    tinhTrang ENUM('Đang làm việc', 'Tạm nghỉ việc', 'Đã nghỉ việc') DEFAULT 'Đang làm việc',
    ngayVaoLam DATE,
    ngayNghiViec DATE DEFAULT NULL,
    diaChi VARCHAR(255),
    soDienThoai VARCHAR(20),
    email VARCHAR(100),
    idChucVu INT,
    idPhongBan INT,
    FOREIGN KEY (idChucVu) REFERENCES ChucVu(idChucVu),
    FOREIGN KEY (idPhongBan) REFERENCES PhongBan(idPhongBan)
);

-- Tạo bảng chấm công
CREATE TABLE ChamCong (
    idChamCong INT AUTO_INCREMENT PRIMARY KEY,
    idNhanVien INT,
    ngayLamViec DATE,
    gioBatDau TIME,
    gioKetThuc TIME,
    tongSoGio DECIMAL(5, 2) DEFAULT 0, -- Thêm cột tính tổng số giờ làm trong ngày
    FOREIGN KEY (idNhanVien) REFERENCES NhanVien(idNhanVien)
);

-- Tạo bảng lương thưởng
CREATE TABLE LuongThuong (
    idLuongThuong INT AUTO_INCREMENT PRIMARY KEY,
    idNhanVien INT,
    nam YEAR, -- Thay đổi kiểu dữ liệu
    thang TINYINT, -- Thay đổi kiểu dữ liệu
    thuong INT,
    thucLanh INT,
    FOREIGN KEY (idNhanVien) REFERENCES NhanVien(idNhanVien)
);

-- Tạo bảng đơn nghỉ phép
CREATE TABLE DonNghiPhep (
    idDonNghiPhep INT AUTO_INCREMENT PRIMARY KEY,
    idNhanVien INT,
    ngayNghi DATE,
    loaiNghi VARCHAR(100) NOT NULL, -- Thay đổi từ ENUM sang VARCHAR để linh động
    FOREIGN KEY (idNhanVien) REFERENCES NhanVien(idNhanVien)
);

-- Tạo bảng đào tạo
CREATE TABLE DaoTao (
    idDaoTao INT AUTO_INCREMENT PRIMARY KEY,
    idNhanVien INT,
    tenKhoaHoc VARCHAR(100) NOT NULL,
    ngayDaoTao DATE,
    ngayKetThuc DATE,
    ketQua VARCHAR(255), -- Thêm cột để ghi chú kết quả đào tạo
    FOREIGN KEY (idNhanVien) REFERENCES NhanVien(idNhanVien)
);

-- Tạo bảng tài khoản người dùng
CREATE TABLE TaiKhoanNguoiDung (
    idTaiKhoan INT AUTO_INCREMENT PRIMARY KEY,
    tenDangNhap VARCHAR(50) NOT NULL,
    matKhau VARCHAR(50) NOT NULL,
    vaiTro ENUM('Quản trị viên', 'Người dùng') NOT NULL,
    idNhanVien INT,
    UNIQUE (tenDangNhap),
    FOREIGN KEY (idNhanVien) REFERENCES NhanVien(idNhanVien)
);
