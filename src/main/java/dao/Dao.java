package dao;

import java.util.List;

public interface Dao<T> {
    public List<T> findAll();

    public T findById(Long id);

    public T findByName(String name);

    public boolean update(T t);

    public boolean save(T t);

    public boolean delete(T t);
}
