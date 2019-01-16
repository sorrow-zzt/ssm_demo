package cn.itcast.dao;


import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {

    //查询所有产品
    @Select("select * from PRODUCT")
    List<Product> findAll();

    //添加产品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName}," +
            "#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    //根据id查询产品
    @Select("select * from PRODUCT where id=#{id}")
    Product findById(String id);

    void delete(List<String> id);
}
