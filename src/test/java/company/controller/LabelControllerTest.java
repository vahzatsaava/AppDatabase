package company.controller;

import company.model.Label;
import company.repository.jdbc.JdbcLabelRepositoryImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class LabelControllerTest extends TestCase{
    @Mock
    private JdbcLabelRepositoryImpl jdbcLabelRepositoryImpl;

    @InjectMocks
    private LabelController controller;


    @Test
    public void testCreateLabelTest_Successful() {
        Mockito.when(jdbcLabelRepositoryImpl.save(any(Label.class))).thenReturn(new Label("Alfa"));
        Assertions.assertEquals("Alfa", controller.createLabel(new Label(anyString())).getName());
    }
    @Test
    public void testCreateLabelTest_UnSuccessful() {
        Mockito.when(jdbcLabelRepositoryImpl.save(any())).thenReturn(new Label("Alfa"));
        Assertions.assertNotEquals("Abc",controller.createLabel(new Label(anyString())));    }

    @Test
    public void testGetLabels_Successful() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("GGG"));
        Mockito.when(jdbcLabelRepositoryImpl.getAll()).thenReturn(labels);
        Assertions.assertEquals(labels,controller.getLabels());
    }
    @Test
    public void testGetLabels_UnSuccessful() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("GGG"));
        Mockito.when(jdbcLabelRepositoryImpl.getAll()).thenReturn(labels);
        Assertions.assertNotEquals(null,controller.getLabels());
    }

    @Test
    public void testUpdate_Successful() {
        Mockito.when(jdbcLabelRepositoryImpl.update(any())).thenReturn(new Label("Alfa"));
        Assertions.assertEquals("Alfa",controller.update(new Label(anyString())).getName());
    }
    @Test
    public void testUpdate_UnSuccessful() {
        Mockito.when(jdbcLabelRepositoryImpl.update(any())).thenReturn(new Label("Alfa"));
        Assertions.assertNotEquals("House",controller.update(new Label(anyString())).getName());
    }
    @Test
    public void testGet_Successful() {
        Mockito.when(jdbcLabelRepositoryImpl.getById(1)).thenReturn(new Label("aaa"));
        Assertions.assertEquals(controller.get(1).getName(),"aaa");
    }
    @Test
    public void testGet_Un_Successful() {
        Mockito.when(jdbcLabelRepositoryImpl.getById(1)).thenReturn(new Label("aaa"));
        Assertions.assertNotEquals(controller.get(1).getName(),"aa");
    }

}