package cn.itcast.dao;

import cn.itcast.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void saveSysLog(Syslog syslog);

    @Select("select * from syslog")
    List<Syslog> findAll();

}
