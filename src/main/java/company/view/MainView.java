package company.view;


import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final WriterView writerView = new WriterView();
    private final PostView postView = new PostView();
    private final Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        System.out.println("Menu for Labels - 1");
        System.out.println("Menu for Writers - 2");
        System.out.println("Menu for Posts - 3");
        int value = scanner.nextInt();
        switch (value){
            case 1:
                callMenu(labelView);
                break;
            case 2:
                callMenu(writerView);
                break;
            case 3:
                callMenu(postView);
            default:
                System.out.println("Enter only this Numbers");
                break;
        }
    }

    private void callMenu(View view) {
        while (true) {
            info();
            int value = scanner.nextInt();
            if (6 <= value || value < 0) {
                break;
            }
            switch (value) {
                case 1:
                    System.out.println("All labels :" + view.getAll());
                    break;
                case 2:
                    System.out.println(view.create());
                    break;
                case 3:
                    view.delete();
                    break;
                case 4:
                    System.out.println(view.get());
                    break;
                case 5:
                    view.update();
                    break;
            }
        }
        view.close();
        scanner.close();
    }



    private void info() {
        System.out.println("1 - Get All Info");
        System.out.println("2 - Save ");
        System.out.println("3 - Delete ");
        System.out.println("4 - Get ");
        System.out.println("5 - Update Position");
        System.out.println("6 - Exit");

    }

}
