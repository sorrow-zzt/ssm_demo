package cn.itcast.dao;

import cn.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("select t.* from traveller t left join order_traveller ot on t.id=ot.travellerid where ot.orderid=#{id}")
    List<Traveller> findTralistById(String id);
}
