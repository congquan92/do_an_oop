package type;

import java.time.LocalDate;

public class TruongPhong extends NhanSu {
    private int kinhNghiem;
    private int soNgaynghi;
    private final String chuVu = "Trưởng Phòng";
    private final int soNgayduocphepnghi = 3;
    private final double luongCoban = 2500000;

    public TruongPhong() {}

    public TruongPhong(String id, String name, String phone, String diaChi, int namVaolam, int soNgaynghi) {
        super(id, name, phone, diaChi, namVaolam);
        this.soNgaynghi = soNgaynghi;
    }

    @Override
    double bonusMoneyhesothidua() {
        switch (getHeSothidua()) {
            case "A": return luongCoban() * 3;
            case "B": return luongCoban() * 2.5;
            case "C": return luongCoban() * 2;
            case "D": return luongCoban() * 1;
            case "E": return luongCoban() * 0.5;
            default: return 0;
        }
    }

    @Override
    double bonusChucvu() {
        return luongCoban * 1.125 + luongCoban * getHeSophucap(); // Giảm một nửa
    }

    @Override
    double luongCoban() {
        return luongCoban;
    }

    @Override
    double tienLuong() {
        double soNgayPhat = Math.max(0, getSoNgaynghi() - getSoNgayduocphepnghi());
        return (luongCoban + bonusChucvu() + bonusMoneyhesothidua() + getPhuCapthamnien()) - (soNgayPhat * 150000); // Giảm tiền phạt
    }

    public int getKinhNghiem() {
        LocalDate year = LocalDate.now();
        return year.getYear() - super.getNamVaolam();
    }

    public double getHeSophucap() {
        if (getKinhNghiem() < 3) return 2;
        if (getKinhNghiem() < 5) return 2.5;
        if (getKinhNghiem() < 10) return 3;
        if (getKinhNghiem() <= 15) return 3.5;
        return 4;
    }

    public double getPhuCapthamnien() {
        return getKinhNghiem() * luongCoban / 200;
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
