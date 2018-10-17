/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienanhviet;

import java.util.Scanner;
import static tudienanhviet.DictionaryManagement.dic;
import static tudienanhviet.DictionaryManagement.dictionaryLookup;
import static tudienanhviet.DictionaryManagement.insertFromCommandline;
import static tudienanhviet.DictionaryManagement.insertFromFile;
import static tudienanhviet.DictionaryManagement.menu;

/**
 *
 * @author Huynh
 */
public class DictionaryCommandLine {
    public static void showAllWorlds() {
        System.out.println("----showAllWorlds-----");
        System.out.printf("%-5s%-25s%-35s%n", "No", "|English", "|Vietnamese");
        for (int i = 0; i < dic.arr.size(); i++) {
            System.out.printf("%-5d%-25s%-35s%n", i + 1, "|" + dic.arr.get(i).getWord_target(), "|" + dic.arr.get(i).getWord_explain());
        }
    }
    public static void dictionaryBasic() {
        System.out.println("----dictionaryBasic-----");
        int n = 0;
        do {
            System.out.println("0. Back");
            System.out.println("1. Insert from command line");
            System.out.println("2. Show all");            
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            switch(a){
                case 1:
                    insertFromCommandline();
                    break;
                case 2:
                    showAllWorlds();
                    break;
                default:
                    if(a!=0)
                    System.out.println("Nhap sai!");
            }            
        } while (n > 2);
        menu();
    }
    public static void dictionaryAdvanced() {
        System.out.println("----dictionaryAdvanced-----");
        int n = 0;
        do {
            System.out.println("0. Back");
            System.out.println("1. Insert from file");
            System.out.println("2. Show all");      
            System.out.println("3. look up");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            switch(a){
                case 1:
                    insertFromFile();
                    break;
                case 2:
                    showAllWorlds();
                    break;
                case 3:
                    dictionaryLookup();
                    break;
                default:
                    if(a!=0)
                    System.out.println("Nhap sai!");
            }            
        } while (n > 3);
        menu();
        
    }
    public static void dictionarySearcher(){
        System.out.println("----dictionarySearcher-----");
        System.out.println("Nhap tu muon tra: ");
        String a = new String();
        Scanner sc = new Scanner(System.in);
        a = sc.nextLine();
        System.out.printf("%-5s%-25s%-35s%n", "No", "|English", "|Vietnamese");
        int dem = 0;
        for (int i = 0; i < dic.arr.size(); i++) {
            if (dic.arr.get(i).getWord_target().contains(a)) {
                dem = 1;
                System.out.printf("%-5d%-25s%-35s%n", i + 1, "|" + dic.arr.get(i).getWord_target(), "|" + dic.arr.get(i).getWord_explain());
            }
        }
        if (dem == 0) {
            System.out.println("Khong tim thay!");
        }
    }
}
