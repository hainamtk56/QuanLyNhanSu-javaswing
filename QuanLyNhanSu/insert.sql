-- Thêm dữ liệu vào bảng ChucVu
INSERT INTO ChucVu (tenChucVu, tienCongMotGio) VALUES
('Giám đốc', 80000),
('Phó Giám đốc', 70000),
('Trưởng phòng', 60000),
('Phó phòng', 50000),
('Nhân viên', 40000),
('Thực tập sinh', 30000);

-- Thêm dữ liệu vào bảng PhongBan
INSERT INTO PhongBan (tenPhongBan) VALUES
('Phòng Kinh doanh'),
('Phòng Marketing'),
('Phòng Nhân sự'),
('Phòng Kỹ thuật'),
('Phòng Sản xuất'),
('Phòng Hỗ trợ');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (hoTen, ngaySinh, gioiTinh, tinhTrang, ngayVaoLam, diaChi, soDienThoai, email, idChucVu, idPhongBan) VALUES
('Trần Văn A', '1995-01-12', 'Nam', 'Đang làm việc', '2022-01-01', 'Hà Nội', '0123456789', 'tranvana@example.com', 1, 1),
('Nguyễn Thị B', '1993-06-24', 'Nữ', 'Đang làm việc', '2022-02-12', 'TP HCM', '0987654321', 'nguyenthib@example.com', 2, 2),
('Lê C', '1987-11-06', 'Khác', 'Đang làm việc', '2022-03-15', 'Đà Nẵng', '0123456789', 'lec@example.com', 3, 3),
('Hoàng Thị D', '1990-04-18', 'Nữ', 'Đang làm việc', '2022-04-01', 'Cần Thơ', '0987654321', 'hoangthid@example.com', 4, 4),
('Phạm E', '1985-02-22', 'Nam', 'Đang làm việc', '2022-05-21', 'Quảng Ninh', '0123456789', 'phame@example.com', 5, 5),
('Vũ Thị F', '1992-07-30', 'Nữ', 'Đang làm việc', '2022-06-02', 'Hải Phòng', '0987654321', 'vuthif@example.com', 6, 6);

-- Thêm dữ liệu vào bảng ChamCong
-- Giả sử mỗi nhân viên đều làm từ 9:00 đến 17:00 trong ngày hôm nay
INSERT INTO ChamCong (idNhanVien, ngayLamViec, gioBatDau, gioKetThuc, tongSoGio) VALUES
(1, CURDATE(), '09:00:00', '17:00:00', 8),
(2, CURDATE(), '09:00:00', '17:00:00', 8),
(3, CURDATE(), '09:00:00', '17:00:00', 8),
(4, CURDATE(), '09:00:00', '17:00:00', 8),
(5, CURDATE(), '09:00:00', '17:00:00', 8),
(6, CURDATE(), '09:00:00', '17:00:00', 8);

-- Thêm dữ liệu vào bảng LuongThuong
-- Giả sử mức lương là cố định và không có thưởng
INSERT INTO LuongThuong (idNhanVien, nam, thang, thuong, thucLanh) VALUES
(1, YEAR(CURDATE()), MONTH(CURDATE()), 0, 10000000),
(2, YEAR(CURDATE()), MONTH(CURDATE()), 0, 8000000),
(3, YEAR(CURDATE()), MONTH(CURDATE()), 0, 12000000),
(4, YEAR(CURDATE()), MONTH(CURDATE()), 0, 7000000),
(5, YEAR(CURDATE()), MONTH(CURDATE()), 0, 9000000),
(6, YEAR(CURDATE()), MONTH(CURDATE()), 0, 8500000);

-- Thêm dữ liệu vào bảng DonNghiPhep
-- Giả sử một số nhân viên đã nghỉ phép trong tháng
INSERT INTO DonNghiPhep (idNhanVien, ngayNghi, loaiNghi) VALUES
(1, CURDATE() - INTERVAL 1 DAY, 'Nghỉ phép năm'),
(2, CURDATE() - INTERVAL 3 DAY, 'Nghỉ không lương'),
(3, CURDATE() - INTERVAL 5 DAY, 'Nghỉ phép năm'),
(4, CURDATE() - INTERVAL 7 DAY, 'Nghỉ ốm'),
(5, CURDATE() - INTERVAL 9 DAY, 'Nghỉ thai sản'),
(6, CURDATE() - INTERVAL 11 DAY, 'Nghỉ việc riêng');

-- Thêm dữ liệu vào bảng DaoTao
-- Giả sử một số nhân viên đã tham gia các khóa đào tạo
INSERT INTO DaoTao (idNhanVien, tenKhoaHoc, ngayDaoTao, ngayKetThuc) VALUES
(1, 'Quản lý dự án', '2022-01-15', '2022-02-15'),
(3, 'Kỹ năng lãnh đạo', '2022-03-01', '2022-04-01'),
(5, 'Kỹ năng giao tiếp', '2022-05-05', '2022-06-05');

-- Thêm dữ liệu vào bảng TaiKhoanNguoiDung
INSERT INTO TaiKhoanNguoiDung (tenDangNhap, matKhau, vaiTro, idNhanVien) VALUES
('user1', 'password1', 'Quản trị viên', 1),
('user2', 'password2', 'Người dùng', 2),
('user3', 'password3', 'Người dùng', 3),
('user4', 'password4', 'Người dùng', 4),
('user5', 'password5', 'Người dùng', 5),
('user6', 'password6', 'Quản trị viên', 6);