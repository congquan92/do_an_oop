package dean;

import java.util.Arrays;
import java.util.Scanner;

public abstract class DeAn {
    private String id;
    private String tenDuAn;
    private double kinhPhi;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String[] arrIdNguoiPhuTrach;
    private String[] arrTenNguoiPhuTrach;
    public static Scanner sc = new Scanner(System.in);

    public DeAn() {
        arrIdNguoiPhuTrach = new String[0];
        arrTenNguoiPhuTrach = new String[0];
    }

    public DeAn(String id, String tenDuAn, double kinhPhi, String ngayBatDau, String ngayKetThuc) {
        this.id = id;
        this.tenDuAn = tenDuAn;
        this.kinhPhi = kinhPhi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        arrIdNguoiPhuTrach = new String[0];
        arrTenNguoiPhuTrach = new String[0];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public double getKinhPhi() {
        return kinhPhi;
    }

    public void setKinhPhi(double kinhPhi) {
        this.kinhPhi = kinhPhi;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void themNguoiPhuTrach(String idNguoi, String tenNguoi) {
        arrIdNguoiPhuTrach = Arrays.copyOf(arrIdNguoiPhuTrach, arrIdNguoiPhuTrach.length + 1);
        arrIdNguoiPhuTrach[arrIdNguoiPhuTrach.length - 1] = idNguoi;

        arrTenNguoiPhuTrach = Arrays.copyOf(arrTenNguoiPhuTrach, arrTenNguoiPhuTrach.length + 1);
        arrTenNguoiPhuTrach[arrTenNguoiPhuTrach.length - 1] = tenNguoi;
    }

    public void nhapNguoiPhuTrach() {
        System.out.println("Nhập số lượng người phụ trách: ");
        int n = Integer.parseInt(sc.nextLine());
        // Làm rỗng danh sách hiện tại để nhập mới
        arrIdNguoiPhuTrach = new String[0];
        arrTenNguoiPhuTrach = new String[0];

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập ID người phụ trách thứ " + (i + 1) + ": ");
            String idNguoiPhuTrach = sc.nextLine();
            System.out.println("Nhập tên người phụ trách thứ " + (i + 1) + ": ");
            String tenNguoiPhuTrach = sc.nextLine();

            themNguoiPhuTrach(idNguoiPhuTrach, tenNguoiPhuTrach);
        }
    }

    public void nhap() {
        System.out.print("Nhập ID dự án: ");
        setId(sc.nextLine());
        System.out.print("Nhập tên dự án: ");
        setTenDuAn(sc.nextLine());
        System.out.print("Nhập kinh phí: ");
        setKinhPhi(Double.parseDouble(sc.nextLine()));
        System.out.print("Nhập ngày bắt đầu: ");
        setNgayBatDau(sc.nextLine());
        System.out.print("Nhập ngày kết thúc: ");
        setNgayKetThuc(sc.nextLine());
        nhapNguoiPhuTrach();
    }

    public abstract double thuongDuanHoanThanh();

    @Override
    public String toString() {
        StringBuilder nguoiPhuTrachInfo = new StringBuilder();
        for (int i = 0; i < arrIdNguoiPhuTrach.length; i++) {
            nguoiPhuTrachInfo.append(String.format("| ID : %-10s | Tên : %-20s |\n", arrIdNguoiPhuTrach[i], arrTenNguoiPhuTrach[i]));
        }
        return String.format(
                "| Dự án: %-30s | ID: %-10s | Kinh phí: %,-10.2f | Thời gian: %-15s đến %-15s \n| Người phụ trách:\n%s",
                tenDuAn, id, kinhPhi, ngayBatDau, ngayKetThuc, nguoiPhuTrachInfo.toString());
    }

    public void xuat() {
        System.out.println(toString());
    }
}
