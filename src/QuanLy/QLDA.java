package QuanLy;

import dean.DeAn;
import dean.DeAnLon;
import dean.DeAnNho;
import dean.DeAnVua;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class QLDA implements Menu{
    private DeAn[] arrda;
    private DeAn[] arrdaDaXoa;
    private Scanner sc = new Scanner(System.in);
    public QLDA() {
        this.arrda = new DeAn[0];
        this.arrdaDaXoa = new DeAn[0];
    }

    public void them_1_dean(DeAn deAn) {
        arrda = Arrays.copyOf(arrda, arrda.length + 1);
        arrda[arrda.length - 1] = deAn;
    }
    public void them_1_dean_daxoa(DeAn deAn) {
        arrdaDaXoa = Arrays.copyOf(arrdaDaXoa, arrdaDaXoa.length + 1);
        arrdaDaXoa[arrdaDaXoa.length - 1] = deAn;
    }

    // deanlon
    private void themDeAnLon() {
        System.out.println("Bạn đang chọn Đề án lớn");
        System.out.print("Nhập số lượng đề án muốn thêm: ");
        int soLuong = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin đề án thứ " + (i + 1) + ":");
            DeAn deAn = new DeAnLon();
            deAn.nhap();
            them_1_dean(deAn);
        }
        System.out.println("Thêm đề án thành công!");
    }
    private void suaDeAnLon() {
        System.out.println("Nhập ID hoặc Tên đề án muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (DeAn deAn : arrda) {
            if (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key)) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có muốn sửa thông tin này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    System.out.println("Nhập thông tin mới:");
                    deAn.nhap();
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.out.println("Đã hủy chỉnh sửa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xoaDeAnLon() {
        System.out.println("Nhập ID hoặc Tên đề án muốn xóa:");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < arrda.length; i++) {
            DeAn deAn = arrda[i];
            if (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key)) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có chắc chắn muốn xóa đề án này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    them_1_dean_daxoa(deAn);
                    DeAn[] newArr = new DeAn[arrda.length - 1];
                    for (int j = 0, k = 0; j < arrda.length; j++) {
                        if (j != i) {
                            newArr[k++] = arrda[j];
                        }
                    }
                    arrda = newArr;
                    System.out.println("Xóa đề án thành công!");
                } else {
                    System.out.println("Đã hủy xóa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xuatDanhSachDeAnLon() {
        System.out.println("\n--- Danh sách các Đề án lớn ---");
        for (DeAn deAn : arrda) {
            if (deAn instanceof DeAnLon) {
                deAn.xuat();
            }
        }
    }
    private void xuatDanhSachDeAnLonDaXoa() {
        System.out.println("\n--- Danh sách các Đề án lớn đã xóa ---");
        for (DeAn deAn : arrdaDaXoa) {
            if (deAn instanceof DeAnLon) {
                deAn.xuat();
            }
        }
    }
    private void ghiDanhSachDeAnLon() {
        String fileName = "src/file/DSdeanlon.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("--- Danh sách đề án lớn ---\n");
            for (DeAn deAn : arrda) {
                if (deAn instanceof DeAnLon) {
                    writer.write(deAn.toString());
                    writer.write("\n-----------------------------------");
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách đề án ra file thành công!");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
        }
    }
    private void docDanhSachDeAnLon() {
        String path = "src/file_test/FileDeAnlon.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                DeAn deAn = new DeAnLon();
                deAn.setId(data[0]);
                deAn.setTenDuAn(data[1]);
                deAn.setKinhPhi(Double.parseDouble(data[2]));
                deAn.setNgayBatDau(data[3]);
                deAn.setNgayKetThuc(data[4]);
                // Xử lý người phụ trách
                int soNguoiPhuTrach = Integer.parseInt(data[5].trim());
                for (int i = 0; i < soNguoiPhuTrach; i++) {
                    String idNguoi = data[6 + i * 2];
                    String tenNguoi = data[6 + i * 2 + 1];
                    deAn.themNguoiPhuTrach(idNguoi, tenNguoi);
                }
                them_1_dean(deAn);
            }
            System.out.println("Đọc danh sách đề án từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    //devua
    private void themDeAnVua() {
        System.out.println("Bạn đang chọn Đề án vừa");
        System.out.print("Nhập số lượng đề án muốn thêm: ");
        int soLuong = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin đề án vừa thứ " + (i + 1) + ":");
            DeAn deAn = new DeAnVua();
            deAn.nhap();
            them_1_dean(deAn);
        }
        System.out.println("Thêm đề án vừa thành công!");
    }
    private void suaDeAnVua() {
        System.out.println("Nhập ID hoặc Tên đề án muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (DeAn deAn : arrda) {
            if (deAn instanceof DeAnVua &&
                    (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key))) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có muốn sửa thông tin này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    System.out.println("Nhập thông tin mới:");
                    deAn.nhap();
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.out.println("Đã hủy chỉnh sửa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xoaDeAnVua() {
        System.out.println("Nhập ID hoặc Tên đề án muốn xóa:");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < arrda.length; i++) {
            DeAn deAn = arrda[i];
            if (deAn instanceof DeAnVua &&
                    (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key))) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có chắc chắn muốn xóa đề án này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    them_1_dean_daxoa(deAn);
                    DeAn[] newArr = new DeAn[arrda.length - 1];
                    for (int j = 0, k = 0; j < arrda.length; j++) {
                        if (j != i) {
                            newArr[k++] = arrda[j];
                        }
                    }
                    arrda = newArr;
                    System.out.println("Xóa đề án thành công!");
                } else {
                    System.out.println("Đã hủy xóa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xuatDanhSachDeAnVua() {
        System.out.println("\n--- Danh sách các Đề án vừa ---");
        for (DeAn deAn : arrda) {
            if (deAn instanceof DeAnVua) {
                deAn.xuat();
            }
        }
    }
    private void xuatDanhSachDeAnVuaDaXoa() {
        System.out.println("\n--- Danh sách các Đề án vừa đã xóa ---");
        for (DeAn deAn : arrdaDaXoa) {
            if (deAn instanceof DeAnVua) {
                deAn.xuat();
            }
        }
    }
    private void ghiDanhSachDeAnVua() {
        String fileName = "src/file/DSdeanvua.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("--- Danh sách đề án vừa ---\n");
            for (DeAn deAn : arrda) {
                if (deAn instanceof DeAnVua) {
                    writer.write(deAn.toString());
                    writer.write("\n------------------------------------");
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách đề án ra file thành công!");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
        }
    }
    private void docDanhSachDeAnVua() {
        String path = "src/file_test/FileDeAnvua.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                DeAn deAn = new DeAnVua();
                deAn.setId(data[0]);
                deAn.setTenDuAn(data[1]);
                deAn.setKinhPhi(Double.parseDouble(data[2]));
                deAn.setNgayBatDau(data[3]);
                deAn.setNgayKetThuc(data[4]);
                int soNguoiPhuTrach = Integer.parseInt(data[5].trim());
                for (int i = 0; i < soNguoiPhuTrach; i++) {
                    String idNguoi = data[6 + i * 2];
                    String tenNguoi = data[6 + i * 2 + 1];
                    deAn.themNguoiPhuTrach(idNguoi, tenNguoi);
                }
                them_1_dean(deAn);
            }
            System.out.println("Đọc danh sách đề án từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
//denho

    private void themDeAnNho() {
        System.out.println("Bạn đang chọn Đề án nhỏ");
        System.out.print("Nhập số lượng đề án muốn thêm: ");
        int soLuong = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin đề án nhỏ thứ " + (i + 1) + ":");
            DeAn deAn = new DeAnNho();
            deAn.nhap();
            them_1_dean(deAn);
        }
        System.out.println("Thêm đề án nhỏ thành công!");
    }
    private void suaDeAnNho() {
        System.out.println("Nhập ID hoặc Tên đề án muốn sửa:");
        String key = sc.nextLine();
        boolean found = false;
        for (DeAn deAn : arrda) {
            if (deAn instanceof DeAnNho &&
                    (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key))) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có muốn sửa thông tin này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    System.out.println("Nhập thông tin mới:");
                    deAn.nhap();
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.out.println("Đã hủy chỉnh sửa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xoaDeAnNho() {
        System.out.println("Nhập ID hoặc Tên đề án muốn xóa:");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < arrda.length; i++) {
            DeAn deAn = arrda[i];
            if (deAn instanceof DeAnNho &&
                    (deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key))) {
                found = true;
                System.out.println("\nThông tin hiện tại của đề án:");
                System.out.println(deAn);
                System.out.println("\nBạn có chắc chắn muốn xóa đề án này?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    them_1_dean_daxoa(deAn);
                    DeAn[] newArr = new DeAn[arrda.length - 1];
                    for (int j = 0, k = 0; j < arrda.length; j++) {
                        if (j != i) {
                            newArr[k++] = arrda[j];
                        }
                    }
                    arrda = newArr;
                    System.out.println("Xóa đề án thành công!");
                } else {
                    System.out.println("Đã hủy xóa.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đề án với ID hoặc Tên đã nhập.");
        }
    }
    private void xuatDanhSachDeAnNho() {
        System.out.println("\n--- Danh sách các Đề án nhỏ ---");
        for (DeAn deAn : arrda) {
            if (deAn instanceof DeAnNho) {
                deAn.xuat();
            }
        }
    }
    private void xuatDanhSachDeAnNhoDaXoa() {
        System.out.println("\n--- Danh sách các Đề án nhỏ đã xóa ---");
        for (DeAn deAn : arrdaDaXoa) {
            if (deAn instanceof DeAnNho) {
                deAn.xuat();
            }
        }
    }
    private void ghiDanhSachDeAnNho() {
        String fileName = "src/file/DSdeannho.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("--- Danh sách đề án nhỏ ---\n");
            for (DeAn deAn : arrda) {
                if (deAn instanceof DeAnNho) {
                    writer.write(deAn.toString());
                    writer.write("\n-----------------------------------");
                    writer.newLine();
                }
            }
            System.out.println("Ghi danh sách đề án ra file thành công!");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
        }
    }
    private void docDanhSachDeAnNho() {
        String path = "src/file_test/FileDeAnnho.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                DeAn deAn = new DeAnNho();
                deAn.setId(data[0]);
                deAn.setTenDuAn(data[1]);
                deAn.setKinhPhi(Double.parseDouble(data[2]));
                deAn.setNgayBatDau(data[3]);
                deAn.setNgayKetThuc(data[4]);
                int soNguoiPhuTrach = Integer.parseInt(data[5].trim());
                for (int i = 0; i < soNguoiPhuTrach; i++) {
                    String idNguoi = data[6 + i * 2];
                    String tenNguoi = data[6 + i * 2 + 1];
                    deAn.themNguoiPhuTrach(idNguoi, tenNguoi);
                }
                them_1_dean(deAn);
            }
            System.out.println("Đọc danh sách đề án từ file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
    //---------------------------------------------------
    public void timKiemDeAnTheoIdHoacTen() {
        System.out.print("Nhập Mã hoặc Tên Đề Án cần tìm: ");
        String key = sc.nextLine();
        boolean found = false;
        for (DeAn deAn : arrda) {
            if ((deAn.getId().equalsIgnoreCase(key) || deAn.getTenDuAn().equalsIgnoreCase(key))) {
                found = true;
                System.out.println("Thông tin Đề Án tìm thấy:");
                deAn.xuat();
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy Đề Án với Mã hoặc Tên đã nhập.");
        }
    }

    @Override
    public void play(){
        boolean flag = true;
        while(flag){
            menu();
            System.out.print("Lua Chon Cua Ban :");
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose){
                case 1 -> {//de an lon
                    menu_chucnang();
                    System.out.print("Lua Chon Cua Ban :");
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen){
                        case 1 -> themDeAnLon();
                        case 2 -> suaDeAnLon();
                        case 3 -> xoaDeAnLon();
                        case 4 -> xuatDanhSachDeAnLon();
                        case 5 -> xuatDanhSachDeAnLonDaXoa();
                        case 6 -> docDanhSachDeAnLon();
                        case 7 -> ghiDanhSachDeAnLon();
                        case 8 -> System.out.println("Đã thoát.");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 2 -> {//de an vua
                    menu_chucnang();
                    System.out.print("Lua Chon Cua Ban :");
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen) {
                        case 1 -> themDeAnVua();
                        case 2 -> suaDeAnVua();
                        case 3 -> xoaDeAnVua();
                        case 4 -> xuatDanhSachDeAnVua();
                        case 5 -> xuatDanhSachDeAnVuaDaXoa();
                        case 6 -> docDanhSachDeAnVua();
                        case 7 -> ghiDanhSachDeAnVua();
                        case 8 -> System.out.println("Đã thoát ra.");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 3 -> {//de an nho
                    menu_chucnang();
                    System.out.print("Lua Chon Cua Ban :");
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen) {
                        case 1 -> themDeAnNho();
                        case 2 -> suaDeAnNho();
                        case 3 -> xoaDeAnNho();
                        case 4 -> xuatDanhSachDeAnNho();
                        case 5 -> xuatDanhSachDeAnNhoDaXoa();
                        case 6 -> docDanhSachDeAnNho();
                        case 7 -> ghiDanhSachDeAnLon();
                        case 8 -> System.out.println("Đã thoát ra.");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 4 ->{
                    timKiemDeAnTheoIdHoacTen();
                }
                case 0 ->{
                    System.out.println("Thoát chương trình.");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    @Override
    public void menu(){
        System.out.println("\n------ Menu ------");
        System.out.println("1. Đề án lớn");
        System.out.println("2. Đề án vừa");
        System.out.println("3. Đề án nhỏ");
        System.out.println("4. Tìm Kiếm");
        System.out.println("0.Thoát");
        System.out.println("--------------------");
    }
    @Override
    public void menu_chucnang(){
        System.out.println("\n------- Menu chức năng -------");
        System.out.println("1.Thêm Đề Án");
        System.out.println("2.Sửa Đề Án");
        System.out.println("3.Xóa Đề Án");
        System.out.println("4.Xuất Đề Án");
        System.out.println("5.Xuất Đề Án Đã Xóa");
        System.out.println("6.Đọc File Đề Án TxT");
        System.out.println("7.Ghi File Đề Án TxT");
        System.out.println("8.Thoát");
        System.out.println("---------------------------------");
    }
}