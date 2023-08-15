package com.lili.controllor.es;

import com.lili.es.entity.BaseLogObj;
import com.lili.es.entity.ParamDto;
import com.lili.es.util.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("escontroller")
@Slf4j
public class EsController {

    /**
     * 查询数据量
     *
     * @param indexname
     * @return
     */
    @RequestMapping("get")
    public String get(ParamDto paramDto) {
        SearchUtil searchutil = new SearchUtil();
        searchutil.setParam(paramDto.getSearchFiled(), paramDto.getSearchValue());
        Set<String> indexset = searchutil.getAllIndex();
        log.info("indexset =======> " + indexset);
        long cnt = searchutil.getCountByIndex(paramDto.getIndexName());
        log.info("cnt =======> " + cnt);
        return cnt + "==SUCCEED";
    }

    @RequestMapping("getMulty")
    public List<Object> getMulty(ParamDto paramDto) {
        SearchUtil searchutil = new SearchUtil();
        searchutil.setMultiMatchQuery(paramDto.getSearchValue(), paramDto.getSearchFileds());
        List<Object> result = searchutil.listByIndex(paramDto.getIndexName(), BaseLogObj.class);
        log.info("cnt =======> " + result.size());

        return result;
    }
}
