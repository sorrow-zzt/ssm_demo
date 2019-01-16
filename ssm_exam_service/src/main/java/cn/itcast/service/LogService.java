package cn.itcast.service;

import cn.itcast.domain.Syslog;

import java.util.List;

public interface LogService {
    void saveSysLog(Syslog syslog);

    List<Syslog> findAll(Integer page, Integer pageSize);
}
