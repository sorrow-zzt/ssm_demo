package cn.itcast.dao;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from USERINFO where username=#{name}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",
            many = @Many(select = "cn.itcast.dao.RoleDao.findRolelistByid"))
    })
    UserInfo findUserInfoByName(String name);

    @Select("select * from USERINFO")
    List<UserInfo> findAll();

    @Insert("insert into USERINFO(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    // 1.根据id查询用户(userinfo)信息
    //
    //​2、根据用户id查询出对应的角色(role)信息(用户表与角色表为多对多关系)
    //
    //​3、根据角色id(role)查询出对应的资源权限信息(permission)[角色表与资源权限表为多对多关系]
    @Select("select * from userinfo where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",
            many = @Many(select = "cn.itcast.dao.RoleDao.findRolelistByid"))
    })
    UserInfo findById(String id);

    @Select("SELECT * from ROLE where id not in (SELECT ROLEID from USERINFO_ROLE WHERE USERID = #{id})")
    List<Role> findUserByIdAndAllRole(String id);

    @Insert("insert into USERINFO_ROLE values (#{userid},#{id})")
    void addRoleToUser(@Param("userid") String userId, @Param("id") String id);
}
