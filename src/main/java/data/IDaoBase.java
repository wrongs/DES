package data;

import java.util.List;
import javax.persistence.EntityManager;

public interface IDaoBase<E extends BaseEntity> {
    
	void insert(E entity);
	void remove(E entity);
        E update(E entity);
	E find(Long id);
	E get(long id);
	List<E> findAll();
	EntityManager getEM();
}
