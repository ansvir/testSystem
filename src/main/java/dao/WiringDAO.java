package dao;

import java.util.List;

public interface WiringDAO<T> {
    List<T> findAll();

    T findByIds(Long id, Long id2);

    boolean update(T t);

    boolean save(T t);

    boolean delete(T t);
}
