package QuanLy;

import type.GiamDoc;
import type.NhanSu;
import type.NhanVien;
import type.TruongPhong;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class QLNS implements Menu {
    private NhanSu[] arr_ns;
    private NhanSu[] arr_ns_daxoa;
    private Scanner sc = new Scanner(System.in);

    public QLNS() {
        this.arr_ns = new NhanSu[0];
        this.arr_ns_daxoa = new NhanSu[0];
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
    public void them_1_nhan_su_daxoa(NhanSu ns) {
        arr_ns_daxoa = Arrays.copyOf(arr_ns_daxoa, arr_ns_daxoa.length + 1);
        arr_ns_daxoa[arr_ns_daxoa.length - 1] = ns;
    }
    //nhanvien
    // Thêm nhân viên
    private void themNhanVien() {
        System.out.println("Nhập số lượng muốn thêm:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin nhân sự thứ " + (i + 1) + ":");
            NhanSu ns = new NhanVien();
            ns.nhap();
            them_1_nhan_su(ns);
        }
        System.out.println("Thêm thành công!!");
    }

    // Sửa thông tin nhân viên
    private void suaNhanVien() {
        System.out.println("Nhập ID hoặc Tên nhân viên muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (NhanSu ns : arr_ns) {
            if (ns instanceof NhanVien) {
                if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                    found = true;
                    System.out.println("\nThông tin hiện tại của nhân viên:");
                    ns.xuat();
                    System.out.println("\nBạn có muốn sửa thông tin này?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
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

    // Xóa nhân viên
    private void xoaNhanVien() {
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
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        them_1_nhan_su_daxoa(ns);
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

    // Ghi danh sách nhân viên ra file
    private void ghiDanhSachNhanVien() {
        String path = "src/file/DSNhanVien.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("--- Danh sách nhân viên ---\n");
            for (NhanSu ns : arr_ns) {
                if (ns instanceof NhanVien) {
                    writer.write(ns.toString());
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách nhân viên vào file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // Hiển thị danh sách nhân viên
    private void hienThiDanhSachNhanVien() {
        System.out.println("\n--- Danh sách nhân viên ---");
        for (NhanSu ns : arr_ns) {
            if (ns instanceof NhanVien) {
                ns.xuat();
            }
        }
    }

    // Hiển thị danh sách nhân viên đã xóa
    private void hienThiNhanVienDaXoa() {
        System.out.println("\n--- Danh sách nhân viên đã xóa ---");
        for (NhanSu ns : arr_ns_daxoa) {
            if (ns instanceof NhanVien) {
                ns.xuat();
            }
        }
    }

    // Đọc danh sách nhân viên từ file
    private void docDanhSachNhanVien() {
        String path = "src/file_test/FileNhanVien.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                NhanVien nv = new NhanVien();
                nv.setId(data[0]);
                nv.setName(data[1]);
                nv.setDiaChi(data[2]);
                nv.setPhone(data[3]);
                nv.setNamVaolam(Integer.parseInt(data[4]));
                nv.setHeSothidua(data[5]);
                nv.setSoNgaynghi(Integer.parseInt(data[6]));
                them_1_nhan_su(nv);
            }
            System.out.println("Đọc danh sách nhân viên từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
    //truongphong
    private void themTruongPhong() {
        System.out.println("Nhập số lượng Trưởng phòng muốn thêm:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin Trưởng phòng thứ " + (i + 1) + ":");
            NhanSu tp = new TruongPhong();
            tp.nhap();
            them_1_nhan_su(tp);
        }
        System.out.println("Thêm thành công!!");
    }
    private void suaTruongPhong() {
        System.out.println("Nhập ID hoặc Tên Trưởng phòng muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (NhanSu ns : arr_ns) {
            if (ns instanceof TruongPhong) {
                if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                    found = true;
                    System.out.println("\nThông tin hiện tại của Trưởng phòng:");
                    ns.xuat();
                    System.out.println("\nBạn có muốn sửa thông tin này?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
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
    private void xoaTruongPhong() {
        System.out.println("Nhập ID hoặc Tên Trưởng phòng muốn xóa:");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < arr_ns.length; i++) {
            NhanSu ns = arr_ns[i];
            if (ns instanceof TruongPhong) {
                if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                    found = true;
                    System.out.println("\nThông tin hiện tại của Trưởng phòng:");
                    ns.xuat();
                    System.out.println("\nBạn có chắc chắn muốn xóa Trưởng phòng này?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        them_1_nhan_su_daxoa(ns);
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
    private void ghiDanhSachTruongPhong() {
        String path = "src/file/DSTruongPhong.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("--- Danh sách Trưởng phòng ---\n");
            for (NhanSu ns : arr_ns) {
                if (ns instanceof TruongPhong) {
                    writer.write(ns.toString());
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách Trưởng phòng vào file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    private void hienThiDanhSachTruongPhong() {
        System.out.println("\n--- Danh sách Trưởng phòng ---");
        for (NhanSu ns : arr_ns) {
            if (ns instanceof TruongPhong) {
                ns.xuat();
            }
        }
    }
    private void hienThiTruongPhongDaXoa() {
        System.out.println("\n--- Danh sách Trưởng phòng đã xóa ---");
        for (NhanSu ns : arr_ns_daxoa) {
            if (ns instanceof TruongPhong) {
                ns.xuat();
            }
        }
    }
    private void docDanhSachTruongPhong() {
        String path = "src/file_test/FileTruongPhong.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                TruongPhong tp = new TruongPhong();
                tp.setId(data[0]);
                tp.setName(data[1]);
                tp.setDiaChi(data[2]);
                tp.setPhone(data[3]);
                tp.setNamVaolam(Integer.parseInt(data[4]));
                tp.setHeSothidua(data[5]);
                tp.setSoNgaynghi(Integer.parseInt(data[6]));
                them_1_nhan_su(tp);
            }
            System.out.println("Đọc danh sách Trưởng phòng từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
//giamdoc
private void themGiamDoc() {
    System.out.println("Nhập số lượng Giám đốc muốn thêm:");
    int n = Integer.parseInt(sc.nextLine());
    for (int i = 0; i < n; i++) {
        System.out.println("Nhập thông tin Giám đốc thứ " + (i + 1) + ":");
        NhanSu gd = new GiamDoc();
        gd.nhap();
        them_1_nhan_su(gd);
    }
    System.out.println("Thêm thành công!!");
}
    private void suaGiamDoc() {
        System.out.println("Nhập ID hoặc Tên Giám đốc muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (NhanSu ns : arr_ns) {
            if (ns instanceof GiamDoc) {
                if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                    found = true;
                    System.out.println("\nThông tin hiện tại của Giám đốc:");
                    ns.xuat();
                    System.out.println("\nBạn có muốn sửa thông tin này?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
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
    private void xoaGiamDoc() {
        System.out.println("Nhập ID hoặc Tên Giám đốc muốn xóa:");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < arr_ns.length; i++) {
            NhanSu ns = arr_ns[i];
            if (ns instanceof GiamDoc) {
                if (ns.getId().equalsIgnoreCase(key) || ns.getName().equalsIgnoreCase(key)) {
                    found = true;
                    System.out.println("\nThông tin hiện tại của Giám đốc:");
                    ns.xuat();
                    System.out.println("\nBạn có chắc chắn muốn xóa Giám đốc này?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 1) {
                        them_1_nhan_su_daxoa(ns);
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
    private void ghiDanhSachGiamDoc() {
        String path = "src/file/DSGiamDoc.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("--- Danh sách Giám đốc ---\n");
            for (NhanSu ns : arr_ns) {
                if (ns instanceof GiamDoc) {
                    writer.write(ns.toString());
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách Giám đốc vào file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    private void hienThiDanhSachGiamDoc() {
        System.out.println("\n--- Danh sách Giám đốc ---");
        for (NhanSu ns : arr_ns) {
            if (ns instanceof GiamDoc) {
                ns.xuat();
            }
        }
    }
    private void hienThiGiamDocDaXoa() {
        System.out.println("\n--- Danh sách Giám đốc đã xóa ---");
        for (NhanSu ns : arr_ns_daxoa) {
            if (ns instanceof GiamDoc) {
                ns.xuat();
            }
        }
    }
    private void docDanhSachGiamDoc() {
        String path = "src/file_test/FileGiamDoc.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                GiamDoc gd = new GiamDoc();
                gd.setId(data[0]);
                gd.setName(data[1]);
                gd.setDiaChi(data[2]);
                gd.setPhone(data[3]);
                gd.setNamVaolam(Integer.parseInt(data[4]));
                gd.setHeSothidua(data[5]);
                gd.setSoNgaynghi(Integer.parseInt(data[6]));
                them_1_nhan_su(gd);
            }
            System.out.println("Đọc danh sách Giám đốc từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
//--------------------------------------
public void timKiemNhanSuTheoIdHoacTen() {
    System.out.print("Nhập Mã hoặc Tên Nhân Sự cần tìm: ");
    String key = sc.nextLine();
    boolean found = false;
    for (NhanSu nhanSu : arr_ns) {
        if ((nhanSu.getId().equalsIgnoreCase(key) || nhanSu.getName().equalsIgnoreCase(key))) {
            found = true;
            System.out.println("Thông tin Nhân Sự tìm thấy:");
            nhanSu.xuat();
        }
    }
    if (!found) {
        System.out.println("Không tìm thấy Nhân Sự với Mã hoặc Tên đã nhập.");
    }
}

    @Override
    public void play() {
        boolean flag = true;
        while (flag) {
            menu();
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Bạn đang chọn Nhân viên");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> themNhanVien();
                        case 2 -> suaNhanVien();
                        case 3 -> xoaNhanVien();
                        case 4 -> hienThiDanhSachNhanVien();
                        case 5 -> hienThiNhanVienDaXoa();
                        case 6 -> docDanhSachNhanVien();
                        case 7 -> ghiDanhSachNhanVien();
                        case 8 -> System.out.println("Đã thoát ra");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 2 -> {
                    System.out.println("Bạn đang chọn Trưởng phòng");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> themTruongPhong();
                        case 2 -> suaTruongPhong();
                        case 3 -> xoaTruongPhong();
                        case 4 -> hienThiDanhSachTruongPhong();
                        case 5 -> hienThiTruongPhongDaXoa();
                        case 6 -> docDanhSachTruongPhong();
                        case 7 -> ghiDanhSachTruongPhong();
                        case 8 -> System.out.println("Đã thoát ra");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 3 -> {
                    System.out.println("Bạn đang chọn Giám đốc");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int choice2 = Integer.parseInt(sc.nextLine());
                    switch (choice2) {
                        case 1 -> themGiamDoc();
                        case 2 -> suaGiamDoc();
                        case 3 -> xoaGiamDoc();
                        case 4 -> hienThiDanhSachGiamDoc();
                        case 5 -> hienThiGiamDocDaXoa();
                        case 6 -> docDanhSachGiamDoc();
                        case 7 -> ghiDanhSachGiamDoc();
                        case 8 -> System.out.println("Đã thoát ra");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 4-> {
                    timKiemNhanSuTheoIdHoacTen();
                }
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    @Override
    public void menu() {
        System.out.println("\n------ Menu ------");
        System.out.println("1. Nhân viên");
        System.out.println("2. Trưởng phòng");
        System.out.println("3. Giám đốc");
        System.out.println("4. Tìm Kiếm");
        System.out.println("0. Thoát");
        System.out.println("--------------------");
    }

    @Override
    public void menu_chucnang() {
        System.out.println("\n------- Menu chức năng -------");
        System.out.println("1. Thêm Nhân Sự");
        System.out.println("2. Sửa Nhân Sự");
        System.out.println("3. Xóa Nhân Sự");
        System.out.println("4. Xuất Nhân Sự");
        System.out.println("5. Xuất Nhân Sự Đã Xóa");
        System.out.println("6. Đọc File TXT Nhân Sự ");
        System.out.println("7. Ghi File TXT Nhân Sự");
        System.out.println("8.Thoat");
        System.out.println("---------------------------------");
    }
}
