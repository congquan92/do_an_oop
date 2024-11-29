package type;

import java.util.Scanner;

public abstract class NhanSu {
    private String id;
    private String name;
    private String phone;
    private String diaChi;
    private int namVaolam;
    private String heSothidua;
    public static Scanner sc = new Scanner(System.in);

    // Abstract methods
    abstract double bonusMoneyhesothidua();
    abstract double bonusChucvu();
    abstract double tienLuong();
    abstract double luongCoban();

    // Constructors
    public NhanSu() {
    }

    public NhanSu(String id, String name, String phone, String diaChi, int namVaolam) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.diaChi = diaChi;
        this.namVaolam = namVaolam;
    }

    // Getters and Setters
    public String getHeSothidua() {
        return heSothidua;
    }

    public void setHeSothidua(String heSothidua) {
        while (true) {
            if (heSothidua.matches("[A-F]")) {  // Kiểm tra hợp lệ sử dụng regex
                this.heSothidua = heSothidua;
                break;
            } else {
                System.out.println("Không hợp lệ! Hệ số thi đua từ A -> F. Nhập lại:");
                heSothidua = sc.nextLine();
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getNamVaolam() {
        return namVaolam;
    }

    public void setNamVaolam(int namVaolam) {
        this.namVaolam = namVaolam;
    }

    // Nhập thông tin nhân sự
    public void nhap() {
            System.out.println("Mã nhân sự: ");
            setId(sc.nextLine());
            System.out.println("Tên nhân sự: ");
            setName(sc.nextLine());
            System.out.println("Địa chỉ: ");
            setDiaChi(sc.nextLine());
            System.out.println("Số điện thoại: ");
            setPhone(sc.nextLine());

            while (true) {
                System.out.println("Năm vào làm: ");
                try {
                    setNamVaolam(Integer.parseInt(sc.nextLine()));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập năm hợp lệ.");
                }
            }

            System.out.println("Hệ số thi đua: ");
            setHeSothidua(sc.nextLine());
    }

    @Override
    public String toString() {
        return String.format(
                "ID : %-10s | Tên : %-15s | Địa chỉ : %-20s | Số điện thoại : %-12s | Năm vào làm : %d",
                this.getId(),
                this.getName(),
                this.getDiaChi(),
                this.getPhone(),
                this.getNamVaolam()
        );
    }

    public void xuat() {
        System.out.println(toString());
    }
}
