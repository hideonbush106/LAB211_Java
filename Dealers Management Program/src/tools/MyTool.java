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
    }
}
