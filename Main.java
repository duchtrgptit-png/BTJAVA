import java.util.Locale;

class SanPham {
    private String maSP;
    private String tenSP;
    private double donGia;
    private int soLuongTon;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham(String maSP, String tenSP, double donGia, int soLuongTon) {
        this(maSP, tenSP);
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
    }

    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }

    // Định dạng tiền
    public static String dinhDangTien(double tien) {
        return String.format(Locale.GERMANY, "%,.0f", tien);
    }

    public void hienThi() {
        System.out.printf("%-6s%-18s%9s  tồn %d%n",
                maSP, tenSP, dinhDangTien(donGia), soLuongTon);
    }
}

class DonHang {
    private String tenKhach;
    private SanPham sanPham;
    private int soLuong;

    public DonHang(String tenKhach, SanPham sanPham, int soLuong) {
        this.tenKhach = tenKhach;
        this.sanPham = sanPham;
        this.soLuong = (soLuong < 1) ? 1 : soLuong; // nhỏ hơn 1 thì gán 1
    }

    // Thành tiền = donGia x soLuong, từ 5 cái trở lên giảm 10%
    public double tinhThanhTien() {
        double tong = sanPham.getDonGia() * soLuong;
        if (soLuong >= 5) {
            tong = tong * 0.9;
        }
        return tong;
    }

    public void inDonHang() {
        System.out.printf("%-8s%-18s%3d%12s%n",
                tenKhach, sanPham.getTenSP(), soLuong,
                SanPham.dinhDangTien(tinhThanhTien()));
        if (soLuong >= 5) {
            System.out.println("        (đã giảm 10%)");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SanPham banPhim = new SanPham("SP01", "Bàn phím cơ", 450000, 20);
        SanPham chuot = new SanPham("SP02", "Chuột không dây", 200000, 50);

        SanPham taiNghe = new SanPham("SP03", "Tai nghe");
        taiNghe.setDonGia(350000);
        taiNghe.setSoLuongTon(10);

        DonHang don1 = new DonHang("An", banPhim, 2);
        DonHang don2 = new DonHang("Bình", chuot, 5);
        DonHang don3 = new DonHang("Chi", taiNghe, 0);

        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        banPhim.hienThi();
        chuot.hienThi();
        taiNghe.hienThi();

        System.out.println();
        System.out.println("===== DANH SÁCH ĐƠN HÀNG =====");
        System.out.printf("%-8s%-18s%3s%12s%n", "Khách", "Sản phẩm", "SL", "Thành tiền");
        don1.inDonHang();
        don2.inDonHang();
        don3.inDonHang();
    }
}