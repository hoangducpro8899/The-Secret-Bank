public class ManagingProgram {
    public static void main(String[] args) {
        // Add choice to menu
        Menu menu = new Menu();
        menu.add("Create new account");
        menu.add("Withdraw money");
        menu.add("Deposit money in the bank");
        menu.add("Transfer money");
        menu.add("Remove an account");
        menu.add("Exit");
        
        // Managing program
        int choice;
        AccountAction list = new Account() {
            @Override
            public void createAccount() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        do {
            System.out.println("-<>-<>-<>- BANKING MONEY MANAGER -<>-<>-<>-");
            choice = menu.getChoice();
            
            //User choices
            switch (choice) {
                case 1: list.createAccount();
                        break;
                case 2: 
                        break;
                case 3: 
                        break;
                case 4: 
                        break;
                case 5: 
                        break;
                case 6: list.goodBye();
                        break;
            }
        } while (choice != 6);
    }
        
}