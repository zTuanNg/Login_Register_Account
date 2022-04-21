import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService {


    // convert json -> list
    public List<Account> readFile(){
        Gson gson = new Gson();
        List<Account> lst = new ArrayList<>();
        try {
            FileReader reader = new FileReader("account.json");
            lst = gson.fromJson(reader,new TypeToken<List<Account>>(){}.getType());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lst;

    }

    // add Account
    public void addAccount(List<Account>lst, Account acc){
        lst.add(acc);
    }

    // add new Account to DB
    public void addDataToDB(List<Account>lst,Account acc){
        Gson gson = new Gson();
        lst.add(acc);
        String s = gson.toJson(lst);
        try {
            FileWriter fw = new FileWriter("account",false);
            fw.write(s);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // check exist account
    public boolean checkExistAcc(List<Account>lst, String userName){
        Account check = null;
       try{
           check = lst.stream().filter(a->a.getUserName().equals(userName)).findAny().get();
           if(check != null)
               return true;
           else
               return false;
       }
       catch (Exception e){
           return false;
       }
    }

    // check Exist email
    public boolean checkExistEmail(List<Account>lst, String userName){

        Account check = null;
        try{
            check = lst.stream().filter(a->a.getEmail().equals(userName)).findAny().get();
            if(check != null)
                return true;
            else
                return false;
        }
        catch (Exception e){
            return false;
        }
    }

    // change username
    public void changeUsername(Account acc, String newAcc){
        acc.setUserName(newAcc);
    }

    // change email
    public void changeEmail(Account acc, String newEmail){
        acc.setEmail(newEmail);
    }

    // change password
    public void changePassword(Account acc, String newPass){
        acc.setPassword(newPass);
    }

    // check valid email
    public boolean checkValidEmail(String email){
        return Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*", email);
    }

    // check valid password
    public boolean checkValidPassword(String password){


        return true;

    }



}
