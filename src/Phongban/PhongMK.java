package Phongban;

public class PhongMK extends PhongBan {
    private int soLuongChienDichThanhCong;

    public PhongMK() {
        super();
    }

    public PhongMK(String maPhong, String tenPhong, double kinhPhiHoatDong, String ngayThanhLap, int soLuongChienDichThanhCong) {
        super(maPhong, tenPhong, kinhPhiHoatDong, ngayThanhLap);
        this.soLuongChienDichThanhCong = soLuongChienDichThanhCong;
    }

    public int getSoLuongChienDichThanhCong() {
        return soLuongChienDichThanhCong;
    }

    public void setSoLuongChienDichThanhCong(int soLuongChienDichThanhCong) {
        this.soLuongChienDichThanhCong = soLuongChienDichThanhCong;
    }

    @Override
    public double thuongPhongban() {
        return getKinhPhiHoatDong() * 0.07 + getSoLuongChienDichThanhCong() * 3000000;
    }

    @Override
    public void soLuong(int n) {
        setSoLuongChienDichThanhCong(n);
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhập số lượng chiến dịch thành công: ");
        setSoLuongChienDichThanhCong(Integer.parseInt(sc.nextLine()));
    }
    @Override
    public String toString() {
        return String.format(
                "%s | Số lượng chiến dịch thành công: %-10d | Thưởng phòng ban: %,.2f |",
                super.toString(),
                getSoLuongChienDichThanhCong(),
                thuongPhongban()
        );
    }
}
