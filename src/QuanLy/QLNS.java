package QuanLy;

import type.GiamDoc;
import type.NhanSu;
import type.NhanVien;
import type.TruongPhong;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class QLNS {
    private NhanSu[] arr_ns;
    private Scanner sc = new Scanner(System.in);

    public QLNS() {
        this.arr_ns = new NhanSu[0]; // Khởi tạo mảng rỗng
    }

    public NhanSu[] getArr_ns() {
        return arr_ns;
    }

    public void setArr_ns(NhanSu[] arr_ns) {
        this.arr_ns = arr_ns;
    }

    public void them_1_nhan_su(NhanSu ns) {
        arr_ns = Arrays.copyOf(arr_ns, arr_ns.length + 1);
        arr_ns[arr_ns.length - 1] = ns;
    }

    public void ghiDanhSachRaFile(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write("--- Danh sách nhân sự ---\n");
            for (NhanSu ns : arr_ns) {
                if (ns instanceof NhanVien) { // Nếu chỉ muốn ghi NhanVien
                    writer.write(ns.toString());
                    writer.newLine(); // Xuống dòng sau mỗi nhân sự
                }
            }
            System.out.println("Ghi danh sách nhân sự vào file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public void play() {
        boolean flag = true;
        while (flag) {
            menu();
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> { // nhan vien
                    System.out.println("Bạn đang chọn Nhân viên");
                    menu_chuc_nang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> {
                            System.out.println("Nhập số lượng muốn thêm:");
                            int n = Integer.parseInt(sc.nextLine());
                            for (int i = 0; i < n; i++) {
                                System.out.println("Nhập thông tin nhân sự thứ " + (i + 1) + ":");
                                NhanVien ns = new NhanVien();
                                ns.nhap();
                                them_1_nhan_su(ns);
                            }
                        }
                        case 2 -> {
                            System.out.println("Nhập ID hoặc Tên nhân viên muốn sửa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof NhanVien) {  // Kiểm tra nếu là NhanVien
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của nhân viên:");
                                        ns.xuat();
                                        System.out.println("\nBạn có muốn sửa thông tin này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            System.out.println("Nhập thông tin mới:");
                                            ns.nhap();
                                            System.out.println("Cập nhật thành công!");
                                        } else {
                                            System.out.println("Đã hủy chỉnh sửa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy nhân sự với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 3 ->{
                            System.out.println("Nhập ID hoặc Tên nhân viên muốn xóa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arr_ns.length; i++) {
                                NhanSu ns = arr_ns[i];
                                if (ns instanceof NhanVien) {
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của nhân viên:");
                                        ns.xuat();
                                        System.out.println("\nBạn có chắc chắn muốn xóa nhân viên này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            NhanSu[] newArr = new NhanSu[arr_ns.length - 1];
                                            for (int j = 0, k = 0; j < arr_ns.length; j++) {
                                                if (j != i) {
                                                    newArr[k++] = arr_ns[j];
                                                }
                                            }
                                            arr_ns = newArr;
                                            System.out.println("Xóa nhân viên thành công!");
                                        } else {
                                            System.out.println("Đã hủy xóa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy nhân sự với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 4 ->{
                            String path = "src/file/DSNhanVien.txt";
                            ghiDanhSachRaFile(path);
                        }
                        case 5 ->{
                            System.out.println("\n--- Danh sách nhân vien ---");
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof NhanVien) {
                                    ns.xuat();
                                }
                            }
                        }
                        case 6 ->{
                            System.out.println("Dã Thoát Ra");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 2 -> { // Trưởng phòng
                    System.out.println("Bạn đang chọn Trưởng phòng");
                    menu_chuc_nang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> {
                            System.out.println("Nhập số lượng Trưởng phòng muốn thêm:");
                            int n = Integer.parseInt(sc.nextLine());
                            for (int i = 0; i < n; i++) {
                                System.out.println("Nhập thông tin Trưởng phòng thứ " + (i + 1) + ":");
                                TruongPhong tp = new TruongPhong(); // Khởi tạo Trưởng phòng
                                tp.nhap();
                                them_1_nhan_su(tp);
                            }
                        }
                        case 2 -> {
                            System.out.println("Nhập ID hoặc Tên Trưởng phòng muốn sửa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof TruongPhong) { // Kiểm tra nếu là Trưởng phòng
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của Trưởng phòng:");
                                        ns.xuat();
                                        System.out.println("\nBạn có muốn sửa thông tin này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            System.out.println("Nhập thông tin mới:");
                                            ns.nhap();
                                            System.out.println("Cập nhật thành công!");
                                        } else {
                                            System.out.println("Đã hủy chỉnh sửa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Trưởng phòng với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 3 -> {
                            System.out.println("Nhập ID hoặc Tên Trưởng phòng muốn xóa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arr_ns.length; i++) {
                                NhanSu ns = arr_ns[i];
                                if (ns instanceof TruongPhong) { // Kiểm tra nếu là Trưởng phòng
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của Trưởng phòng:");
                                        ns.xuat();
                                        System.out.println("\nBạn có chắc chắn muốn xóa Trưởng phòng này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            NhanSu[] newArr = new NhanSu[arr_ns.length - 1];
                                            for (int j = 0, k = 0; j < arr_ns.length; j++) {
                                                if (j != i) {
                                                    newArr[k++] = arr_ns[j];
                                                }
                                            }
                                            arr_ns = newArr;
                                            System.out.println("Xóa Trưởng phòng thành công!");
                                        } else {
                                            System.out.println("Đã hủy xóa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Trưởng phòng với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 4 -> {
                            String path = "src/file/DSTruongPhong.txt";
                            ghiDanhSachRaFile(path);
                        }
                        case 5 -> {
                            System.out.println("\n--- Danh sách Trưởng phòng ---");
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof TruongPhong) {
                                    ns.xuat();
                                }
                            }
                        }
                        case 6 ->{
                            System.out.println("Dã Thoát Ra");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }

                }
                case 3 -> { // Giám đốc
                    System.out.println("Bạn đang chọn Giám đốc");
                    menu_chuc_nang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> {
                            System.out.println("Nhập số lượng Giám đốc muốn thêm:");
                            int n = Integer.parseInt(sc.nextLine());
                            for (int i = 0; i < n; i++) {
                                System.out.println("Nhập thông tin Giám đốc thứ " + (i + 1) + ":");
                                GiamDoc gd = new GiamDoc(); // Khởi tạo Giám đốc
                                gd.nhap();
                                them_1_nhan_su(gd);
                            }
                        }
                        case 2 -> {
                            System.out.println("Nhập ID hoặc Tên Giám đốc muốn sửa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof GiamDoc) { // Kiểm tra nếu là Giám đốc
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của Giám đốc:");
                                        ns.xuat();
                                        System.out.println("\nBạn có muốn sửa thông tin này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            System.out.println("Nhập thông tin mới:");
                                            ns.nhap();
                                            System.out.println("Cập nhật thành công!");
                                        } else {
                                            System.out.println("Đã hủy chỉnh sửa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Giám đốc với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 3 -> {
                            System.out.println("Nhập ID hoặc Tên Giám đốc muốn xóa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arr_ns.length; i++) {
                                NhanSu ns = arr_ns[i];
                                if (ns instanceof GiamDoc) { // Kiểm tra nếu là Giám đốc
                                    if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                                        found = true;
                                        System.out.println("\nThông tin hiện tại của Giám đốc:");
                                        ns.xuat();
                                        System.out.println("\nBạn có chắc chắn muốn xóa Giám đốc này?");
                                        System.out.println("1. Yes");
                                        System.out.println("2. No");
                                        int n = Integer.parseInt(sc.nextLine());
                                        if (n == 1) {
                                            NhanSu[] newArr = new NhanSu[arr_ns.length - 1];
                                            for (int j = 0, k = 0; j < arr_ns.length; j++) {
                                                if (j != i) {
                                                    newArr[k++] = arr_ns[j];
                                                }
                                            }
                                            arr_ns = newArr;
                                            System.out.println("Xóa Giám đốc thành công!");
                                        } else {
                                            System.out.println("Đã hủy xóa.");
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Giám đốc với ID hoặc Tên đã nhập.");
                            }
                        }
                        case 4 -> {
                            String path = "src/file/DSGiamDoc.txt";
                            ghiDanhSachRaFile(path);
                        }
                        case 5 -> {
                            System.out.println("\n--- Danh sách Giám đốc ---");
                            for (NhanSu ns : arr_ns) {
                                if (ns instanceof GiamDoc) {
                                    ns.xuat();
                                }
                            }
                        }
                        case 6 ->{
                            System.out.println("Dã Thoát Ra");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public void menu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Nhân viên");
        System.out.println("2. Trưởng phòng");
        System.out.println("3. Giám đốc");
        System.out.println("0. Thoát");
    }

    public void menu_chuc_nang() {
        System.out.println("\n--- Menu chức năng ---");
        System.out.println("1. Thêm nhân sự");
        System.out.println("2. Sửa");
        System.out.println("3. Xóa");
        System.out.println("4. In tệp TXT");
        System.out.println("5. Xuất danh sách");
        System.out.println("6.Thoat");
    }

}
