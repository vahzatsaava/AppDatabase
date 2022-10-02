package company.view;

import company.controller.PostController;
import company.model.Label;
import company.model.Post;
import liquibase.integration.ant.DropAllTask;

import javax.xml.stream.events.Characters;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.*;

public class PostView implements View<Post> {
    private final PostController controller = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Post create() {
        System.out.println("Type the content ");
        String content = scanner.next();
        System.out.println("Type time start");
        Timestamp start = generateTime();
        System.out.println("Type time finish");
        Timestamp finish = generateTime();
        List<Label> labels = generateLabels();
        return controller.createPost(new Post(content, start, finish, labels));
    }

    @Override
    public List<Post> getAll() {
        System.out.println("get all info");
        return controller.getPosts();
    }

    @Override
    public void delete() {
        System.out.println("Type number ID");
        int id = scanner.nextInt();
        controller.deletePostFromDb(id);
    }

    @Override
    public Post update() {
        System.out.println("Type id ");
        int id = scanner.nextInt();
        System.out.println("Type the content");
        String content = scanner.next();
        System.out.println("Type time start");
        Timestamp start = generateTime();
        System.out.println("Type time finish");
        Timestamp finish = generateTime();
        List<Label> labels = generateLabels();
        return controller.updatePost(new Post(id, content, start, finish, labels));
    }

    @Override
    public Post get() {
        System.out.println("Enter id");
        int id = scanner.nextInt();
        return controller.getPost(id);
    }

    @Override
    public void close() {
        scanner.close();
    }

    private Timestamp generateTime() {
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
            System.out.println("Type hours");
            String hour = scanner.next();

            if (hour.length() != 2 || !Character.isDigit(hour.charAt(0)) || !Character.isDigit(hour.charAt(1))) {
                System.out.println("Wrong values of hours");
                continue;
            }
            System.out.println("Type minutes");
            String minutes = scanner.next();
            if (minutes.length() != 2 || !Character.isDigit(minutes.charAt(0)) || !Character.isDigit(minutes.charAt(1))) {
                System.out.println("Wrong values of minutes");
                continue;
            }
            System.out.println("Type seconds");
            String seconds = scanner.next();
            if (seconds.length() != 2 || !Character.isDigit(seconds.charAt(0)) || !Character.isDigit(seconds.charAt(1))) {
                System.out.println("Wrong values of seconds");
                continue;
            }

            dataFormat = String.format("%s-%s-%s %s:%s:%s",year,month,day, hour, minutes, seconds);
            break;
        }

        return Timestamp.valueOf(dataFormat);
    }

    private List<Label> generateLabels() {
        System.out.println("How many labels you want to create ?");
        int count = scanner.nextInt();
        List<Label> list = new ArrayList<>();
        while (list.size() < count) {
            System.out.println("Enter name ");
            String name = scanner.next();
            Label label = new Label(0, name);
            list.add(label);
            label.setId(generateId(list));
        }
        return list;
    }

    private int generateId(List<Label> labels) {
        Label maxLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(maxLabel) ? 1 : maxLabel.getId() + 1;
    }
}
