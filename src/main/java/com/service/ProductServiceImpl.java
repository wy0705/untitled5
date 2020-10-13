package com.service;
import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    @Autowired
    private ProductDao productDao;

    public int insertProduct(Product product){
        return productDao.insertProduct(product);
    }

    public int updateProduct(Product product){
        return productDao.updateProduct(product);
    }

    @Cacheable(cacheNames = "product", key = "#pid")
    public Product findByProductId(int pid){
        return findByProductId(pid);
    }

    @Cacheable(cacheNames = "product", key = "#pname")
    public Product findByProductName(String pname){
        return productDao.findByProductName(pname);
    }
}
