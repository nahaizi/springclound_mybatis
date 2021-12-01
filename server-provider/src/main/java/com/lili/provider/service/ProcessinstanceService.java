package com.lili.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lili.provider.entity.Processinstance;
import com.lili.provider.entity.Processpermissionsrc;
import com.lili.provider.entity.process.Processtaskdeatil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyh
 * @since 2021-10-13
 */
public interface ProcessinstanceService extends IService<Processinstance> {
    public List<Processtaskdeatil> getProcesstaskdeatilList(String instanceid);
    public Processinstance getProcessinstance(String instanceid);
    List<Processpermissionsrc> getProcesspermissionsrc(String instanceid);
    List<Processpermissionsrc> getNextProcesspermissionsrclist(String instanceid);
    int getProcesstaskdeatillist(String instanceid);

    List<Map> getbatchlist(Integer currentPage , Integer pageSize);

    void savebatchlist(Map map);
}
