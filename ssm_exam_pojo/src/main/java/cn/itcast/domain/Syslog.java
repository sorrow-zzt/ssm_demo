package cn.itcast.domain;

import cn.itcast.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class Syslog {
    private String id;
    private Date visitTime;
    private String visitTimeStr;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;

    public String getVisitTimeStr() {
        String visitTimeStr = DateUtils.date2String(visitTime, "yyyy-MM-dd HH-mm-ss");
        return visitTimeStr;
    }

}
