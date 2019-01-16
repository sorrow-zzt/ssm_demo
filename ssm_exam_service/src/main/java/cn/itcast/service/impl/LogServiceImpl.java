package cn.itcast.service.impl;

import cn.itcast.dao.LogDao;
import cn.itcast.domain.Syslog;
import cn.itcast.service.LogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Override
    public void saveSysLog(Syslog syslog) {
        logDao.saveSysLog(syslog);
    }

    @Override
    public List<Syslog> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return logDao.findAll();
    }
}
