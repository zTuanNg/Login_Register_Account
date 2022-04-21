import java.util.List;
import java.util.Scanner;

public class AccountController {

    public void run() {

        Scanner sc = new Scanner(System.in);
        AccountService service = new AccountService();

        boolean flag = true;
        boolean switchToLogin = false;
        int choice = 0;

        while (flag) {

            // Refresh db
            List<Account> db = service.readFile();

            if (!switchToLogin) {
                form();
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } else {
                choice = 1;
                switchToLogin = false;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter Username: ");
                    String username = sc.nextLine();
                    while (!service.checkExistAcc(db, username)) {
                        System.out.println("Username does not exist , Try again");
                        System.out.println("Enter Username: ");
                        username = sc.nextLine();
                    }

                    // if valid username => get Object to check password
                    Account acc = service.getAccount(db, username);

                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();

                    if (service.checkPassword(acc, password)) {
                        System.out.printf("Welcome %s , Please choice this option: \n", acc.getUserName());
                        settingMenu();

                        System.out.println("Enter your choice: ");
                        int subChoice = Integer.parseInt(sc.nextLine());
                        if (subChoice == 1) {
                            System.out.println("Enter new Username: ");
                            acc.setUserName(sc.nextLine());
                            System.out.println(acc);
                        }
                        if (subChoice == 2) {
                            System.out.println("Enter new Email: ");
                            String email = sc.nextLine();
                            while(!service.checkValidPassword(email)){
                                System.out.println("Email invalid, Try again");
                                System.out.println("Enter new Email: ");
                                email = sc.nextLine();
                            }
                            acc.setEmail(email);
                            System.out.println(acc);
                        }
                        if (subChoice == 3) {
                            System.out.println("Enter new Password: ");
                            password = sc.nextLine();
                            while(!service.checkValidPassword(password)){
                                System.out.println("Password invalid, Try again");
                                System.out.println("Enter new Password: ");
                                password = sc.nextLine();
                            }
                            acc.setPassword(password);
                            System.out.println(acc);
                        }
                        if (subChoice == 4) {
                            break;
                        }
                        if (subChoice == 0) {
                            System.exit(0);

                        }

                    } else {
                        loginMenu();
                        System.out.println("Enter your choice: ");
                        int subChoice = Integer.parseInt(sc.nextLine());
                        if (subChoice == 1) {
                            switchToLogin = true;
//                            choice = 1;
                            break;
                        }
                        if (subChoice == 2) {
                            System.out.println("Enter email: ");
                            String email = sc.nextLine();
                            if (service.checkExistEmail(db, email)) {
                                System.out.println("Enter new password: ");
                                acc.setPassword(sc.nextLine());
                                System.out.println(acc);
                                switchToLogin = true;
                                break;
                            } else {
                                System.out.println("This account does not exist");
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    // Case create new account
                    System.out.println("Enter Username: ");
                    username = sc.nextLine();
                    while (service.checkExistAcc(db, username)) {
                        System.out.println("Username already exists, Try again");
                        System.out.println("Enter Username: ");
                        username = sc.nextLine();

                    }

                    System.out.println("Enter Email: ");
                    String email = sc.nextLine();
                    while (service.checkExistAcc(db, email)) {
                        System.out.println("Email already exists, Try again");
                        System.out.println("Enter email: ");
                        email = sc.nextLine();

                    }

                    System.out.println("Enter Password: ");
                    password = sc.nextLine();
                    while (!service.checkValidPassword(password)) {
                        System.out.println("Password invalid, Try agian");
                        System.out.println("Enter Password: ");
                        password = sc.nextLine();
                    }

                    // After create account successful -> add to DB
                    service.addDataToDB(db, new Account(username,email,password));
                    break;
                default:
                    System.out.println("Try again");
                    break;


            }

        }

    }

        public static void form() {
            System.out.println("Choice this option: ");
            System.out.println("1 - Log in");
            System.out.println("2 - Sign up");
        }

        public static void loginMenu() {
            System.out.println("1 - Log in again");
            System.out.println("2 - Forgot pass word");
        }

        public static void settingMenu() {
            System.out.println("1 - Change Username");
            System.out.println("2 - Change Email");
            System.out.println("3 - Change password");
            System.out.println("4 - Log out");
            System.out.println("0 - Exit");
        }

}
