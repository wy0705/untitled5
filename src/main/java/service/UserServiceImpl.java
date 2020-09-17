package service;
import dao.ProductDao;
import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    public User findByUserId(int uid){
        return userDao.findByUserId(uid);
    }

    public User findByName(String uname){
        return userDao.findByName(uname);
    }

    public User findByNameAndPassword(String name, String password){
        return userDao.findByNameAndPassword(name,password);
    }

    public boolean register(User user) {
        //用户重名校验
        User userByName = findByName(user.getUname());
        if (userByName != null && userByName.getUname() != null && userByName.getUname().equals(user.getUname())) {
            return true;
        }
        //对用户密码进行MD5,目的是，数据库中的敏感数据，不要存储明文。
        user.setUpasswd(DigestUtils.md5DigestAsHex(user.getUpasswd().getBytes()));
        return userDao.insertUser(user) != 0;
    }

    @Cacheable(cacheNames = "user", key = "#uid")
    public User findById(int id) {
        return userDao.findByUserId(id);
    }

    @CacheEvict(cacheNames = "user", key = "#user.uid")
    public int updateUserName(User user) {
        return userDao.updateUser(user);
    }

}
