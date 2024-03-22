package QuanLyPhongBan.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAO {
    private Connection connection;

    public PhongBanDAO(Connection connection) {
        this.connection = connection;
    }

    // Phương thức để lấy danh sách tất cả các phòng ban từ cơ sở dữ liệu
    public List<PhongBanModel> getAllPhongBan() throws SQLException {
        List<PhongBanModel> phongBanList = new ArrayList<>();

        String query = "SELECT * FROM PhongBan";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tenPhongBan = resultSet.getString("tenPhongBan");
                PhongBanModel phongBan = new PhongBanModel(id, tenPhongBan);
                phongBanList.add(phongBan);
            }
        }
        return phongBanList;
    }

    // Phương thức để thêm một phòng ban mới vào cơ sở dữ liệu
    public void addPhongBan(PhongBanModel phongBan) throws SQLException {
        String query = "INSERT INTO PhongBan (id, tenPhongBan) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phongBan.getId());
            preparedStatement.setString(2, phongBan.getTenPhongBan());
            preparedStatement.executeUpdate();
        }
    }

    // Phương thức để sửa thông tin một phòng ban trong cơ sở dữ liệu
    public void updatePhongBan(PhongBanModel phongBan) throws SQLException {
        String query = "UPDATE PhongBan SET tenPhongBan = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phongBan.getTenPhongBan());
            preparedStatement.setString(2, phongBan.getId());
            preparedStatement.executeUpdate();
        }
    }

    // Phương thức để xóa một phòng ban khỏi cơ sở dữ liệu
    public void deletePhongBan(int id) throws SQLException {
        String query = "DELETE FROM PhongBan WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
