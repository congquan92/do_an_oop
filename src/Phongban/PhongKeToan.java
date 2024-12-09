package Phongban;

public class PhongKeToan extends PhongBan {
    private int soLuongBaoCaoHoanThanh;

    public PhongKeToan() {
        super();
    }

    public PhongKeToan(String maPhong, String tenPhong, double kinhPhiHoatDong, String ngayThanhLap, int soLuongBaoCaoHoanThanh) {
        super(maPhong, tenPhong, kinhPhiHoatDong, ngayThanhLap);
        this.soLuongBaoCaoHoanThanh = soLuongBaoCaoHoanThanh;
    }

    public int getSoLuongBaoCaoHoanThanh() {
        return soLuongBaoCaoHoanThanh;
    }

    public void setSoLuongBaoCaoHoanThanh(int soLuongBaoCaoHoanThanh) {
        this.soLuongBaoCaoHoanThanh = soLuongBaoCaoHoanThanh;
    }

    @Override
    public double thuongPhongban() {
        return getKinhPhiHoatDong() * 0.03 + getSoLuongBaoCaoHoanThanh() * 2000000;
    }

    @Override
    public void soLuong(int n) {
        setSoLuongBaoCaoHoanThanh(n);
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhập số lượng báo cáo hoàn thành: ");
        setSoLuongBaoCaoHoanThanh(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return String.format(
                "%s| Số lượng báo cáo hoàn thành: %-10d | Thưởng phòng ban: %,.2f \n",
                super.toString(),
                getSoLuongBaoCaoHoanThanh(),
                thuongPhongban()
        );
    }

}
