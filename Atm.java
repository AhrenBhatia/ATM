import java.util.*;
import java.util.Map;

public class Atm {
    public Map<String, Double> accounts = new HashMap<String, Double>();

    public void openAccount(String userID, double deposit) throws Exception {
        if (accounts.containsKey(userID)) {
            throw new Exception("Error: User already exists");
        } else {
            accounts.put(userID, deposit);
        }
    }

    public void closeAccount(String userID) throws Exception {
        if (accounts.get(userID) > 0) {
            throw new Exception("Error: Please withdraw all money first");
        } else {
            accounts.remove(userID);
        }
    }

    public double checkBalance(String userID) throws Exception {
        if (accounts.containsKey(userID)) {
            return accounts.get(userID);
        } else {
            throw new Exception("No account found");
        }
    }

    public double depositMoney(String userID, double amount) throws Exception {
        if (accounts.containsKey(userID)) {
            accounts.replace(userID, accounts.get(userID) + amount);
            return amount;
        } else {
            throw new Exception("You are broke lol");
        }
    }

    public double withdrawMoney(String userID, double amount) throws Exception {
        if (accounts.containsKey(userID)) {
            double holder = accounts.get(userID);
            if (holder > amount) {
                accounts.replace(userID, holder - amount);
                return holder - amount;
            } else {
                throw new Exception("You are broke lol");
            }
        } else {
            throw new Exception("No account found");
        }
    }

    public boolean transferMoney(String fromAccount, String toAccount, double amount) throws Exception {
        if (withdrawMoney(fromAccount, amount) > 0 && depositMoney(toAccount, amount) > 0) {
            return true;
        } else {
            return false;
        }
    }

}