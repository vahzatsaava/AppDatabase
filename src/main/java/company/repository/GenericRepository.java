package company.repository;

import java.util.List;

public interface GenericRepository<T> {
    T getById(Integer id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(Integer id);
}
