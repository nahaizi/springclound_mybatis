package com.lili.provider.controller;

import com.lili.provider.service.ProcessinstanceService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * @param
 * @author lijunyu
 * @Description: //TODO
 * @return
 * @throws
 * @date ===========================================
 * 修改人：lijunyu，    修改时间：      修改版本：
 * 修改备注：
 * ===========================================
 */

@Api(description = "流程相关接口")
@RestController
@RequestMapping("batch")
public class BatchListController {

    @Autowired
    private ProcessinstanceService processinstanceservice;
    @GetMapping("batchlist")
    public Object batchlist(@RequestParam("currentPage") Integer currentPage , @RequestParam("pageSize") Integer pageSize) {
        List<Map> maplist = processinstanceservice.getbatchlist(currentPage,pageSize);
        return  maplist;
    }

    @PostMapping("savebatchlist")
    public void savebatchlist(@RequestBody Map map) {
         processinstanceservice.savebatchlist(map);
    }

    @PostMapping("savelog")
    public void savelog() {
        try {
            File file = new File("C:\\Users\\lijunyu\\Desktop\\abs-info.2022-01-12.1.log");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = "";

            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            File copyfile = new File("C:\\Users\\lijunyu\\Desktop\\abs-info.2022-01-12.copy.log");
            if(copyfile.exists()){
                copyfile.delete();
            }
            FileWriter writer = new FileWriter(copyfile, true);
            // 一次读入一行，直到读入null为文件结束
            List<Map> maplist = new ArrayList<>();


            Date lastdate = null;
            Date nowdate = null;
            while ((tempString = reader.readLine()) != null) {
                if(StringUtils.isEmpty(tempString) || tempString.indexOf("DEBUG updateHandlingTime") > -1 || tempString.length() < 23
                        ||
                tempString.indexOf("com.batchtest.GeneralLogUtil") > -1 ||
                        tempString.indexOf("com.joyintech.batch.service.lock.DistributedLockService") > -1 ||
                        tempString.indexOf("com.joyintech.batch.service.lock.AbsBatchLockService") > -1){
                    continue;
                }

                writer.write(tempString + "\r");

                Map map = new HashMap();
                map.put("content",tempString);

                map.put("dealdatestr",tempString.substring(0, 23 ));


                try {
                    nowdate = DateUtils.parseDate(tempString.substring(0, 23 ),new String[]{"yyyy-MM-dd HH:mm:ss.SSS"});
                    map.put("dealdate",tempString.substring(0, 23 ));

                    if(lastdate == null){
                        lastdate =  DateUtils.parseDate(tempString.substring(0, 23 ),new String[]{"yyyy-MM-dd HH:mm:ss.SSS"});
                        map.put("times",0);
                    } else {

                        map.put("times",nowdate.getTime() - lastdate.getTime());
                        lastdate =  DateUtils.parseDate(tempString.substring(0, 23 ),new String[]{"yyyy-MM-dd HH:mm:ss.SSS"});
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                maplist.add(map);

            }
            processinstanceservice.savelog(maplist);
            reader.close();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
