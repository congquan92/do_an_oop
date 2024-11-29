package type;

import java.time.LocalDate;

public class NhanVien extends NhanSu {
    private int kinhNghiem;
    private int soNgaynghi;
    private final String chuVu = "Nhân Viên";
    private final int soNgayduocphepnghi = 2;
    private final double luongCoban = 2000000; // Lương cơ bản thấp hơn Trưởng Phòng

    public NhanVien() {}

    public NhanVien(String id, String name, String phone, String diaChi, int namVaolam, int soNgaynghi) {
        super(id, name, phone, diaChi, namVaolam);
        this.soNgaynghi = soNgaynghi;
    }

    @Override
    double bonusMoneyhesothidua() {
        switch (getHeSothidua()) {
            case "A": return luongCoban * 2; // Giảm 0.25 so với Trưởng Phòng
            case "B": return luongCoban * 1.5;
            case "C": return luongCoban * 0.5;
            case "D": return luongCoban * 0.25;
            case "E": return luongCoban * 0;
            default: return 0;
        }
    }

    @Override
    double bonusChucvu() {
        return luongCoban * 0.75 + luongCoban * getHeSophucap(); // Giảm thêm 0.25
    }

    @Override
    double luongCoban() {
        return luongCoban;
    }

    @Override
    double tienLuong() {
        double soNgayPhat = Math.max(0, getSoNgaynghi() - getSoNgayduocphepnghi());
        return (luongCoban + bonusChucvu() + bonusMoneyhesothidua() + getPhuCapthamnien()) - (soNgayPhat * 100000); // Tiền phạt giảm
    }

    public int getKinhNghiem() {
        LocalDate year = LocalDate.now();
        return year.getYear() - super.getNamVaolam();
    }

    public double getHeSophucap() {
        if (getKinhNghiem() < 3) return 2;
        if (getKinhNghiem() < 5) return 2.5;
        if (getKinhNghiem() < 10) return 2;
        if (getKinhNghiem() <= 15) return 3.25;
        return 3.5;
    }

    public double getPhuCapthamnien() {
        return getKinhNghiem() * luongCoban / 300; // Tỷ lệ giảm
    }

    public int getSoNgaynghi() {
        return soNgaynghi;
    }

    public void setSoNgaynghi(int soNgaynghi) {
        this.soNgaynghi = soNgaynghi;
    }

    public String getChuVu() {
        return chuVu;
    }

    public int getSoNgayduocphepnghi() {
        return soNgayduocphepnghi;
    }

    public void nhap() {
        super.nhap();
        System.out.print("Số ngày nghỉ: ");
        setSoNgaynghi(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return String.format(
                "%s | Kinh nghiệm: %-5d | Hệ số phụ cấp: %-5.2f | Chức vụ: %-10s | Số ngày nghỉ: %-3d | Tổng tiền lương: %,.2f",
                super.toString(),
                getKinhNghiem(),
                getHeSophucap(),
                getChuVu(),
                getSoNgaynghi(),
                tienLuong()
        );
    }

    public void xuat() {
        System.out.println(toString());
    }
}
