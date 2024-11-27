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
        return getKinhPhiHoatDong() * 0.07 + soLuongChienDichThanhCong * 3000000;
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhập số lượng chiến dịch thành công: ");
        setSoLuongChienDichThanhCong(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return super.toString() +
                "Số lượng chiến dịch thành công: " + soLuongChienDichThanhCong + "\n" +
                "Thưởng phòng ban: " + thuongPhongban() + "\n";
    }
}
