package cn.felord.kono.configuration;

/**
 * @author a
 * @since 14:00
 **/
public interface CrudMapper<T, PK> {


    int insert(T entity);

    int updateById(T entity);

    int deleteById(PK id);

    T findById(PK id);

}
