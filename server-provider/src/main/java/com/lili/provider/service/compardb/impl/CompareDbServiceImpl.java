package com.lili.provider.service.compardb.impl;

import com.lili.provider.mapper.ProcessinstanceMapper;
import com.lili.provider.service.compardb.CompareDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param $
 * @author lijunyu
 * @Description: TODO
 * @return $
 * @throws
 * @date $ $
 * ===========================================
 * 修改人：lijunyu，    修改时间：$ $，    修改版本：
 * 修改备注：
 * ===========================================
 */
@Service
public class CompareDbServiceImpl implements CompareDbService {

    @Autowired
    ProcessinstanceMapper processinstancemapper;

    @Override
    public String insertOracle(String url) {

        //解析csv方法

        return null;
    }

    @Override
    public String insertClickhouse(String url) {
        return null;
    }
}
