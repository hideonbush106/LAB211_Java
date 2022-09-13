/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import mng.LogIn;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author Admin
 */
public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for (String line : lines) {
            this.add(new Dealer(line));
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList resultList = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == true) {
                resultList.add(d);
            }
        }
        return resultList;
    }

    public DealerList getUncontinuingList() {
        DealerList resultList = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == false) {
                resultList.add(d);
            }
        }
        return resultList;
    }

    private int searchDealer(String ID) {
        ID = ID.toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        System.out.print("Dealer's ID needs searching: ");
        Scanner sc = new Scanner(System.in);
        String ID = sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("ID NOT FOUND!!");
        } else {
            System.out.println(this.get(pos));
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readPattern("ID of new dealer [D000]", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer");
        phone = MyTool.readPattern("Phone number", Dealer.PHONE_FORMAT);
        continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        System.out.println("Dealer's ID needs removing: ");
        Scanner sc = new Scanner(System.in);
        String ID = sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("NOT FOUND!!");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Dealer Removed!");
            changed = true;
        }
    }

    public void updateDealer() {
        System.out.print("Dealer's ID neeads updating: ");
        String ID = MyTool.SC.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " Not Found!");
        } else {
            Dealer d = this.get(pos);

            String newName = ""; //Update name
            System.out.println("New name, ENTER for omitting: ");
            newName = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }

            String newAddr = "";
            System.out.println("New address, ENTER for omitting: ");
            newAddr = MyTool.SC.nextLine().trim().toUpperCase();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }

            String newPhone = "";
            System.out.println("New phone, ENTER for omitting: ");
            newPhone = MyTool.SC.nextLine().trim();
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            for (Dealer thi : this) {
                System.out.println(thi);
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUncontinuingList().printAllDealers();
    }

    public void writeDealerToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
