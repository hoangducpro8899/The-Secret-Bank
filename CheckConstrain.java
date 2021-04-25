import java.util.Scanner;

public class CheckConstrain {
    static Scanner sc = new Scanner (System.in);
    
    //Check whether user enter name correct or not
    public static String getName(String msg, String error1, String error2) {
        String newName;
        int check = 0;
        System.out.println(msg);
        do {
            newName = sc.nextLine();
            if (newName.isEmpty()) {
                check = 0;
                System.out.println(error1);
            } else
                for (int i=0; i<newName.length(); i++) {
                    if (newName.charAt(i)>= '0' && newName.charAt(i)<= '9') {   //charAt(i): return char value at position i
                        check = 0;                                              //if name has a number -> check=0
                        System.out.println(error2);
                    } else
                        check = 1;
                }
        } while (check == 0);
        return newName;
    }
    
    //Check whether user enter ID correct or not
    public static String getID(String msg, String error1, String error2) {
        String newId = new String();
        int check = 0;
        String pattern = "[a-zA-Z]{2} [0-9]{4}";
        do {                          //while(true): the loop runs infinitely
            try {
                System.out.println(msg);
                newId = sc.nextLine();
                if (newId.isEmpty()) {
                    check = 0;
                    System.out.println(error1);
                } else {
                    if (!newId.matches(pattern)) {
                        check = 0;
                        throw new Exception();
                    }
                    else
                        check =  1;
                }
            } catch (Exception e) {
                System.out.println(error2);
            }
        } while (check == 0);
        return newId.toUpperCase();
    }
    
    //Check whether user enter answer correct or not
    public static String getAnswer(String msg, String error) {
        String answer = new String();
        int check = 0;
        do {
            try {
                System.out.println(msg);
                answer = sc.nextLine();
                if (answer.isEmpty()) {
                    check = 0;
                    System.out.println(error);
                }
                else {
                    if (!(answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))) {
                        check = 0;
                        System.out.println(error);
                    }
                    else
                        check = 1;
                }
            } catch (Exception e) {
                System.out.println(error);
            }
        } while (check == 0);
        return answer.trim();
    }
    
    //Check whether user enter password correct or not
    public static String getPassword(String msg, String error1, String error2) {
        String password = new String();
        int check = 0;
        String pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,20})";   //(...) group of characters inside
        do {                                                                        // . Matches any character
            try {                                                                   // * Appear 0 or more times
                System.out.println(msg);                                            // ? Appear 0 or 1 time
                password = sc.nextLine();
                if (password.isEmpty()) {
                    check = 0;
                    System.out.println(error1);
                } else {
                    if (!password.matches(pattern)) {
                        check = 0;
                        throw new Exception();
                    }
                    else
                        check =  1;
                }
            } catch (Exception e) {
                System.out.println(error2);
            }
        } while (check == 0);
        return password.replaceAll("\\s+","");                                      // Remove blank before and after password
    }
    
}
