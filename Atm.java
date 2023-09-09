import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map;

public class Atm {
    public static Map<String, Double> accounts = new HashMap<String, Double>();

    public static void openAccount(String userID, double deposit) throws Exception {
        if (accounts.containsKey(userID)) {
            throw new Exception("Error: User already exists");
        } else {
            accounts.put(userID, deposit);
        }
    }

    public static void closeAccount(String userID) throws Exception {
        if (accounts.get(userID) > 0) {
            throw new Exception("Error: Please withdraw all money first");
        } else {
            accounts.remove(userID);
        }
    }

    public static double checkBalance(String userID) throws Exception {
        if (accounts.containsKey(userID)) {
            return accounts.get(userID);
        } else {
            throw new Exception("No account found");
        }
    }

    public static double depositMoney(String userID, double amount) throws Exception {
        if (accounts.containsKey(userID)) {
            accounts.replace(userID, accounts.get(userID) + amount);
            return amount;
        } else {
            throw new Exception("You are broke lol");
        }
    }

    public static double withdrawMoney(String userID, double amount) throws Exception {
        if (accounts.containsKey(userID)) {
            double holder = accounts.get(userID);
            if (holder >= amount) {
                accounts.replace(userID, holder - amount);
                return holder - amount;
            } else {
                throw new Exception("You are broke lol");
            }
        } else {
            throw new Exception("No account found");
        }
    }

    public static boolean transferMoney(String fromAccount, String toAccount, double amount) throws Exception {
        if (withdrawMoney(fromAccount, amount) > 0 && depositMoney(toAccount, amount) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void audit() throws FileNotFoundException {
        File file = new File("AccountAudit.txt");
        if (file.exists()) {
            file.delete();
        }
        PrintWriter pw = new PrintWriter("AccountAudit.txt");
        for (String account : accounts.keySet()) {
            pw.write("Username: " + account + "    Amount: " + accounts.get(account) + "\n");

        }

        pw.close();
    }

    public static void main(String[] args) throws Exception {
        openAccount("Ahren", 100);
        openAccount("Bari", 1000);
        openAccount("Asher", 20);

        // closeAccount("Asher");
        withdrawMoney("Asher", 20);
        closeAccount("Asher");

        // withdrawMoney("Asher", 100);
        depositMoney("Ahren", 11000);

        transferMoney("Ahren", "Bari", 1000);
        // withdrawMoney("Ahren", 1100);
        // depositMoney("Bari", 1100);
        System.out.println(checkBalance("Ahren"));
        System.out.println(checkBalance("Bari"));

        audit();
    }

}