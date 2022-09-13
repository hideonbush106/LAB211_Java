/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author Admin
 */
public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        System.out.println("Please Login to System!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Your account name: ");
        String accName = sc.nextLine().trim().toUpperCase();
        System.out.print("Your password: ");
        String pwd = sc.nextLine();
        System.out.print("Your role: ");
        String role = sc.nextLine().trim().toUpperCase();
        return new Account(accName, pwd, role);
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;
        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account - Try again?");
            }
            if (!valid && !cont) {
                System.out.println("Good Bye!");
                System.exit(0);
            }
        } while (cont);
        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {
                "Add new dealer", "Search a dealer", "Remove a dealer",
                "Update a dealer", "Print all dealers", "Print continuing dealers",
                "Print UN-continuing dealers", "Write to file"};
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.initWithFile();

            int choice = 0;
            do {
                choice = mnu.getChoice("Managing Dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTool.readBool("Data changed. Write to file? ");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Good Bye!");
        }

    }

}
