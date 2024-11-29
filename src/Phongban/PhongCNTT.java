package Phongban;

public class PhongCNTT extends PhongBan {
    private int soLuongDuAnHoanThanh;

    public PhongCNTT() {
        super();
    }

    public PhongCNTT(String maPhong, String tenPhong, double kinhPhiHoatDong, String ngayThanhLap, int soLuongDuAnHoanThanh) {
        super(maPhong, tenPhong, kinhPhiHoatDong, ngayThanhLap);
        this.soLuongDuAnHoanThanh = soLuongDuAnHoanThanh;
    }

    public int getSoLuongDuAnHoanThanh() {
        return soLuongDuAnHoanThanh;
    }

    public void setSoLuongDuAnHoanThanh(int soLuongDuAnHoanThanh) {
        this.soLuongDuAnHoanThanh = soLuongDuAnHoanThanh;
    }

    @Override
    public double thuongPhongban() {
        return getKinhPhiHoatDong() * 0.05 + getSoLuongDuAnHoanThanh() * 5000000;
    }

    @Override
    public void soLuong(int n) {
         setSoLuongDuAnHoanThanh(n);
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhập số lượng dự án hoàn thành: ");
        setSoLuongDuAnHoanThanh(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return String.format(
                "%s| Số lượng dự án hoàn thành : %-10d | Thưởng phòng ban : %,.2f |",
                super.toString(),
                getSoLuongDuAnHoanThanh(),
                thuongPhongban()
        );
    }


}
