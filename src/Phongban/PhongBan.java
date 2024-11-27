package Phongban;

import java.util.Arrays;
import java.util.Scanner;

public abstract class PhongBan {
    private String maPhong;
    private String tenPhong;
    private double kinhPhiHoatDong;
    private String ngayThanhLap;

    private String[] arrIdNhanVien;
    private String[] arrTenNhanVien;

    public static Scanner sc = new Scanner(System.in);

    public abstract double thuongPhongban();

    public PhongBan() {
        arrIdNhanVien = new String[0];
        arrTenNhanVien = new String[0];
    }

    public PhongBan(String maPhong, String tenPhong, double kinhPhiHoatDong, String ngayThanhLap) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.kinhPhiHoatDong = kinhPhiHoatDong;
        this.ngayThanhLap = ngayThanhLap;
        arrIdNhanVien = new String[0];
        arrTenNhanVien = new String[0];
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public double getKinhPhiHoatDong() {
        return kinhPhiHoatDong;
    }

    public void setKinhPhiHoatDong(double kinhPhiHoatDong) {
        this.kinhPhiHoatDong = kinhPhiHoatDong;
    }

    public String getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(String ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public void themNhanVien(String idNhanVien, String tenNhanVien) {
        arrIdNhanVien = Arrays.copyOf(arrIdNhanVien, arrIdNhanVien.length + 1);
        arrIdNhanVien[arrIdNhanVien.length - 1] = idNhanVien;

        arrTenNhanVien = Arrays.copyOf(arrTenNhanVien, arrTenNhanVien.length + 1);
        arrTenNhanVien[arrTenNhanVien.length - 1] = tenNhanVien;
    }

    public void nhapNhanVien() {
        System.out.println("Nhập số lượng nhân viên: ");
        int n = Integer.parseInt(sc.nextLine());
        arrIdNhanVien = new String[0];
        arrTenNhanVien = new String[0];

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập ID nhân viên thứ " + (i + 1) + ": ");
            String idNhanVien = sc.nextLine();
            System.out.println("Nhập tên nhân viên thứ " + (i + 1) + ": ");
            String tenNhanVien = sc.nextLine();

            themNhanVien(idNhanVien, tenNhanVien);
        }
    }

    public void nhap() {
        System.out.print("Nhập mã phòng: ");
        setMaPhong(sc.nextLine());
        System.out.print("Nhập tên phòng: ");
        setTenPhong(sc.nextLine());
        System.out.print("Nhập kinh phí hoạt động: ");
        setKinhPhiHoatDong(Double.parseDouble(sc.nextLine()));
        System.out.print("Nhập ngày thành lập: ");
        setNgayThanhLap(sc.nextLine());
        nhapNhanVien();
    }

    @Override
    public String toString() {
        StringBuilder nhanVienInfo = new StringBuilder();
        for (int i = 0; i < arrIdNhanVien.length; i++) {
            nhanVienInfo.append("- ID: ").append(arrIdNhanVien[i])
                    .append(", Tên: ").append(arrTenNhanVien[i]).append("\n");
        }
        return "Mã phòng: " + maPhong + "\n" +
                "Tên phòng: " + tenPhong + "\n" +
                "Kinh phí hoạt động: " + kinhPhiHoatDong + "\n" +
                "Ngày thành lập: " + ngayThanhLap + "\n" +
                "Danh sách nhân viên:\n" + nhanVienInfo;
    }
    public void xuat(){
        System.out.println(toString());
    }
}
