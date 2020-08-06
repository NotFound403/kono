package cn.felord.kono.mybatis;

/**
 * 所有的Mapper接口都会继承{@code CrudMapper<T, PK>}.
 *
 * @param <T>  the type parameter
 * @param <PK> the type parameter
 * @author felord.cn
 * @since 14 :00
 */
public interface CrudMapper<T, PK> {

    int insert(T entity);

    int updateById(T entity);

    int deleteById(PK id);

    T findById(PK id);
}
