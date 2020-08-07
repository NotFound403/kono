package cn.felord.kono.mybatis;

/**
 * The interface Crud mapper.
 *
 * @param <T>  the type parameter
 * @param <PK> the type parameter
 */
public interface CrudMapper<T, PK> {

    /**
     * Insert int.
     *
     * @param entity the entity
     * @return the int
     */
    int insert(T entity);

    /**
     * Update by id int.
     *
     * @param entity the entity
     * @return the int
     */
    int updateById(T entity);

    /**
     * Delete by id int.
     *
     * @param id the id
     * @return the int
     */
    int deleteById(PK id);

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(PK id);
}
