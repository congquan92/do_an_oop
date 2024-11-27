package QuanLy;

import dean.DeAn;
import dean.DeAnLon;
import dean.DeAnNho;
import dean.DeAnVua;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class QLDA {
    private DeAn[] arrda;
    private Scanner sc = new Scanner(System.in);
    public QLDA() {
        this.arrda = new DeAn[0];
    }
    public DeAn timDeAnTheoID(String id) {
        for (DeAn deAn : arrda) {
            if (deAn.getId().equalsIgnoreCase(id)) {
                return deAn;
            }
        }
        return null;
    }

    public void play(){
        QLNS qlns;
        boolean flag = true;
        while(flag){
            menu();
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose){
                case 1 -> {
                    System.out.print("Nhập số lượng đề án muốn thêm: ");
                    int soLuong = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < soLuong; i++) {
                        System.out.println("Nhập thông tin đề án thứ " + (i + 1) + ":");
                        System.out.println("Chọn loại đề án muốn thêm:");
                        System.out.println("1. Đề án lớn");
                        System.out.println("2. Đề án vừa");
                        System.out.println("3. Đề án nhỏ");
                        System.out.print("Lựa chọn của bạn: ");
                        int loaiDeAn = Integer.parseInt(sc.nextLine());
                        DeAn deAn = switch (loaiDeAn) {
                            case 1 -> new DeAnLon();
                            case 2 -> new DeAnVua();
                            case 3 -> new DeAnNho();
                            default -> {
                                System.out.println("Lựa chọn không hợp lệ! Mặc định chọn Đề án lớn.");
                                yield new DeAnLon();
                            }
                        };
                        deAn.nhap();
                        arrda = Arrays.copyOf(arrda, arrda.length + 1);
                        arrda[arrda.length - 1] = deAn;
                        System.out.println("Thêm đề án thành công!");
                    }
                }
                case 2 -> { // Sửa đề án
                    System.out.print("Nhập ID đề án cần sửa: ");
                    String id = sc.nextLine();
                    DeAn deAn = timDeAnTheoID(id);
                    if (deAn != null) {
                        System.out.println("Thông tin hiện tại của đề án:");
                        deAn.xuat();

                        System.out.print("Bạn có muốn sửa thông tin đề án này không? (Y/N): ");
                        String luaChon = sc.nextLine().toUpperCase();
                        if (luaChon.equals("Y")) {
                            System.out.println("Nhập thông tin mới cho đề án:");
                            deAn.nhap();
                            System.out.println("Sửa đề án thành công!");
                        } else {
                            System.out.println("Không có thay đổi nào được thực hiện.");
                        }
                    } else {
                        System.out.println("Không tìm thấy đề án với ID đã nhập.");
                    }
                }
                case 3 -> { // Xóa đề án
                    System.out.print("Nhập ID đề án cần xóa: ");
                    String id = sc.nextLine();
                    int index = -1;//luu vi tri
                    for (int i = 0; i < arrda.length; i++) {
                        if (arrda[i].getId().equalsIgnoreCase(id)) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        System.out.print("Bạn có chắc chắn muốn xóa đề án này không? (Y/N): ");
                        String confirm = sc.nextLine().toUpperCase();
                        if (confirm.equals("Y")) {
                            // Dịch chuyển các phần tử phía sau vị trí cần xóa
                            for (int i = index; i < arrda.length - 1; i++) {
                                arrda[i] = arrda[i + 1];
                            }
                            // Cập nhật lại mảng sau khi xóa
                            arrda = Arrays.copyOf(arrda, arrda.length - 1);
                            System.out.println("Xóa đề án thành công!");
                        } else {
                            System.out.println("Đề án không được xóa.");
                        }
                    } else {
                        System.out.println("Không tìm thấy đề án với ID đã nhập.");
                    }
                }
                case 4 -> { // Xuất danh sách đề án
                    System.out.println("Tổng số đề án: " + arrda.length);
                    if (arrda.length == 0) {
                        System.out.println("Không có đề án nào trong danh sách.");
                    } else {
                        System.out.println("Danh sách đề án:");
                        for (DeAn deAn : arrda) {
                            deAn.xuat();
                            System.out.println("----------------------------");
                        }
                    }
                }
                case 5 -> { // Ghi danh sách đề án ra file TXT
                    String fileName = "D:\\Nam2\\TestWeb\\OOP\\src\\file\\DSdean.txt";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (DeAn deAn : arrda) {
                            writer.write(deAn.toString());
                            writer.newLine();
                            writer.write("----------------------------");
                            writer.newLine();
                            writer.newLine();
                        }
                        System.out.println("Ghi danh sách đề án ra file thành công!");
                    } catch (IOException e) {
                        System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
                    }
                }
                case 6 -> { // Thoát chương trình
                    System.out.println("Thoát chương trình!");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

        }
    }
    public void menu_luachon_dean(){
        System.out.println("Chọn loại đề án muốn thêm:");
        System.out.println("1. Đề án lớn");
        System.out.println("2. Đề án vừa");
        System.out.println("3. Đề án nhỏ");
    }
    public void menu(){
        System.out.println("1.Them De An");
        System.out.println("2.Sua De An");
        System.out.println("3.Xoa de An");
        System.out.println("4.Xuat De An");
        System.out.println("5.In File txt");
        System.out.println("6.Thoat");
    }
    public void menu_chon_ns(){
        System.out.println("1.Giam Doc");
        System.out.println("2.Truong Phong");
        System.out.println("3.Nhan Vien");
    }


}

