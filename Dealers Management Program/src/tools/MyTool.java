/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    } // Ham xac thuc chuoi

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*") && str.matches(".*[\\d]+.*") && str.matches(".*[\\W]+.*");
    } // Ham xac thuc mat khau

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    } // Ham phan tich chuoi ve date

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            String str = dF.format(date);
            return str;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    } // Ham format date ve chuoi

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    } // Ham phan tich boolean

    public static String readNonBlank(String message) { // Ham input
        String input = "";
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    } // Ham nhap

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.println(message + ": ");
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    } //Ham nhap theo bieu thuc chinh quy

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    } //Ham nhap boolean

    public static List<String> readLineFromFile(String filename) {
        File file = new File(filename);
        if (file.isFile() && file.exists()) {
            try {
                String s;
                List<String> list = new ArrayList();
                FileReader f = new FileReader(filename);
                BufferedReader bf = new BufferedReader(f);
                while ((s = bf.readLine()) != null) {
                    s = readNonBlank(s);
                    if (!s.isEmpty()) {
                        list.add(s);
                    }
                }
                f.close();
                bf.close();
                return list;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public static void writeFile(String filename, List list) {
        if (list != null && !list.isEmpty()) {
            try {
                FileWriter fw = new FileWriter(filename);
                PrintWriter pw = new PrintWriter(fw);
                for (Object item : list) {
                    pw.println(item);
                }
                fw.close();
                pw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Tests with phone number: "); // - OK
        System.out.println(validStr("012345678", "\\d{9}|\\d{11}")); //true
        System.out.println(validStr("0123455691", "\\d{9}|\\d{11}")); //false
        System.out.println(validStr("12345678", "\\d{9}|\\d{11}")); //false
        System.out.println("Tests with password: "); // - OK
        System.out.println(validPassword("qwerty", 8)); //false
        System.out.println(validPassword("qwertyABC", 8)); //false
        System.out.println(validPassword("12345678", 8)); //false
        System.out.println(validPassword("qbc123456", 8)); //false
        System.out.println(validPassword("qbc@123456", 8)); //true
        System.out.println("Tests with IDs:"); // - OK
        System.out.println(validStr("A0001", "D\\d{3}"));
        System.out.println(validStr("10001", "D\\d{3}"));
        System.out.println(validStr("D0001", "D\\d{3}"));
        System.out.println(validStr("D101", "D\\d{3}"));
        System.out.println("Tests with date format:"); // - OK
        Date d = parseDate("2022:12:07", "yyyy:MM:dd");
        System.out.println(d);
        System.out.println(dataToStr(d, "dd/MM/yyyy")); // - OK 
        d = parseDate("12/07/2022", "MM/dd/yyyy");
        System.out.println(d);
        d = parseDate("2022/07/12", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/29/02", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/30/02", "yyyy/dd/MM");
        System.out.println(d);
        d = parseDate("2000/40/16", "yyyy/dd/MM");
        System.out.println(d);
    }
}
