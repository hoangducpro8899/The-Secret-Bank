import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AccountList extends Account {
    private ArrayList<Account> list = new ArrayList<>();
    Scanner sc = new Scanner (System.in);
    
    public AccountList() {
        super();
    }
    
    public static String getMd5(String input) 
    { 
        try { 
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    //Find whether account ID existed or not
    public int findID(String code) {
        for (int i = 0; i < list.size(); i++) 
            if (list.get(i).getAccountID().equalsIgnoreCase(code))
                return i;
        return -1;            
    }
    
    //Find whether account name existed or not
    public int findName(String name) {
        for (int i = 0; i < list.size(); i++) 
            if (list.get(i).getAccountName().equalsIgnoreCase(name))
                return i;
        return -1;            
    }
    
    //Create account
    @Override
    public void createAccount() {
        String newId, newName, newPassword, checkPass, password;
        double money = 0;
        int pos = 0;
        System.out.println("\n---------- CREATE NEW ACCOUNT ----------");
        do {
            newId = CheckConstrain.getID("Enter account ID: ", "--> Account ID is required !!!\n", 
                                        "--> Wrong ID format: SE + 4 integers (Example: SE0001)");
            pos = findID(newId);
            if (pos >= 0)
                System.out.println("--> Account ID already exists");
        }while (pos >= 0);
        newName = CheckConstrain.getName("Enter account name: ", "--> Account name is required !!!\n", 
                                        "--> Name can not have integers");
        
        do {
            newPassword = CheckConstrain.getPassword("Enter password: ", "--> Password is required !!!\n", 
                                                "--> Password must contain at least 6 characters, including uppercase "
                                                + "letters, lower case letters, numeric characters and 1 special character");
            checkPass = CheckConstrain.getPassword("Re-enter password: ", "--> Password is required !!!\n", 
                                                "--> Password must contain at least 6 characters, including uppercase "
                                                + "letters, lower case letters, numeric characters and 1 special character");
            if (!newPassword.equalsIgnoreCase(checkPass)) {
                System.out.println("--> ERROR: Passwords do not match\n");
                System.out.println("--> Please enter password again");
            }
        } while (!newPassword.equalsIgnoreCase(checkPass));
        password = getMd5(checkPass);
        
            list.add(new Account(newId, newName, password, money) {
            @Override
            public void createAccount() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String login(String id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


            @Override
            public void SaveBank(String fName) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void SaveUser(String fName) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void readFile(String fName1, String fName2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void rut(String id) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        System.out.println("--> Create new account successful !!!\n");
    }
    
    //Find whether account ID existed or not
    public int checkInfor(String input1, String input2) {
        for (Account xxx: list) 
            if (xxx.getAccountID().equalsIgnoreCase(input1) && xxx.getPassword().equalsIgnoreCase(input2)) {
                return 100;
            }
        return -1;
    }
    
    //Login account
    @Override
    public String login(String id) {
        int time = 0;
        String pass;
        if (list.size()==0) {
            System.out.println("--> The account list is empty !!!\n");
        }
        
        do {
            System.out.println("\n---------- LOGIN ACCOUNT ----------");
            do {
                System.out.print("Enter account ID: ");
                id = sc.nextLine();
                if (id.isEmpty())
                    System.out.println("--> Please fill in the blank");
            } while (id.isEmpty());
            
            do {
                System.out.print("Enter password: ");
                pass = sc.nextLine();
                if (pass.isEmpty())
                    System.out.println("--> Please fill in the blank");
            } while(pass.isEmpty());
            
            if (checkInfor(id, pass) == 100)
                System.out.println("--> Login successful\n");
            else {
                System.out.println("--> User account is not found. Please enter again !!!\n");
                time = time + 1;
            }
        } while(time <= 3 || checkInfor(id, pass) == 100);
        return id;
    }
    
    //Withdraw money from the bank
    public void withdraw(String id) {
        double moneyOut;
        for (Account xxx: list) {
            if (xxx.getAccountID().equalsIgnoreCase(id)) {
                System.out.print("Enter the amount you want to withdraw: ");
                moneyOut = sc.nextDouble();
                if (xxx.getMoney() < moneyOut) {
                    System.out.println("--> The balance in the account is not enough");
                } else {
                    xxx.setMoney(xxx.getMoney() - moneyOut);
                    System.out.println("WithDraw successfully!");
                }
                
            }
        }
    }
    
    
    @Override
    public void rut(String id){
        id=login(id);
        withdraw(id);
        
        
        
        
    }
    
    //Save bank account (ID, Name, Money)
    @Override
    public void SaveBank(String fName) {
        if (list.size()==0) {
            System.out.println("--> The account list is empty !!!\n");
            return;
        }
          
        try {
            File f = new File("bank.dat");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Account xxx: list){
                bw.write(xxx.accountID + ", " + xxx.accountName + ", " + xxx.money + "\n") ;
            }
            
            bw.close();
            fw.close();
            
            System.out.println("--> Save to file successfull !!!\n");
        } catch(Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    //Save user information (ID, Name, Password)
    @Override
    public void SaveUser(String fName) {
        if (list.size()==0) {
            System.out.println("--> The account list is empty !!!\n");
            return;
        }
          
        try {
            File f = new File("user.dat");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Account xxx: list){
                bw.write(xxx.accountID + ", " + xxx.accountName + ", " + xxx.password + "\n") ;
            }
            
            bw.close();
            fw.close();
            
            System.out.println("--> Save to file successfull !!!\n");
        } catch(Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    //Read from file and load to list
    public void readFile(String fName1, String fName2) {
        try {
            list.clear();
            File f = new File(fName1);
            if (!f.exists()) 
                return;
            
            FileReader fr = new FileReader(fName1);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            
            while ((details=bf.readLine())!= null) {
                StringTokenizer stk = new StringTokenizer(details,", ");
                String newId = stk.nextToken();
                String newName = stk.nextToken();
                Double newMoney = Double.parseDouble(stk.nextToken());
                
                FileReader fb = new FileReader(fName2);
                BufferedReader br = new BufferedReader(fb);
                while (br.ready()) {
                    StringTokenizer stk2 = new StringTokenizer(details,", ");
                    String newId2 = stk2.nextToken();
                    String newName2 = stk2.nextToken();
                    String newPassword = stk2.nextToken();
                    
                    if (newId.equalsIgnoreCase(newId2))
                        list.add(new Account(newId, newName, newPassword, newMoney) {
                        @Override
                        public void createAccount() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public String login(String id) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }


                        @Override
                        public void SaveBank(String fName) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void SaveUser(String fName) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void readFile(String fName1, String fName2) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void rut(String id) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                }
            
            }
            bf.close();;
            fr.close();
        } catch(Exception e) {
           System.out.println("ERROR: " + e);
        }
    }
    
    
    
}
