package cn.itcast.dao;

import cn.itcast.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    @Select("select * from ORDERS")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(column = "PRODUCTID",property = "product",
                    one=@One(select = "cn.itcast.dao.ProductDao.findById"))
    })
    List<Orders> findAll();


    @Select("select * from ORDERS where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            //映射产品,一对一
            @Result(column = "PRODUCTID",property = "product",
                    one=@One(select = "cn.itcast.dao.ProductDao.findById")),
            //映射会员,一对一
            @Result(column = "MEMBERID",property = "member",
                    one=@One(select = "cn.itcast.dao.MemberDao.findMemberById")),
            //映射旅客,一对多
            @Result(column = "ID",property = "travellers",
                    many=@Many(select = "cn.itcast.dao.TravellerDao.findTralistById"))
    })
    Orders findById(String id);
}
