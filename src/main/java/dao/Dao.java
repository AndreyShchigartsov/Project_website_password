package dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface Dao<K,V> {
    public V save(V value);
    public Optional<V> findId(K key);
    public List<V> findAll();
    public boolean update(V value);
    public boolean delete(K key);
}
