package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;

import javax.annotation.security.PermitAll;
import java.util.List;

public interface RoleDao {
    @Select("select * from ROLE where id in (select ROLEID from USERINFO_ROLE where userid = #{userid})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",
            many = @Many(select = "cn.itcast.dao.PermissionDao.findPermissionlistByid"))
    })
    List<Role> findRolelistByid(String userid);

    @Select("select * from ROLE")
    List<Role> findAll();

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findPermissionlistByid"))
    })
    Role findById(String id);

    @Select("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("SELECT * from PERMISSION where id not in(SELECT PERMISSIONID from ROLE_PERMISSION WHERE ROLEID = #{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);

    @Insert("insert into ROLE_PERMISSION values(#{id},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("id") String id);
}
