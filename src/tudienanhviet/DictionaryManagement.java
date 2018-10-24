/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudienanhviet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tudienanhviet.DictionaryCommandLine.dictionaryAdvanced;
import static tudienanhviet.DictionaryCommandLine.dictionaryBasic;
import static tudienanhviet.DictionaryCommandLine.dictionarySearcher;

/**
 *
 * @author Huynh
 */
public class DictionaryManagement {

    static Dictionary dic = new Dictionary();
//Nhập dữ liệu từ bàn phím
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
//tryền dữ liệu tờ file vào mảng
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
                word.setWord_explain(w[1]);
                dic.arr.add(word);


            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Thêm từ mới vào file
    public static void addfile(Word w) {
        try {
            File file = new File("dictioneries.txt");

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String line1 = "";
            while ((line = br.readLine()) != null) {
                line1 += line + "\n";
            }
            line1 += w.getWord_target() +"\t" + w.getWord_explain();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line1);
            bw.close();
            fw.close();
            dic.arr.add(w);

        } catch (IOException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void dictionaryExportToFile(){
        try {
            File file = new File("dictioneries.txt");
            String line = "";
                for(int i=0;i<dic.arr.size();i++) {
                    line +=  dic.arr.get(i).getWord_target() +"\t" + dic.arr.get(i).getWord_explain() + "\n";
                }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line);
            bw.close();
            fw.close();


        } catch (IOException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dictionaryLookup() {
        System.out.println("----dictionaryLookup-----");
        System.out.println("Nhap tu muon tra: ");
        String a = new String();
        Scanner sc = new Scanner(System.in);
        a = sc.nextLine();
        System.out.printf("%-5s%-25s%-35s%n", "No", "|English", "|Vietnamese");
        int dem = 0;
        for (int i = 0; i < dic.arr.size(); i++) {
            if (dic.arr.get(i).getWord_target().equals(a)) {
                dem = 1;
                System.out.printf("%-5d%-25s%-35s%n", i + 1, "|" + dic.arr.get(i).getWord_target(), "|" + dic.arr.get(i).getWord_explain());
            }
        }
        if (dem == 0) {
            System.out.println("Khong tim thay!");
        }

    }
    public static void menu() {
        System.out.println("----------Tu Điển-----------");
        int n = 0;
        do {
            System.out.println("1. Basic Dictionary");
            System.out.println("2. Advanced Dictionary");
            System.out.println("3. Searcher");
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
                case 3:
                    dictionarySearcher();
                    break;
                default:
                    if(n!=0)
                        System.out.println("Nhap sai!");
            }

        } while (n > 3);
    }

    public static void deleteword(Word w){
        dic.arr.remove(w);
        try {
            File file = new File("dictioneries.txt");

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String line1 = "";
            while ((line = br.readLine()) != null) {
                Word word = new Word();
                String[] wo = line.split("\\s", 2);
                word.setWord_target(wo[0]);
                word.setWord_explain(wo[1].trim());
                if(!w.getWord_target().equals( wo[0])){
                    line1 += line + "\n";
                }
            }


            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line1);
            bw.close();
            fw.close();


        } catch (IOException ex) {
            Logger.getLogger(DictionaryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public static void editword(Word word) {
        Word editword = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Sua Tieng Anh: ");
        editword.setWord_target(sc.nextLine());
        System.out.println("Sua Tieng Viet");
        editword.setWord_explain(sc.nextLine());
        dic.arr.add(editword);
        deleteword(word);

    }

    public static void main(String[] args) {
//        int m;
//
//        Scanner sr = new Scanner(System.in);

//        System.out.println("Nhan 1 de nhap tu dien: ");
//        System.out.println("Nhan 2 de xem tu dien: ");
//        m = sr.nextInt();
//        dictionaryBasic(m);
        //showAllWorlds();
//        menu();



//        Word w = new Word();
//        w.setWord_explain("6");
//        w.setWord_target("hello");
//        deleteword(w);
//
//        String s = "abc|a b v|abccd";
//        String[] wo = s.split("\\|", 3);
//        System.out.println(wo[0]);
//        System.out.println(wo[1]);
//        System.out.println(wo[2]);
//
        menu();


    }
}

