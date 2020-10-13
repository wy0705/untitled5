import com.config.DataConfig;
import com.config.info.JdbcInfo;
import com.dao.mapper.UserMapper;
import com.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, JdbcInfo.class})
public class UserTestMybatis {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User u = userMapper.findByUserId(1);
//        Assert.assertEquals(true,u != null);
        if (u != null) {
            Assert.assertEquals(1, u.getUid());
        }
        User u2 = userMapper.findByName("test2");
        if (u2 != null) {
            Assert.assertEquals("test2", u2.getUname());
        } else {
            Assert.fail("u2 is null");
        }

        User u3 = userMapper.findByNameAndPassword("test2", DigestUtils.md5DigestAsHex("123456".getBytes()));
        if (u3 != null) {
            Assert.assertEquals(6, u3.getUid());
        } else {
            Assert.fail("u3 is null");
        }
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setUname("test3");
        u.setAge(100);
        u.setUpasswd(DigestUtils.md5DigestAsHex("123".getBytes()));
        userMapper.insertUser(u);
        User result = userMapper.findByName("test3");
        if (result != null) {
            Assert.assertEquals("test3", result.getUname());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void testUpdate() {
        User u = new User();
        u.setUid(6);
        u.setUname("test2");
        userMapper.updateUser(u);
        User result = userMapper.findByUserId(u.getUid());
        if (result != null) {
            Assert.assertEquals("test2", result.getUname());
        } else {
            Assert.fail();
        }
    }
}
