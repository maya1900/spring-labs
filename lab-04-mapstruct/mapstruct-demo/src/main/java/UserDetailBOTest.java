import bo.UserBO;
import bo.UserDetailBO;
import convert.UserConvert;
import dataobject.UserDO;

public class UserDetailBOTest {
    public static void main(String[] args) {
        UserDO userDO = new UserDO().setId(1).setUsername("maya").setPassword("123456");
        UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetail(userDO);
        System.out.println(userDetailBO.getUserId());
    }
}
