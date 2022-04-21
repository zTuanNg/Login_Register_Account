import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AccountService sv = new AccountService();
        List<Account> lst = sv.readFile();

       Account a1 = new Account("sds","dfsd","dfsdf");

       sv.addAccount(lst,a1);

        Gson gson = new Gson();

        String s = gson.toJson(lst);
//        System.out.println(s.length());

        try {
            FileWriter fw = new FileWriter("acc.json",false);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
