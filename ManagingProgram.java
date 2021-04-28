public class ManagingProgram {
    public static void main(String[] args) {
        // Add choice to menu
        Menu menu = new Menu();
        menu.add("Load data from file");
        menu.add("Create new account");
        menu.add("Withdraw money");
        menu.add("Deposit money in the bank");
        menu.add("Transfer money");
        menu.add("Remove an account");
        menu.add("Save information of bank account");
        menu.add("Save information of user");
        menu.add("Exit");
        
        // Managing program
        int choice;
        String id = null;
        AccountAction list = new AccountList();
        String fName1 = "bank.dat";
        String fName2 = "user.dat";
        
        do {
            System.out.println("-<>-<>-<>- BANKING MONEY MANAGER -<>-<>-<>-");
            choice = menu.getChoice();
            
            //User choices
            switch (choice) {
                case 1: list.readFile(fName1, fName2);
                        break;
                case 2: list.createAccount();
                        break;
                case 3: list.rut(id);
                        list.login(id);
                        break;
                case 4: list.login(id);
                        break;
                case 5: list.login(id);
                        break;
                case 6: list.login(id);
                        break;
                case 7: list.SaveBank(fName1);
                        break;
                case 8: list.SaveUser(fName2);
                        break;
                case 9: list.goodBye();
                        break;
                default: System.out.println("");
            }
        } while (choice != 9);
    }
        
}
