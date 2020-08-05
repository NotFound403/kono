package cn.felord.kono.entity;

import lombok.Data;

import java.beans.*;
import java.time.LocalDateTime;

/**
 * @author a
 * @since 12:26
 **/
@Data
public class UserInfoVO extends UserInfo {
    private static final long serialVersionUID = 6355694289388175815L;
    private LocalDateTime addTime;


    public static void main(String[] args) throws IntrospectionException {

        UserInfo userInfo = new UserInfo();
            userInfo.setAge(12);
            userInfo.setName("xxx");

        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);




        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();


        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            System.out.println("propertyDescriptor.getName() = " + propertyDescriptor.getName());

        }




    }

}
