package com.lili.provider.controller;

import com.lili.provider.service.ProcessinstanceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

}
