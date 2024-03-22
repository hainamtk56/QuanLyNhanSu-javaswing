// PhongBanModel.java
package QuanLyPhongBan.Model;

import java.util.ArrayList;
import java.util.List;

public class PhongBanModel {
    private List<Object[]> data;
    private final String[] columnNames = {"ID", "Tên Phòng Ban"};

    public PhongBanModel() {
        data = new ArrayList<>();
    }

    // Thêm một hàng dữ liệu mới vào model
    public void addRow(String id, String tenPhongBan) {
        Object[] rowData = {id, tenPhongBan};
        data.add(rowData);
    }

    // Xóa một hàng dữ liệu khỏi model
    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
        }
    }

    // Lấy tổng số hàng dữ liệu trong model
    public int getRowCount() {
        return data.size();
    }

    // Lấy số cột dữ liệu trong model
    public int getColumnCount() {
        return columnNames.length;
    }

    // Lấy tên cột tại chỉ số columnIndex
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // Lấy giá trị tại chỉ số rowIndex và columnIndex
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount() && columnIndex >= 0 && columnIndex < getColumnCount()) {
            return data.get(rowIndex)[columnIndex];
        }
        return null;
    }

    // Thiết lập giá trị tại chỉ số rowIndex và columnIndex
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount() && columnIndex >= 0 && columnIndex < getColumnCount()) {
            data.get(rowIndex)[columnIndex] = value;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
