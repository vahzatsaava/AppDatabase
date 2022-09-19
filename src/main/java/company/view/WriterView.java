package company.view;

import company.controller.WriterController;
import company.model.Label;
import company.model.Writer;

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
        List<Label> listOfLabels = createLabels();

        return controller.createWriter(new Writer(firstName, secondName, listOfLabels));
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
        List<Label> listOfLabels = createLabels();

        return controller.upDate(new Writer(id, firstName, lastName, listOfLabels));
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

    private int generateIdForLabels(List<Label> labels) {
        Label maxLabel = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(maxLabel) ? 1 : maxLabel.getId() + 1;
    }
}
