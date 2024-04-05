package DangNhap.Model;

public class RegisterModel {
    private String tenDangNhap;
    private String matKhau;

    public RegisterModel(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    // Getter methods
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }
}
