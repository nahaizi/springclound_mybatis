package com.lili.provider.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.provider.entity.Processinstance;
import com.lili.provider.entity.Processpermissionsrc;
import com.lili.provider.entity.process.Processtaskdeatil;
import com.lili.provider.mapper.ProcessinstanceMapper;
import com.lili.provider.service.ProcessinstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyh
 * @since 2021-10-13
 */
@Service
public class ProcessinstanceServiceImpl extends ServiceImpl<ProcessinstanceMapper, Processinstance> implements ProcessinstanceService {


    @Autowired
    ProcessinstanceMapper processinstancemapper;

    public List<Processtaskdeatil> getProcesstaskdeatilList(String instanceid){
        return processinstancemapper.getProcesstaskdeatilList(instanceid);
    }

    public Processinstance getProcessinstance(String instanceid){
        return processinstancemapper.getProcessinstance(instanceid);
    }
    public List<Processpermissionsrc> getProcesspermissionsrc(String instanceid){
        return processinstancemapper.getProcesspermissionsrc(instanceid);
    }

    public List<Processpermissionsrc> getNextProcesspermissionsrclist(String instanceid){
        return processinstancemapper.getNextProcesspermissionsrclist(instanceid);
    }

    public int getProcesstaskdeatillist(String instanceid){
        QueryWrapper<Map> wrapper = new QueryWrapper<>();
        wrapper.eq("dealstatus","W");
        wrapper.eq("procinstanceid",instanceid);
        return processinstancemapper.getProcesstaskdeatillist(wrapper);
    }


    public List<Map> getbatchlist(Integer currentPage , Integer pageSize){
        return processinstancemapper.getbatchlist( currentPage ,  pageSize);
    }

    @DS("mysql_1")
    public void savebatchlist(Map map){
        Map parammap = new HashMap();

        String columnname = "";
        String value = "";
        for (Object key : map.keySet()){
            columnname += key + ",";
            value += map.get(key) + ",";
        }
        parammap.put("columnname",columnname);
        parammap.put("value",value);
        processinstancemapper.savebatchlist(parammap);
    }
    @DS("mysql_1")
    public void savelog(List<Map> maplist){

        processinstancemapper.savelog(maplist);
    }
}
