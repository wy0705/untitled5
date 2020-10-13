import com.config.DataConfig;
import com.config.info.JdbcInfo;
import com.dao.mapper.ProductMapper;
import com.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, JdbcInfo.class})

public class ProductTestMybatis {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelect(){
        Product product=productMapper.findByProductId(1);
        if (product!=null){
            Assert.assertEquals(1,product.getPid());
        }
        Product product1=productMapper.findByProductName("test2");
        if (product1!=null){
            Assert.assertEquals("test2",product1.getPname());
        }else {
            Assert.fail("product1 is null");
        }
    }

    @Test
    public void testInstert(){
        Product product=new Product();
        product.setPname("test3");
        product.setPrice(123);
        productMapper.insertProduct(product);
        Product result=productMapper.findByProductName("test3");
        if (result!=null){
            Assert.assertEquals("test3",result.getPname());
        }else{
            Assert.fail();
        }
    }
    @Test
    public void testUpdate(){
        Product product=new Product();
        product.setPid(2);
        product.setPname("test2");
        product.setPrice(123);
        productMapper.updateProduct(product);
        Product product1=productMapper.findByProductId(product.getPid());
        if (product1!=null){
            Assert.assertEquals("test2",product1.getPname());
        }else {
            Assert.fail();
        }
    }
}
