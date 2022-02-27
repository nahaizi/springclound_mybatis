package com.lili.provider.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lili.provider.entity.Processinstance;
import com.lili.provider.entity.Processpermissionsrc;
import com.lili.provider.entity.process.Processtaskdeatil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyh
 * @since 2021-10-13
 */
@Mapper
@Repository
public interface ProcessinstanceMapper extends BaseMapper<Processinstance> {
    List<Processtaskdeatil> getProcesstaskdeatilList(@Param("instanceid") String instanceid);
    Processinstance getProcessinstance(@Param("instanceid") String instanceid);
    List<Processpermissionsrc> getProcesspermissionsrc(@Param("defkey") String instanceid);
    List<Processpermissionsrc> getNextProcesspermissionsrclist(@Param("defkey") String instanceid);
    int getProcesstaskdeatillist(@Param(Constants.WRAPPER) Wrapper<Map> wrapper);

    List<Map> getbatchlist(@Param("currentPage")Integer currentPage ,@Param("pageSize") Integer pageSize);
    int savebatchlist(Map map);
    int savelog(List<Map> maplist);
    int deletebatch(Map map);
}
