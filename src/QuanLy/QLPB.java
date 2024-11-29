package QuanLy;

import Phongban.PhongBan;
import Phongban.PhongCNTT;
import Phongban.PhongKeToan;
import Phongban.PhongMK;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class QLPB implements Menu{
    private PhongBan[] arrpb;
    private PhongBan[] arrpb_da_xoa;
    private Scanner sc = new Scanner(System.in);

    public QLPB() {
        arrpb = new PhongBan[0];
        arrpb_da_xoa = new PhongBan[0];
    }
    public void them_1_phongban(PhongBan phongBan) {
        arrpb = Arrays.copyOf(arrpb,arrpb.length+1);
        arrpb[arrpb.length-1] = phongBan;
    }
    public void them_1_phongban_da_xoa(PhongBan phongBan) {
        arrpb_da_xoa = Arrays.copyOf(arrpb_da_xoa,arrpb_da_xoa.length+1);
        arrpb_da_xoa[arrpb_da_xoa.length-1] = phongBan;
    }
@Override
    public void play() {
        boolean flag = true;
        while (flag) {
            menu();
            System.out.print("Lựa chọn của bạn: ");
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose) {
                case 1 -> {
                    System.out.println("Bạn Đang chọn Phong Ban Công Nghệ Thông Tin");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int chose2 = Integer.parseInt(sc.nextLine());
                    switch (chose2) {
                        case 1 -> {
                            System.out.print("Nhập số lượng phòng ban muốn thêm: ");
                            int soLuong = Integer.parseInt(sc.nextLine());
                            PhongBan phongBan = new PhongCNTT();
                            for (int i = 0; i < soLuong; i++) {
                                System.out.println("Nhập thông tin phòng ban thứ " + (i + 1) + ":");
                                phongBan.nhap();
                                them_1_phongban(phongBan);
                            }
                            System.out.println("Thêm phòng ban thành công!");

                        }
                        case 2 -> {
                            System.out.print("Nhập mã phòng ban cần sửa hoặc tên phòng muốn sửa :");
                            String key = sc.nextLine();
                            boolean found = false;
                            for(PhongBan pb : arrpb) {
                                if(pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key)) {
                                    found = true;
                                    System.out.println("\nThông tin hiện tại của phòng ban:");
                                    System.out.println(pb);
                                    System.out.println("\nBạn có muốn sửa thông tin này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if(n == 1) {
                                        System.out.println("Nhập thông tin mới:");
                                        pb.nhap();
                                        System.out.println("Cập nhật thành công!");
                                    }else {
                                        System.out.println("Đã hủy chỉnh sửa.");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy phòng ban với mã đã nhập.");
                            }
                        }
                        case 3 -> {
                            System.out.println("\n---Danh Sách Các Phòng Ban Công nghê Thông Tin---");
                            for(PhongBan pb : arrpb) {
                                if(pb instanceof PhongCNTT) {
                                    pb.xuat();
                                };
                            }
                        }
                        case 4 -> {
                            String path = "src/file/DSPhongbancntt.txt";
                            try(BufferedWriter w = new BufferedWriter(new FileWriter(path))){
                                w.write("---------Danh Sach Phong Ban CNTT---------\n");
                                for(PhongBan pb : arrpb) {
                                    if(pb instanceof PhongCNTT) {
                                        w.write(pb.toString());
                                        w.write("\n--------------------------------");
                                        w.newLine();
                                    }
                                }
                                System.out.println("Ghi Thanh Cong!!!");
                            }catch(IOException e){
                                System.out.println("Lỗi Ghi File !!!" + e.getMessage());
                            }
                        }
                        case 5 -> {
                            System.out.println("Nhập Mã hoặc Tên Phòng Ban muốn xóa:");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arrpb.length; i++) {
                                PhongBan pb = arrpb[i];
                                if (pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key)) {
                                    found = true;
                                    System.out.println("Thông tin hiện tại của Phòng Ban:");
                                    pb.xuat();
                                    System.out.println("\nBạn có chắc chắn muốn xóa Phòng Ban này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n == 1) {
                                        them_1_phongban_da_xoa(pb);
                                        PhongBan[] newArr = new PhongBan[arrpb.length - 1];
                                        for (int j = 0, k = 0; j < arrpb.length; j++) {
                                            if (j != i) {
                                                newArr[k++] = arrpb[j];
                                            }
                                        }
                                        arrpb = newArr;
                                        System.out.println("Xóa Phòng Ban Thành Công!");
                                    } else {
                                        System.out.println("Đã Hủy Xóa!");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Phòng Ban với Mã hoặc Tên đã nhập.");
                            }
                        }
                        case 6 -> {
                            System.out.println("\n---Danh sách các phòng ban đã xóa");
                            for(PhongBan pb : arrpb_da_xoa){
                                if(pb instanceof PhongCNTT) {
                                    pb.xuat();
                                }
                            }
                        }
                        case 7 ->{
                            String path = "src/file_test/FilePhongbancntt.txt";
                            try(BufferedReader br = new BufferedReader(new FileReader(path))) {
                                String line;
                                while((line = br.readLine()) != null){
                                    String[] data = line.split(",");
                                    PhongBan pb = new PhongCNTT();
                                    pb.setMaPhong(data[0]);
                                    pb.setTenPhong(data[1]);
                                    pb.setKinhPhiHoatDong(Double.parseDouble(data[2]));
                                    pb.setNgayThanhLap(data[3]);
                                    pb.soLuong(Integer.parseInt(data[4].trim()));
                                    int n = Integer.parseInt(data[5].trim());
                                    for(int i =0;i<n;i++){
                                        String id = data[6 + i *2 ];
                                        String name = data[6 + i * 2 + 1];
                                        pb.themNhanVien(id, name);
                                    }
                                    them_1_phongban(pb);
                                }
                                System.out.println("Đọc File Thành Công!!!");
                            }catch (IOException e) {
                                System.out.println("Lỗi Khi Đọc File><!!");
                            }
                        }
                        case 8 -> {
                            System.out.println("Dã Thoát Ra");
                        }
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 2 -> {
                    System.out.println("Bạn Đang Chọn Phòng Ban Marketing");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int chose2 = Integer.parseInt(sc.nextLine());
                    switch (chose2) {
                        case 1 -> {
                            System.out.print("Nhập số lượng phòng ban muốn thêm: ");
                            int soLuong = Integer.parseInt(sc.nextLine());
                            for (int i = 0; i < soLuong; i++) {
                                PhongBan phongBan = new PhongMK();
                                System.out.println("Nhập thông tin phòng ban thứ " + (i + 1) + ":");
                                phongBan.nhap();
                                them_1_phongban(phongBan);
                            }
                            System.out.println("Thêm phòng ban thành công!");
                        }
                        case 2 -> {
                            System.out.print("Nhập mã hoặc tên phòng ban cần sửa: ");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (PhongBan pb : arrpb) {
                                if ((pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key))
                                        && pb instanceof PhongMK) {
                                    found = true;
                                    System.out.println("\nThông tin hiện tại của phòng ban:");
                                    pb.xuat();
                                    System.out.println("\nBạn có muốn sửa thông tin này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n == 1) {
                                        System.out.println("Nhập thông tin mới:");
                                        pb.nhap();
                                        System.out.println("Cập nhật thành công!");
                                    } else {
                                        System.out.println("Đã hủy chỉnh sửa.");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy phòng ban Marketing với mã hoặc tên đã nhập.");
                            }
                        }
                        case 3 -> {
                            System.out.println("\n---Danh Sách Các Phòng Ban Marketing---");
                            for (PhongBan pb : arrpb) {
                                if (pb instanceof PhongMK) {
                                    pb.xuat();
                                }
                            }
                        }
                        case 4 -> {
                            String path = "src/file/DSPhongbanmarketing.txt";
                            try (BufferedWriter w = new BufferedWriter(new FileWriter(path))) {
                                w.write("---------Danh Sách Phòng Ban Marketing---------\n");
                                for (PhongBan pb : arrpb) {
                                    if (pb instanceof PhongMK) {
                                        w.write(pb.toString());
                                        w.write("\n--------------------------------");
                                        w.newLine();
                                    }
                                }
                                System.out.println("Ghi Thành Công!!!");
                            } catch (IOException e) {
                                System.out.println("Lỗi Ghi File!!! " + e.getMessage());
                            }
                        }
                        case 5 -> {
                            System.out.print("Nhập Mã hoặc Tên Phòng Ban muốn xóa: ");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arrpb.length; i++) {
                                PhongBan pb = arrpb[i];
                                if ((pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key))
                                        && pb instanceof PhongMK) {
                                    found = true;
                                    System.out.println("Thông tin hiện tại của Phòng Ban:");
                                    pb.xuat();
                                    System.out.println("\nBạn có chắc chắn muốn xóa Phòng Ban này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n == 1) {
                                        them_1_phongban_da_xoa(pb);
                                        PhongBan[] newArr = new PhongBan[arrpb.length - 1];
                                        for (int j = 0, k = 0; j < arrpb.length; j++) {
                                            if (j != i) {
                                                newArr[k++] = arrpb[j];
                                            }
                                        }
                                        arrpb = newArr;
                                        System.out.println("Xóa Phòng Ban Thành Công!");
                                    } else {
                                        System.out.println("Đã Hủy Xóa!");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Phòng Ban Marketing với mã hoặc tên đã nhập.");
                            }
                        }
                        case 6 -> {
                            System.out.println("\n---Danh Sách Các Phòng Ban Marketing Đã Xóa---");
                            for (PhongBan pb : arrpb_da_xoa) {
                                if (pb instanceof PhongMK) {
                                    pb.xuat();
                                }
                            }
                        }
                        case 7 -> {
                            String path = "src/file_test/FilePhongbanmarketing.txt";
                            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(",");
                                    PhongBan pb = new PhongMK();
                                    pb.setMaPhong(data[0]);
                                    pb.setTenPhong(data[1]);
                                    pb.setKinhPhiHoatDong(Double.parseDouble(data[2]));
                                    pb.setNgayThanhLap(data[3]);
                                    pb.soLuong(Integer.parseInt(data[4].trim()));
                                    int n = Integer.parseInt(data[5].trim());
                                    for (int i = 0; i < n; i++) {
                                        String id = data[6 + i * 2];
                                        String name = data[6 + i * 2 + 1];
                                        pb.themNhanVien(id, name);
                                    }
                                    them_1_phongban(pb);
                                }
                                System.out.println("Đọc File Thành Công!!!");
                            } catch (IOException e) {
                                System.out.println("Lỗi Khi Đọc File!!!");
                            }
                        }
                        case 8 -> System.out.println("Đã Thoát!");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 3 -> {
                    System.out.println("Bạn Đang Chọn Phòng Ban Kế Toán");
                    menu_chucnang();
                    System.out.print("Lựa chọn chức năng: ");
                    int chose2 = Integer.parseInt(sc.nextLine());
                    switch (chose2) {
                        case 1 -> {
                            System.out.print("Nhập số lượng phòng ban muốn thêm: ");
                            int soLuong = Integer.parseInt(sc.nextLine());
                            for (int i = 0; i < soLuong; i++) {
                                PhongBan phongBan = new PhongKeToan();
                                System.out.println("Nhập thông tin phòng ban thứ " + (i + 1) + ":");
                                phongBan.nhap();
                                them_1_phongban(phongBan);
                            }
                            System.out.println("Thêm phòng ban thành công!");
                        }
                        case 2 -> {
                            System.out.print("Nhập mã hoặc tên phòng ban cần sửa: ");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (PhongBan pb : arrpb) {
                                if ((pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key))
                                        && pb instanceof PhongKeToan) {
                                    found = true;
                                    System.out.println("\nThông tin hiện tại của phòng ban:");
                                    pb.xuat();
                                    System.out.println("\nBạn có muốn sửa thông tin này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n == 1) {
                                        System.out.println("Nhập thông tin mới:");
                                        pb.nhap();
                                        System.out.println("Cập nhật thành công!");
                                    } else {
                                        System.out.println("Đã hủy chỉnh sửa.");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy phòng ban Kế Toán với mã hoặc tên đã nhập.");
                            }
                        }
                        case 3 -> {
                            System.out.println("\n---Danh Sách Các Phòng Ban Kế Toán---");
                            for (PhongBan pb : arrpb) {
                                if (pb instanceof PhongKeToan) {
                                    pb.xuat();
                                }
                            }
                        }
                        case 4 -> {
                            String path = "src/file/DSPhongbanketoan.txt";
                            try (BufferedWriter w = new BufferedWriter(new FileWriter(path))) {
                                w.write("---------Danh Sách Phòng Ban Kế Toán---------\n");
                                for (PhongBan pb : arrpb) {
                                    if (pb instanceof PhongKeToan) {
                                        w.write(pb.toString());
                                        w.write("\n--------------------------------");
                                        w.newLine();
                                    }
                                }
                                System.out.println("Ghi Thành Công!!!");
                            } catch (IOException e) {
                                System.out.println("Lỗi Ghi File!!! " + e.getMessage());
                            }
                        }
                        case 5 -> {
                            System.out.print("Nhập Mã hoặc Tên Phòng Ban muốn xóa: ");
                            String key = sc.nextLine();
                            boolean found = false;
                            for (int i = 0; i < arrpb.length; i++) {
                                PhongBan pb = arrpb[i];
                                if ((pb.getMaPhong().equalsIgnoreCase(key) || pb.getTenPhong().equalsIgnoreCase(key))
                                        && pb instanceof PhongKeToan) {
                                    found = true;
                                    System.out.println("Thông tin hiện tại của Phòng Ban:");
                                    pb.xuat();
                                    System.out.println("\nBạn có chắc chắn muốn xóa Phòng Ban này?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n == 1) {
                                        them_1_phongban_da_xoa(pb);
                                        PhongBan[] newArr = new PhongBan[arrpb.length - 1];
                                        for (int j = 0, k = 0; j < arrpb.length; j++) {
                                            if (j != i) {
                                                newArr[k++] = arrpb[j];
                                            }
                                        }
                                        arrpb = newArr;
                                        System.out.println("Xóa Phòng Ban Thành Công!");
                                    } else {
                                        System.out.println("Đã Hủy Xóa!");
                                    }
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Không tìm thấy Phòng Ban Kế Toán với mã hoặc tên đã nhập.");
                            }
                        }
                        case 6 -> {
                            System.out.println("\n---Danh Sách Các Phòng Ban Kế Toán Đã Xóa---");
                            for (PhongBan pb : arrpb_da_xoa) {
                                if (pb instanceof PhongKeToan) {
                                    pb.xuat();
                                }
                            }
                        }
                        case 7 -> {
                            String path = "src/file_test/FilePhongbanketoan.txt";
                            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    String[] data = line.split(",");
                                    PhongBan pb = new PhongKeToan();
                                    pb.setMaPhong(data[0]);
                                    pb.setTenPhong(data[1]);
                                    pb.setKinhPhiHoatDong(Double.parseDouble(data[2]));
                                    pb.setNgayThanhLap(data[3]);
                                    pb.soLuong(Integer.parseInt(data[4].trim()));
                                    int n = Integer.parseInt(data[5].trim());
                                    for (int i = 0; i < n; i++) {
                                        String id = data[6 + i * 2];
                                        String name = data[6 + i * 2 + 1];
                                        pb.themNhanVien(id, name);
                                    }
                                    them_1_phongban(pb);
                                }
                                System.out.println("Đọc File Thành Công!!!");
                            } catch (IOException e) {
                                System.out.println("Lỗi Khi Đọc File!!!");
                            }
                        }
                        case 8 -> System.out.println("Đã Thoát!");
                        default -> System.out.println("Chức năng chưa hỗ trợ!");
                    }
                }
                case 4 -> {
                    System.out.println("Thoát chương trình.");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
    @Override
    public void menu() {
        System.out.println("\n------ Menu ------");
        System.out.println("1. Phòng Công Nghệ Thông Tin");
        System.out.println("2. Phòng Marketing");
        System.out.println("3. Phòng Kế Toán");
        System.out.println("4. Thoát ");
        System.out.println("--------------------");
    }
    @Override
    public void menu_chucnang() {
        System.out.println("\n------- Menu chức năng -------");
        System.out.println("1. Thêm Phòng Ban");
        System.out.println("2. Sửa Phòng Ban");
        System.out.println("3. Xuất Phòng Ban");
        System.out.println("4. In file TXT");
        System.out.println("5. Xóa Phòng Ban");
        System.out.println("6. Xuất Phòng Ban Đã Xóa");
        System.out.println("7. Đọc Từ File TXT");
        System.out.println("8. Thoát");
        System.out.println("---------------------------------");
    }
}
