package type;

import java.time.LocalDate;

public class GiamDoc extends NhanSu {
    private int soNgaynghi;
    private final String chuVu = "Giám Đốc";
    private final int soNgayduocphepnghi = 3;
    private final double luongCoban = 5000000;

    public GiamDoc() {}

    public GiamDoc(String id, String name, String phone, String diaChi, int namVaolam, int soNgaynghi) {
        super(id, name, phone, diaChi, namVaolam);
        this.soNgaynghi = soNgaynghi;
    }

    @Override
    double bonusMoneyhesothidua() {
        switch (getHeSothidua()) {
            case "A": return luongCoban() * 4;
            case "B": return luongCoban() * 3.5;
            case "C": return luongCoban() * 3;
            case "D": return luongCoban() * 2.5;
            case "E": return luongCoban() * 2;
            default: return 0;
        }
    }

    @Override
    double bonusChucvu() {
        return luongCoban * 2.25 + luongCoban * getHeSophucap();
    }

    @Override
    double luongCoban() {
        return luongCoban;
    }

    @Override
    double tienLuong() {
        double soNgayPhat = Math.max(0, getSoNgaynghi() - getSoNgayduocphepnghi());
        return (luongCoban + bonusChucvu() + bonusMoneyhesothidua() + getPhuCapthamnien()) - (soNgayPhat * 300000);
    }

    public int getKinhNghiem() {
        LocalDate year = LocalDate.now();
        return year.getYear() - super.getNamVaolam();
    }

    public double getHeSophucap() {
        if (getKinhNghiem() < 3) return 2.5;
        if (getKinhNghiem() < 5) return 3;
        if (getKinhNghiem() < 10) return 3.5;
        if (getKinhNghiem() <= 15) return 4;
        return 5.0;
    }

    public double getPhuCapthamnien() {
        return getKinhNghiem() * luongCoban / 100; // Ví dụ 1% mỗi năm
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
        int ngayNghi;
        do {
            System.out.print("Số ngày nghỉ: ");
            ngayNghi = Integer.parseInt(sc.nextLine());
            if (ngayNghi < 0) System.out.println("Số ngày nghỉ không thể âm. Vui lòng nhập lại.");
        } while (ngayNghi < 0);
        setSoNgaynghi(ngayNghi);
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
