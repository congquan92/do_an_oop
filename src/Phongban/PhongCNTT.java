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
        return getKinhPhiHoatDong() * 0.05 + soLuongDuAnHoanThanh * 5000000;
    }

    public void nhap() {
        super.nhap();
        System.out.print("Nhập số lượng dự án hoàn thành: ");
        setSoLuongDuAnHoanThanh(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return super.toString() +
                "Số lượng dự án hoàn thành: " + soLuongDuAnHoanThanh + "\n" +
                "Thưởng phòng ban: " + thuongPhongban() + "\n";
    }
}
