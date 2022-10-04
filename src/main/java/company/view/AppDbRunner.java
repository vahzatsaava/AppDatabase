package company.view;

import java.sql.Date;

public class AppDbRunner {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.mainMenu();
        /*
        Date date = new Date(3000);
        String dateS = "2000-12-10";
        date.setTime(300000);
        System.out.println(Date.valueOf(dateS));

         */
    }
}
