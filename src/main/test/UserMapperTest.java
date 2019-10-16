import com.avic.common.utils.MD5;
import com.avic.mapper.UserMapper;
import com.avic.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sconglee
 * @date 2019/10/14
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class UserMapperTest {

    @Autowired(required = true)
    UserMapper userMapper;



    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("lsc");
        user.setPassWord(MD5.getMD5("123456"));
        user.setAccountType(1);
        user.setAccountStatus(0);
        userMapper.insertUser(user);
    }

    @Test
    public void findUserByUserName() {
        String userName = "lsc";
        String passWord = "123456";

        User user = new User();
        user.setUserName(userName);
        user.setPassWord(MD5.getMD5(passWord));

        User userInfo = userMapper.findUserByUsername(user);
        if (userInfo == null) {
            System.out.println("没有查询结果！！");
        } else {
            System.out.println(userInfo.getPassWord());
        }

    }

}
