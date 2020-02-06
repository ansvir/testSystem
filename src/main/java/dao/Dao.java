package dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();

    T findById(Long id);

    T findByName(String name);

    boolean update(T t);

    boolean save(T t);

    boolean delete(T t);
}
