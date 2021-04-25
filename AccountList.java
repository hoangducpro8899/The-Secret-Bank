import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

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
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    //Find whether account ID existed or not
    public int find(String code) {
        if (list.isEmpty()) {
            System.out.println("--> The list is empty !!!\n");
            return -1;
        }
        
        for (int i = 0; i < list.size(); i++) 
            if (list.get(i).getAccountID().equalsIgnoreCase(code))
                return i;
        return -1;            
    }
    
    //Create account
    @Override
    public void createAccount() {
        String newId, newName, newPassword, checkPass;
        int pos;
        System.out.println("---------- Create new account ----------");
        newId = CheckConstrain.getID("Enter account ID: ", "--> Account ID is required !!!\n", "--> Wrong ID format (2 characters + 4 integers)");
        newName = CheckConstrain.getName("Enter account name: ", "--> Account name is required !!!\n", "--> Name can not have integers");
        
        do {
            newPassword = CheckConstrain.getPassword("Enter password: ", "--> Password is required !!!\n", "--> Password must contain at least "
                                    + "6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character");
            checkPass = CheckConstrain.getPassword("Re-enter password: ", "--> Password is required !!!\n", "--> Password must contain at least "
                                    + "6 characters, including uppercase letters, lower case letters, numeric characters and 1 special character");
            if (!newPassword.equalsIgnoreCase(checkPass)) {
                System.out.println("--> ERROR: Passwords do not match\n");
                System.out.println("--> Please enter password again:");
            }
        } while (!newPassword.equalsIgnoreCase(checkPass));
    }
    
    
}
