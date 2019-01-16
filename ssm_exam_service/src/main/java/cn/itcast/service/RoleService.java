package cn.itcast.service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll(Integer page, Integer pageSize);

    Role findById(String id);

    void save(Role role);

    List<Permission> findRoleByIdAndAllPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
