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
        System.out.println("---------Welcome-------");
        System.out.println("1.Quan Ly Nhan Su");
        System.out.println("2.Quan Ly De An");
        System.out.println("3.Quan Phong Ban");
        System.out.println("4.Thoat");
    }
    public void display(){
        boolean flag = true;
        while(flag){
            menu();
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1 -> ns.play();
                case 2 -> da.play();
                case 3 -> pb.play();
                case 4 -> {
                    System.out.println("Bye Bye !!");
                    flag = false;
                }
            }
        }
    }
}
