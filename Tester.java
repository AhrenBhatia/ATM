public class Tester {
    public static void main(String[] args) throws Exception {
        Atm tester = new Atm();
        tester.openAccount("Ahren", 100);
        tester.openAccount("Bari", 1000);
        tester.openAccount("Asher", 20);

        // closeAccount("Asher");
        tester.withdrawMoney("Asher", 20);
        tester.closeAccount("Asher");

        // withdrawMoney("Asher", 100);
        tester.depositMoney("Ahren", 11000);

        tester.transferMoney("Ahren", "Bari", 1000);
        // withdrawMoney("Ahren", 1100);
        // depositMoney("Bari", 1100);
        System.out.println(tester.checkBalance("Ahren"));
        System.out.println(tester.checkBalance("Bari"));

        tester.audit();
    }
}
