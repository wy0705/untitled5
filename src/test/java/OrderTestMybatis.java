import com.config.DataConfig;
import com.config.info.JdbcInfo;
import com.dao.mapper.OrderMapper;
import com.entity.Orderr;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, JdbcInfo.class})

public class OrderTestMybatis {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelect(){
        Orderr order=orderMapper.findByOredrrId(1);
        if (order!=null){
            Assert.assertEquals(1,order.getOid());
        }
        List<Orderr> orderr2=orderMapper.findByUserId(1);
        if (orderr2!=null){
            for (Orderr orderr:orderr2){
                Assert.assertEquals(1,order.getUid());
            }
        }else {
            Assert.fail("Order is fales");
        }
    }

    @Test
    public void testInsert(){
        Orderr orderr=new Orderr();
        orderr.setOtime("otime");
        orderr.setPid(1);
        orderr.setState("T");
        orderr.setUid(1);
        orderMapper.insertOrderr(orderr);
        List<Orderr> result=orderMapper.findByUserId(1);
        if (result!=null){
            for (Orderr orderr1:result){
                Assert.assertEquals("otime",orderr1.getOtime());
            }
        }else {
            Assert.fail();
        }
    }

}
