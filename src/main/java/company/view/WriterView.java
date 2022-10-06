package company.view;

import company.controller.WriterController;
import company.model.Label;
import company.model.Post;
import company.model.Writer;

import java.sql.Date;
import java.util.*;

public class WriterView implements View<Writer> {
    private final WriterController controller = new WriterController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Writer create() {
        System.out.println("Enter the name");
        String firstName = scanner.next();
        System.out.println("Enter second Name");
        String secondName = scanner.next();
        return controller.createWriter(new Writer(firstName, secondName));
    }

    @Override
    public List<Writer> getAll() {
        return controller.getWriters();
    }

    @Override
    public void delete() {
        System.out.println("Enter id number");
        int idNumber = scanner.nextInt();
        controller.delete(idNumber);
    }

    @Override
    public Writer update() {
        System.out.println("Enter id ");
        int id = scanner.nextInt();
        System.out.println("Enter firstName");
        String firstName = scanner.next();
        System.out.println("Enter lastName");
        String lastName = scanner.next();
        List<Post> listOfPost = createPost();

        return controller.upDate(new Writer(id, firstName, lastName, listOfPost));
    }

    @Override
    public Writer get() {
        System.out.println("Enter id number");
        int idNumber = scanner.nextInt();
        return controller.get(idNumber);
    }

    @Override
    public void close() {
        scanner.close();
    }

    private List<Label> createLabels() {
        System.out.println("How many labels you want to create");
        int countLabels = scanner.nextInt();
        List<Label> labels = new ArrayList<>();
        while (labels.size() < countLabels) {
            System.out.println("Enter labels name");
            String name = scanner.next();
            Label label = new Label(0, name);
            label.setId(generateIdForLabels(labels));
            labels.add(label);
        }
        return labels;
    }
    private List<Post> createPost() {
        System.out.println("How many posts you want to create");
        int countPost = scanner.nextInt();
        List<Post> labels = new ArrayList<>();
        while (labels.size() < countPost) {
            int id = scanner.nextInt();
            System.out.println("Enter post content");
            String post = scanner.next();
            System.out.println("Enter created time");
            java.sql.Date created = generateTime();
            System.out.println("Enter updated time");
            Date updated = generateTime();
            System.out.println("Enter writers id");
            int writerId = scanner.nextInt();
            List<Label> labels1 = createLabels();


            labels.add(new Post(id,post,created,updated,writerId,labels1));
        }
        return labels;
    }

    private Date generateTime() {
        String dataFormat = "";
        while (true) {
            System.out.println("Type years");
            String year = scanner.next();
            if (year.length() != 4) {
                System.out.println("Wrong values of hours");
                continue;
            }
            System.out.println("Type months");
            String month = scanner.next();
            if (month.length() != 2) {
                System.out.println("Wrong values of months");
                continue;
            }
            System.out.println("Type days");
            String day = scanner.next();
            if (day.length() != 2) {
                System.out.println("Wrong values of days");
                continue;
            }

            dataFormat = String.format("%s-%s-%s",year,month,day);
            break;
        }

        return Date.valueOf(dataFormat);
    }


    private int generateIdForLabels(List<Label> labels) {
        Label maxLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(maxLabel) ? 1 : maxLabel.getId() + 1;
    }
}
