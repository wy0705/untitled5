package service;
import dao.OrderDao;
import entity.Orderr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderrServiceImpl {
    @Autowired
    private OrderDao orderDao;

    public int insertOrderr(Orderr orderr){
        return orderDao.insertOrderr(orderr);
    }

    public int delectOrderr(Orderr orderr){
        return orderDao.delectOrderr(orderr);
    }

    @Cacheable(cacheNames = "oreder",key ="#oid")
    public Orderr findByOredrrId(int oid){
        return orderDao.findByOredrrId(oid);
    }

    @Cacheable(cacheNames = "userorder",key ="#uid")
    public List<Orderr> findByUserId(int uid){
        return orderDao.findByUserId(uid);
    }
}

