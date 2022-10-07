package company.view;

import java.util.List;

public interface View <T>{
    T create();
    List<T> getAll();
    void delete();
    T update();
    T get();
    void close();
}
