package com.service;
import com.dao.OrderDao;
import com.dao.UserDao;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TransactionTemplate txTemplate;

    @Cacheable(cacheNames = "user", key = "#uid")
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
        User userByName = findByName(user.getUname());
        if (userByName != null && userByName.getUname() != null && userByName.getUname().equals(user.getUname())) {
            return true;
        }
        user.setUpasswd(DigestUtils.md5DigestAsHex(user.getUpasswd().getBytes()));
        return userDao.insertUser(user) != 0;
    }


    @CacheEvict(cacheNames = "user", key = "#user.uid")
    public int updateUserName(User user) {
        return userDao.updateUser(user);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "user", key = "#user.id")})
    public int deleteUserAnddeleteOreder(int uid){
        int deleteuser=0;
        int deleteOrderbyUid=0;
        Object execute = txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                try {
                    userDao.deleteUser(uid);
                    orderDao.deleteOrderbyUid(uid);
                } catch (Throwable t) {
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });

        return deleteOrderbyUid+deleteuser;
    }

   /* @Transactional
    public int deleteUserAnddeleteOreder(int uid){
        userDao.deleteUser(uid);
        orderDao.deleteOrderbyUid(uid);
        return 0;
    }*/
}
