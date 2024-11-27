package QuanLy;

import Phongban.PhongBan;
import Phongban.PhongCNTT;
import Phongban.PhongKeToan;
import Phongban.PhongMK;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class QLPB {
    private PhongBan[] arrpb;
    private Scanner sc = new Scanner(System.in);

    public QLPB() {
        arrpb = new PhongBan[0];
    }

    public PhongBan timPhongBanTheoMa(String maPhong) {
        for (PhongBan phongBan : arrpb) {
            if (phongBan.getMaPhong().equalsIgnoreCase(maPhong)) {
                return phongBan;
            }
        }
        return null;
    }

    public void play() {
        boolean flag = true;
        while (flag) {
            menu_chucnang();
            int chose = Integer.parseInt(sc.nextLine());
            switch (chose) {
                case 1 -> { // Thêm phòng ban
                    System.out.print("Nhập số lượng phòng ban muốn thêm: ");
                    int soLuong = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < soLuong; i++) {
                        System.out.println("Nhập thông tin phòng ban thứ " + (i + 1) + ":");
                        menu();
                        System.out.print("Chọn loại phòng ban: ");
                        int loaiPhong = Integer.parseInt(sc.nextLine());
                        PhongBan phongBan = switch (loaiPhong) {
                            case 1 -> new PhongCNTT();
                            case 2 -> new PhongMK();
                            case 3 -> new PhongKeToan();
                            default -> {
                                System.out.println("Lựa chọn không hợp lệ! Mặc định chọn Phòng CNTT.");
                                yield new PhongCNTT();
                            }
                        };
                        phongBan.nhap();
                        arrpb = Arrays.copyOf(arrpb, arrpb.length + 1);
                        arrpb[arrpb.length - 1] = phongBan;
                        System.out.println("Thêm phòng ban thành công!");
                    }
                }
                case 2 -> { // Sửa phòng ban
                    System.out.print("Nhập mã phòng ban cần sửa: ");
                    String maPhong = sc.nextLine();
                    PhongBan phongBan = timPhongBanTheoMa(maPhong);
                    if (phongBan != null) {
                        System.out.println("Thông tin hiện tại của phòng ban:");
                        phongBan.xuat();

                        System.out.print("Bạn có muốn sửa thông tin phòng ban này không? (Y/N): ");
                        String luaChon = sc.nextLine().toUpperCase();
                        if (luaChon.equals("Y")) {
                            System.out.println("Nhập thông tin mới cho phòng ban:");
                            phongBan.nhap();
                            System.out.println("Sửa phòng ban thành công!");
                        } else {
                            System.out.println("Không có thay đổi nào được thực hiện.");
                        }
                    } else {
                        System.out.println("Không tìm thấy phòng ban với mã đã nhập.");
                    }
                }
                case 3 -> { // Xuất danh sách phòng ban
                    System.out.println("Tổng số phòng ban: " + arrpb.length);
                    if (arrpb.length == 0) {
                        System.out.println("Không có phòng ban nào trong danh sách.");
                    } else {
                        System.out.println("Danh sách phòng ban:");
                        for (PhongBan phongBan : arrpb) {
                            phongBan.xuat();
                            System.out.println("----------------------------");
                        }
                    }
                }
                case 4 -> { // Ghi danh sách phòng ban ra file TXT
                    String fileName = "D:\\Nam2\\TestWeb\\OOP\\src\\file\\DSPhongBan.txt";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (PhongBan phongBan : arrpb) {
                            writer.write(phongBan.toString());
                            writer.newLine();
                            writer.write("----------------------------");
                            writer.newLine();
                            writer.newLine();
                        }
                        System.out.println("Ghi danh sách phòng ban ra file thành công!");
                    } catch (IOException e) {
                        System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
                    }
                }
                case 5 -> { // Xóa phòng ban
                    System.out.print("Nhập mã phòng ban cần xóa: ");
                    String maPhong = sc.nextLine();
                    int index = -1; // Lưu vị trí
                    for (int i = 0; i < arrpb.length; i++) {
                        if (arrpb[i].getMaPhong().equalsIgnoreCase(maPhong)) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        System.out.print("Bạn có chắc chắn muốn xóa phòng ban này không? (Y/N): ");
                        String confirm = sc.nextLine().toUpperCase();
                        if (confirm.equals("Y")) {
                            // Dịch chuyển các phần tử phía sau vị trí cần xóa
                            for (int i = index; i < arrpb.length - 1; i++) {
                                arrpb[i] = arrpb[i + 1];
                            }
                            // Cập nhật lại mảng sau khi xóa
                            arrpb = Arrays.copyOf(arrpb, arrpb.length - 1);
                            System.out.println("Xóa phòng ban thành công!");
                        } else {
                            System.out.println("Phòng ban không được xóa.");
                        }
                    } else {
                        System.out.println("Không tìm thấy phòng ban với mã đã nhập.");
                    }
                }
                case 6 -> {
                    System.out.println("Thoát chương trình!");
                    flag = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        }
    }

    public void menu() {
        System.out.println("1. Phòng Công Nghệ Thông Tin");
        System.out.println("2. Phòng Marketing");
        System.out.println("3. Phòng Kế Toán");
    }
    public void menu_chucnang() {
        System.out.println("1. Thêm Phòng Ban");
        System.out.println("2. Sửa Phòng Ban");
        System.out.println("3. Xuất Phòng Ban");
        System.out.println("4. In file TXT");
        System.out.println("5. Xóa Phòng Ban");
        System.out.println("6. Thoát");
    }
}
