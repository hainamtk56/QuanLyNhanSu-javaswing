package DangNhap.Model;

public class RegisterModel {
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;

    public RegisterModel(String tenDangNhap, String matKhau, String vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    // Getter methods
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }
}
