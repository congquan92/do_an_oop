package dean;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DeAnVua extends DeAn {
    private String khoangcach;

    public DeAnVua() {
    }

    public DeAnVua(String id, String tenDuAn, double kinhPhi, String ngayBatDau, String ngayKetThuc, String khoangcach) {
        super(id, tenDuAn, kinhPhi, ngayBatDau, ngayKetThuc);
        this.khoangcach = khoangcach;
    }

    public String getKhoangcach() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(super.getNgayBatDau().trim(), formatter);
        LocalDate endDate = LocalDate.parse(super.getNgayKetThuc().trim(), formatter);
        Period period = Period.between(startDate, endDate);
        return (period.getYears() + " năm, " + period.getMonths() + " tháng, " + period.getDays() + " ngày");
    }

    public void setKhoangcach(String khoangcach) {
        this.khoangcach = khoangcach;
    }

    @Override
    public double thuongDuanHoanThanh() {
        return 1.5 * getKinhPhi();
    }

    public void nhap() {
        super.nhap();
    }

    @Override
    public String toString() {
        return String.format(
                "%s| Thời gian: %-20s | Thưởng từ dự án: %,.2f\n",
                super.toString(),
                getKhoangcach(),
                thuongDuanHoanThanh()
        );
    }

    @Override
    public void xuat() {
        System.out.println(toString());
    }
}
