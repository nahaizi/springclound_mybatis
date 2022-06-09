package com.lili.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.lili.provider.entity.process.PortResult;
import com.lili.provider.entity.process.RequestParam;
import com.lili.provider.entity.process.ResponseParam;
import com.lili.provider.service.ProcessinstanceService;
import com.lili.provider.service.compardb.CompareDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
@Api(description = "数据库比较")
@RestController
@RequestMapping("dbcompare")
public class ClockCompareOracle {

    private static final Logger logger = LoggerFactory.getLogger(ClockCompareOracle.class);
    @Autowired
    private CompareDbService comparedbservice;

    @ApiOperation("开启流程")
    @ApiImplicitParam("RequestParam参数：BusiData")
    @PostMapping("insertdata")
    public Object insertdata(@RequestBody String url) {

        //根据传入的数据文件路径进行大批量数据的接入
        Date vdate = new Date();
        logger.info("date: {0} 开始将{1}插入oracle数据库！",vdate,url);
        comparedbservice.insertOracle(url);
        Date mdate = new Date();
        logger.info("date: {0} 完成将{1}插入oracle数据库！",mdate,url);
        long second = (mdate.getTime() - vdate.getTime())/1000;
        logger.info("共耗时：{0}秒,{1}分",second,second/60);
        vdate = new Date();
        logger.info("date: {0} 开始将{1}插入Clickhouse数据库！",vdate,url);
        comparedbservice.insertClickhouse(url);
        mdate = new Date();
        logger.info("date: {0} 完成将{1}插入Clickhouse数据库！",mdate,url);
        second = (mdate.getTime() - vdate.getTime())/1000;
        logger.info("共耗时：{0}秒,{1}分",second,second/60);

        ResponseParam responseparam = new ResponseParam();
        responseparam.setPipRespCode("000000"); // 响应码
        responseparam.setPipRespDesc("流程开启成功"); // 响应描述
        Date nowdate = new Date();
        responseparam.setProcDefineID(nowdate.getTime() + ""); // 流程定义ID

        responseparam.setProcDefineName(nowdate.getTime() + ""); // 流程定义名称
        responseparam.setProcInstanceID(nowdate.getTime() + ""); // 流程实例ID
        PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

        Object responseobj = JSONArray.toJSON(result);

        logger.info("ProviderController--任务推送数据{}", responseobj);

        return responseobj;
    }
}
