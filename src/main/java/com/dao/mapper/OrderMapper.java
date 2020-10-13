package com.dao.mapper;

import com.entity.Orderr;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {
    @Select(value = "SELECT oid,otime,uid,pid,state WHERE oid=#{id}")
    Orderr findByOredrrId(@Param("id") int oid);

    @Select(value = "SELECT oid,otime,pid,state *FROM orderr WHERE uid=#{id}")
    List<Orderr> findByUserId(@Param("id") int uid);

    @Insert(value ="INSERT INTO orderr (otime,uid,pid,state) VALUES(#{orderr.otime},#{orderr.uid},#{orderr.pid},#{orderr.state})")
    int insertOrderr(@Param("orderr") Orderr orderr);

    @Delete(value = "UPDATE orderr SET state=#{orderr.state} WHERE oid=#{orderr.oid}")
    int delectOrderr(@Param("orderr") Orderr orderr);
}
