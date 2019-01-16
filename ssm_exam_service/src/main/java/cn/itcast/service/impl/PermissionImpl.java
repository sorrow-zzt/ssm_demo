package cn.itcast.service.impl;

import cn.itcast.dao.PermissionDao;
import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
