package Menu;

import QuanLyChamCong.Controller.ChamCongController;
import QuanLyDuAn.Controller.DuAnController;
import QuanLyLuong.Controller.LuongController;
import QuanLyNhanVien.Controller.NhanVienController;
import QuanLyPhongBan.View.PhongBanView;
import UserThings.Controller.ThongTinNhanVienController;
import UserThings.Model.NhanVien;

public class MenuController {

    private final MenuView menuView = new MenuView();
    private ChamCongController chamCongController;
    private LuongController luongController;
    private NhanVienController nhanVienController;
    private PhongBanView phongBanView;
    private ThongTinNhanVienController thongTinNhanVienController;
    private DuAnController duAnController;
    private NhanVien nhanVien;

    public MenuController() {
        initialize();
    }

    private void initialize() {
        menuView.quanlyChamCongActionListener(e -> openChamCongView());
        menuView.quanLyDuAnActionListener(e -> openDuAnView());
        menuView.quanLyLuongActionListener(e -> openLuongView());
        menuView.quanLyNhanVienActionListener(e -> openNhanVienView());
        menuView.quanLyPhongBanActionListener(e -> openPhongBanView());
        menuView.thongTinCaNhanActionListener(e -> openThongTinNhanVienView());
        menuView.dangXuatActionListener(e -> menuView.dispose());
    }

    private void openChamCongView() {
        chamCongController = new ChamCongController();

    }

    private void openDuAnView() {
        duAnController = new DuAnController();
    }

    private void openLuongView() {
        luongController = new LuongController();
    }

    private void openNhanVienView() {
        nhanVienController = new QuanLyNhanVien.Controller.NhanVienController();
    }

    private void openPhongBanView() {
        phongBanView = new PhongBanView();
    }

    private void openThongTinNhanVienView() {
        thongTinNhanVienController = new ThongTinNhanVienController(nhanVien);
    }
}

