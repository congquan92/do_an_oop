package dean;

public class DeAnVua extends DeAn {
    private int soPhongBan;
    public DeAnVua() {
    }

    public DeAnVua(String id, String tenDuAn, double kinhPhi, String ngayBatDau, String ngayKetThuc, int soPhongBan) {
        super(id, tenDuAn, kinhPhi, ngayBatDau, ngayKetThuc);
        this.soPhongBan = soPhongBan;
    }

    public int getSoPhongBan() {
        return soPhongBan;
    }

    public void setSoPhongBan(int soPhongBan) {
        this.soPhongBan = soPhongBan;
    }

    @Override
    public double thuongDuanHoanThanh() {
        return 2*getKinhPhi();
    }
    public void nhap(){
        super.nhap();
        System.out.println("Phòng ban:");
        setSoPhongBan(Integer.parseInt(sc.nextLine()));
    }

    public String toString() {
        return super.toString() + "\tPhòng ban: " + soPhongBan + "\tThuong tu du an :" + thuongDuanHoanThanh();
    }
    @Override
    public void xuat() {
        System.out.println(toString());
    }
}
