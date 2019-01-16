package cn.itcast.dao;

import cn.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    /**
     * @param id
     * @return
     */
    @Select("select * from MEMBER where id = #{id}")
    Member findMemberById(String id);
}
