package dao;

import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Product findByProductId(int pid) {
        final Product product=new Product();
        String sql = "SELECT pid,pname,price FROM product WHERE pid=?";
        jdbcTemplate.query(sql, new Object[]{pid}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                product.setPid(resultSet.getInt(1));
                product.setPname(resultSet.getString(2));
                product.setPrice(resultSet.getInt(3));
            }
        });
//        List list = jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
        return product;
    }
    public Product findByProductName(String pname) {
        final Product product=new Product();
        String sql = "SELECT pid,pname,price FROM product WHERE pname=?";
        jdbcTemplate.query(sql, new Object[]{pname}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                product.setPid(resultSet.getInt(1));
                product.setPname(resultSet.getString(2));
                product.setPrice(resultSet.getInt(3));
            }
        });
//        List list = jdbcTemplate.queryForObject(sql, new Object[]{id}, List.class);
        return product;
    }
    public int insertProduct(Product product) {
        String sql = "INSERT INTO product (pname,price) VALUES (?,?)";
        return jdbcTemplate.update(sql, product.getPname(), product.getPrice());
    }


    public int updateProduct(Product product) {
        String sql = "UPDATE product SET pname=? WHERE pid=?";
        return jdbcTemplate.update(sql, product.getPname(), product.getPid());
    }

   /* public int delectProduct(Product product){
        String sql="DELECT from product where pid=?";
        return jdbcTemplate.update(sql,product.getPid());
    }*/
}
