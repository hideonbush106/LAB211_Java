/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Admin
 */
public class Dealer implements Comparable<Dealer> {

    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "D\\d{3}";
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";
    private String ID = "";
    private String name = "";
    private String addr = "";
    private String phone = "";
    private boolean continuing = false;

    public Dealer(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.continuing = continuing;
    }

}
