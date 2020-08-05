package cn.felord.kono.configuration;

/**
 * @author a
 * @since 15:51
 **/
public final class SqlProvider {



    public<PK> String findById(PK id){


        return "select * from client_user where user_id = "   ;
    }

}
