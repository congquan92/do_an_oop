package type;

import java.time.LocalDate;


public class GiamDoc extends NhanSu {
    private int kinhNghiem;
    private double heSophucap;
    private double phuCapthamnien;
    private int soNgaynghi;
    private final String chuVu = "Giám Đốc";
    private final int soNgayduocphepnghi = 3;


    public GiamDoc() {
    }

    public GiamDoc(String id, String name, String phone, String diaChi, int namVaolam, int kinhNghiem, double phuCapthamnien, int soNgaynghi) {
        super(id, name, phone, diaChi, namVaolam);
        this.kinhNghiem = kinhNghiem;
        this.phuCapthamnien = phuCapthamnien;
        this.soNgaynghi = soNgaynghi;
    }
    @Override
    double bonusMoneyhesothidua() {
        switch (getHeSothidua()) {
            case "A": return luongCoban() * 4;
            case "B": return luongCoban() * 3.5;
            case "C": return luongCoban() * 3;
            case "D": return luongCoban() * 2.5;
            case "E": return luongCoban() *2;
            default: return 0;
        }
    }

    @Override
    double bonusChucvu() {
        return luongCoban() * 2.25 + luongCoban() * getHeSophucap();
    }

    @Override
    double luongCoban() {
        return 5000000;
    }

    @Override
    double tienLuong() {
        double soNgayPhat = Math.max(0, getSoNgaynghi() - getSoNgayduocphepnghi());
        return (luongCoban() + bonusChucvu() + bonusMoneyhesothidua() + getPhuCapthamnien()) - (soNgayPhat * 300000);
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
        return getKinhNghiem() * luongCoban() / 100; // Ví dụ 1% mỗi năm
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
        super.nhap(); // Gọi phương thức nhập của lớp cha
        System.out.print("Số ngày nghỉ: ");
        setSoNgaynghi(Integer.parseInt(sc.nextLine()));
    }

    @Override
    public String toString() {
        return super.toString() +
                "\t\tKinh nghiệm: " + getKinhNghiem() +
                "\t\tHệ số phụ cấp: " + getHeSophucap() +
                "\t\tChức vụ: " + getChuVu() +
                "\t\tSố ngày nghỉ: " + getSoNgaynghi() +
                "\t\tTổng tiền lương: " + tienLuong();
    }


    public void xuat() {
        System.out.println(toString());
    }
}
