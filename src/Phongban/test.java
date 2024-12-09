package Phongban;

public class test {
    public static void main(String[] args) {
                PhongCNTT phongCNTT = new PhongCNTT();
//                PhongKeToan phongKeToan = new PhongKeToan();
//                PhongMK phongMK = new PhongMK();

                System.out.println("Nhập thông tin phòng CNTT:");
                phongCNTT.nhap();
//                System.out.println("\nNhập thông tin phòng Kế Toán:");
//                phongKeToan.nhap();
//                System.out.println("\nNhập thông tin phòng Marketing:");
//                phongMK.nhap();

              System.out.println("\nThông tin các phòng ban:");
              phongCNTT.xuat();
//              phongMK.xuat();
//              phongKeToan.xuat();
    }
}
