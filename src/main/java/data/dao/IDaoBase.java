package data.dao;

import data.entites.BaseEntity;
import java.util.List;
import javax.persistence.EntityManager;

public interface IDaoBase<E extends BaseEntity> {
    
	void insert(E entity);
	void remove(E entity);
        E update(E entity);
	E find(int id);
	E get(int id);
	List<E> findAll();
	EntityManager getEM();
}
