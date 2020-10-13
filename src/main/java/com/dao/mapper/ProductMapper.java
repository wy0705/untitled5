package com.dao.mapper;

import com.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper {
    @Select(value = "SELECT pid,pname,price FROM product WHERE pid=#{id}")
    Product findByProductId(@Param("id") int id );

    @Select(value = "SELECT pid,pname,price FROM prioduct WHERE pname=#{name}")
    Product findByProductName(@Param("name") String pname);

    @Insert(value = "INSERT INTO product(pid,pname,price) VALUES (#{product.id},#{product.name},#{product.price})")
    int insertProduct(@Param(value = "product") Product product);

    @Update(value = "UPDUCT product SET pname=#{product.name} WHERE pid=#{product.id}")
    int updateProduct(@Param(value = "product") Product product);
}
