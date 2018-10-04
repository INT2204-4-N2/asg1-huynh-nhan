/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienanhviet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh
 */
public class DictionaryManagement {

    static Dictionary dic = new Dictionary();

    public static void insertFromCommandline() {
        int n;
        System.out.println("So tu moi can them");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            Word word = new Word();
            System.out.println("Tieng Anh: ");
            word.setWord_target(sc.nextLine());
            System.out.println("Tieng Viet");
            word.setWord_explain(sc.nextLine());
            dic.arr.add(word);
        }
    }

    public static void showAllWorlds() {
        System.out.printf("%-5s%-25s%-35s%n", "No", "|English", "|Vietnamese");
        for (int i = 0; i < dic.arr.size(); i++) {
            System.out.printf("%-5d%-25s%-35s%n", i + 1, "|" + dic.arr.get(i).getWord_target(), "|" + dic.arr.get(i).getWord_explain());
        }
    }

    public static void dictionaryBasic() {
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
                    System.out.println("Nhap sai!");
            }            
        } while (n > 2);
        menu();
    }

    public static void insertFromFile() {
        File file = new File("dictioneries.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                Word word = new Word();
                String[] w = line.split("\\s", 2);
                word.setWord_target(w[0]);
                word.setWord_explain(w[1].trim());
                dic.arr.add(word);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dictionaryLookup() {
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

    public static void dictionaryAdvanced() {
        
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
                    System.out.println("Nhap sai!");
            }            
        } while (n > 3);
        menu();
        
    }

    public static void menu() {
        int n = 0;
        do {
            System.out.println("1. Basic Dictionary");
            System.out.println("2. Advanced Dictionary");
            System.out.println("0. Exit");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            switch (n) {
                case 1:
                    dictionaryBasic();
                    break;
                case 2:
                    dictionaryAdvanced();
                    break;
                default:
                    System.out.println("Nhap sai!");
            }

        } while (n > 2);
    }

    public static void main(String[] args) {
        int m;

        Scanner sr = new Scanner(System.in);
        

//        System.out.println("Nhan 1 de nhap tu dien: ");
//        System.out.println("Nhan 2 de xem tu dien: ");
//        m = sr.nextInt();
//        dictionaryBasic(m);
        //showAllWorlds();
        menu();

    }
}
