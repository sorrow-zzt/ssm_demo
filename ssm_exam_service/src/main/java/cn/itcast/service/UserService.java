package cn.itcast.service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<UserInfo> findAll(Integer page, Integer pageSize);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    void addRoleToUser(String userId, String[] ids);
}
