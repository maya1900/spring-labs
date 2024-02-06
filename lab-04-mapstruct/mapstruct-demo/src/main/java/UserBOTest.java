import bo.UserBO;
import dataobject.UserDO;
import convert.UserConvert;

public class UserBOTest {
    public static void main(String[] args) {
        UserDO userDO = new UserDO();
        userDO.setId(1).setUsername("maya").setPassword("123456");
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        System.out.println(userBO.getId());
        System.out.println(userBO.getUsername());
        System.out.println(userBO.getPassword());
    }
}
