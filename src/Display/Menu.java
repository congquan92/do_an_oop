package Display;

import QuanLy.QLDA;
import QuanLy.QLNS;
import QuanLy.QLPB;

import java.util.Scanner;

public class Menu implements display{
    Scanner sc = new Scanner(System.in);
    QLNS ns = new QLNS();
    QLDA da = new QLDA();
    QLPB pb = new QLPB();
    public void menu(){
        System.out.println("_________________________________");
        System.out.println("|____________Welcome____________|");
        System.out.println("|       1.Quản Lý Nhân Sự       |");
        System.out.println("|       2.Quản Lý Đề Án         |");
        System.out.println("|       3.Quản Phòng Ban        |");
        System.out.println("|       0.Thoát                 |");
        System.out.println("|_______________________________|");
        System.out.println("|_______________________________|");
    }
    public void display(){
        boolean flag = true;
        while(flag){
            menu();
            System.out.print("Nhâp [1-4] : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1 -> ns.play();
                case 2 -> da.play();
                case 3 -> pb.play();
                case 0 -> {
                    System.out.println(".____________________________.");
                    System.out.println("|    .__________________.    |");
                    System.out.println("|    | See You Again !! |    |");
                    System.out.println("|    |__________________|    |");
                    System.out.println("|____________________________|");
                    flag = false;
                }
                default -> System.out.println("\nKhông hợp lệ !!!!\n");
            }
        }
    }
}
