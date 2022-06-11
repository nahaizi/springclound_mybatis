package com.lili.provider.service.compardb.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lili.provider.mapper.ProcessinstanceMapper;
import com.lili.provider.mapper.comparedb.ComparDBMapper;
import com.lili.provider.service.compardb.CompareDbService;
import com.lili.provider.util.csv.CsvUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param $
 * @author lijunyu
 * @Description: TODO
 * @return $
 * @throws
 * @date $ $
 *       ===========================================
 *       修改人：lijunyu， 修改时间：$ $， 修改版本：
 *       修改备注：
 *       ===========================================
 */
@Service
public class CompareDbServiceImpl implements CompareDbService {
    private static final Logger logger = LoggerFactory.getLogger(CompareDbServiceImpl.class);

    @Autowired
    ComparDBMapper compardbmapper;

    @Override
    @DS("oracle")
    public String insertOracle(String url) {

        // 解析csv文件，将数据插入oracle数据库
        String[] str = CsvUtils.readCsv(url);
        for (String s : str) {
            logger.info(s);

        }
        //String[]分批

        compardbmapper.insertOracle();
        return null;
    }

    @Override
    @DS("clickhouse")
    public String insertClickhouse(String url) {
        String[] str = CsvUtils.readCsv(url);
        for (String s : str) {
            logger.info(s);
        }
        compardbmapper.insertClickhouse();
        return null;
    }
}
