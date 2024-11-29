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

    @Override
    public void play(){
        boolean flag = true;
        while(flag){
            menu();
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose){
                case 1 -> {//de an lon
                    menu_chucnang();
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen){
                        case 1 -> {
                            System.out.println("Bạn đang chọn Du an lon");
                            System.out.print("Nhập số lượng đề án muốn thêm: ");
                            int soLuong = Integer.parseInt(sc.nextLine());
                            DeAn deAn = new DeAnLon();
                            for (int i = 0; i < soLuong; i++){
                                System.out.println("Nhập thông tin đề án thứ " + (i + 1) + ":");
                                deAn.nhap();
                               them_1_dean(deAn);
                            }
                            System.out.println("Thêm đề án thành công!");
                        }
                        case 2 -> {
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
                        case 3 -> {
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
                        case 4 ->{
                            System.out.println("\n---Danh sach cac De an Lon---");
                            for(DeAn deAn : arrda){
                                if(deAn instanceof DeAnLon){
                                    deAn.xuat();
                                }
                            }
                        }
                        case 5 ->{
                            System.out.println("\n--- Danh sách các Đề án Lon da xoa---");
                            for (DeAn deAn : arrdaDaXoa) {
                                if (deAn instanceof DeAnLon) {
                                    deAn.xuat();
                                }
                            }
                        }
                        case 6 -> {
                            String fileName = "src/file/DSdeanlon.txt";
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                                writer.write("--- Danh sách đề án Lon ---\n");
                                for (DeAn deAn : arrda) {
                                    if(deAn instanceof DeAnLon){
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
                        case 7 -> {
                            String path = "src/file_test/FileDeAnlon.txt";
                            //id,ten,kinhphi, ngaybatdau, ngketthuc,slnguoi,idng1,tenng1,....
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
                                System.out.println("Đọc danh sách nhân vien từ file thành công!");
                            } catch (IOException e) {
                                System.out.println("Lỗi khi đọc file: " + e.getMessage());
                            }
                        }
                        case 8 ->{
                            System.out.println("Dã Thoát Ra");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 2 -> {
                    menu_chucnang();
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen) {
                        case 1 -> {
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
                        case 2 -> {
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
                        case 3 -> {
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
                                        // Tạo mảng mới, bỏ qua phần tử tại vị trí `i`
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
                        case 4 -> {
                            System.out.println("\n--- Danh sách các Đề án vừa ---");
                            for (DeAn deAn : arrda) {
                                if (deAn instanceof DeAnVua) {
                                    deAn.xuat();
                                }
                            }
                        }
                        case 5 ->{
                            System.out.println("\n--- Danh sách các Đề án vừa da xoa---");
                            for (DeAn deAn : arrdaDaXoa) {
                                if (deAn instanceof DeAnVua) {
                                    deAn.xuat();
                                }
                            }
                        }
                        case 6 -> {
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
                        case 7 -> {
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
                        case 8 -> {
                            System.out.println("Đã thoát ra.");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 3 -> {
                    menu_chucnang();
                    int chosen = Integer.parseInt(sc.nextLine());
                    switch (chosen) {
                        case 1 -> {
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
                        case 2 -> {
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
                        case 3 -> {
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
                                        // Tạo mảng mới, bỏ qua phần tử tại vị trí `i`
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
                        case 4 -> {
                            System.out.println("\n--- Danh sách các Đề án nhỏ ---");
                            for (DeAn deAn : arrda) {
                                if (deAn instanceof DeAnNho) {
                                    deAn.xuat();
                                }
                            }
                        }
                        case 5 ->{
                            System.out.println("\n--- Danh sách các Đề án nhỏ da xoa---");
                            for (DeAn deAn : arrdaDaXoa) {
                                if (deAn instanceof DeAnNho) {
                                    deAn.xuat();
                                }
                            }
                        }
                        case 6 -> {
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
                        case 7 -> {
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
                        case 8 -> {
                            System.out.println("Đã thoát ra.");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 4 ->{
                    System.out.println("Thoát chương trình.");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
                }
        }

    }
    @Override
    public void menu(){
        System.out.println("1. Đề án lớn");
        System.out.println("2. Đề án vừa");
        System.out.println("3. Đề án nhỏ");
        System.out.println("4.Thoát");
    }
    @Override
    public void menu_chucnang(){
        System.out.println("---------Menu---------");
        System.out.println("1.Them De An");
        System.out.println("2.Sua De An");
        System.out.println("3.Xoa de An");
        System.out.println("4.Xuat De An");
        System.out.println("5.Xuat De An da xoa");
        System.out.println("6.In File TxT");
        System.out.println("7.Doc File TxT");
        System.out.println("8.Thoát");
    }
}