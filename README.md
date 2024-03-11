# Hướng dẫn mở kết nối đến MySql(Xampp) sử dụng IntelliJ IDEA Community Edition:
File -> Project Structure -> Modules -> Tab Dependencies -> "+" -> Chọn mysql-connector-j-8.3.0.jar -> Apply rồi OK
![image](https://github.com/NguyenLinh203/QuanLyNhanSu/assets/138262382/150fdbd9-c0f9-4cab-9673-5e5deac5c81a)
(Cac file module + database đều nằm trong folder Project)

Chạy thử DatabaseConnectionTest để kiểm tra kết nối

*Nhớ import package ConnectionManager.ConnectionManager khi muốn mở connection đến database*

# Cấu trúc dự án:
Các view đều có folder riêng, ai làm phần nào thì làm trong folder đấy

Các Controller, View, Model bỏ riêng vào thư mục tương ứng như ví dụ có sẵn trong mỗi folder

Muốn chạy view nào thì về main tạo model, view, controller tương ứng

# Phân việc 
Tùng: - View đăng nhập đăng kí quên mật khẩu

View Admin:

Nam: - View Quản lý nhân viên: Hiển thị, thêm sửa xóa nhân viên

		+ View Quản lý lương nhân viên: Hiển thị lương, các khoản thưởng, phạt, thêm sửa xóa thưởng phạt

		+ View Quản lý ngày công: Hiển thị ngày công của nhân viên đó

Tùng: - View Quản lý phòng ban: Hiển thị phòng ban, thêm sửa xóa phòng ban

		+ View chi tiết phòng ban: Hiển thị người trong phòng ban đó, thêm xóa người khỏi phòng ban

Bình: - View Quản lý dự án: Hiển thị dự án, thêm sửa xóa dự án

		+ View chi tiết dự án: Hiển thị ai đang tham gia dự án đó, thêm sửa xóa nhiệm vụ cho người đó

View User:

Linh: View Thông tin cá nhân: Hiển thị thông tin cá nhân, lương, thưởng phạt, chỉnh sửa một số thông tin cá nhân

		+ View Đổi mật khẩu: đổi mật khẩu tài khoản cá nhân

		+ View Điểm danh: Xem số ngày công, điểm danh cho ngày hôm đó

		+ View Dự án đang tham gia: Hiển thị các nhiệm vụ cá nhân trong dự án đang tham gia, xác nhận hoàn thành nhiệm vụ
  
# Lưu ý:
Vẫn đang thiếu bảng dự án
