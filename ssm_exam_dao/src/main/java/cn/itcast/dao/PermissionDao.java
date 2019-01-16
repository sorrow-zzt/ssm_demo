package cn.itcast.dao;

import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionDao {

    @Select("select * from PERMISSION where id in (select PERMISSIONID from ROLE_PERMISSION where ROLEID = #{id})")
    List<Permission> findPermissionlistByid(String roleid);

    @Select("select * from PERMISSION")
    List<Permission> findAll();

    @Select("insert into PERMISSION (PERMISSIONNAME,URL) values (#{permissionName},#{url})")
    void save(Permission permission);
}
