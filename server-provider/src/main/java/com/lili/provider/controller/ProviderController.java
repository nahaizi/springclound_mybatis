package com.lili.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lili.provider.entity.Param;
import com.lili.provider.entity.Processinstance;
import com.lili.provider.entity.Processpermissionsrc;
import com.lili.provider.entity.process.*;
import com.lili.provider.service.ProcessinstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author lijunyu @Description: //TODO
 * @return
 * @throws
 * @date =========================================== 修改人：lijunyu， 修改时间： 修改版本： 修改备注：
 *     ===========================================
 */
@Api(description = "流程相关接口")
@RestController
@RequestMapping("provider")
public class ProviderController {
  private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

  @Autowired private ProcessinstanceService processinstanceservice;

  @ApiOperation("开启流程")
  @ApiImplicitParam("RequestParam参数：BusiData")
  @PostMapping("startprocess")
  public Object getData(@RequestBody RequestParam requestparam) {
    Object obj = JSONArray.toJSON(requestparam);
    logger.info("ProviderController--开启流程数据{}", obj);
    int count = processinstanceservice.getProcesstaskdeatillist(requestparam.getProcInstanceID());

    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程开启成功"); // 响应描述
    Date nowdate = new Date();
    responseparam.setProcDefineID(nowdate.getTime() + ""); // 流程定义ID

    responseparam.setProcDefineName(nowdate.getTime() + ""); // 流程定义名称
    responseparam.setProcInstanceID(nowdate.getTime() + ""); // 流程实例ID
    responseparam.setBusidata(requestparam.getBusiData());
    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);

    logger.info("ProviderController--任务推送数据{}", responseobj);

    return responseobj;
  }

  @ApiOperation("提交流程数据")
  @ApiImplicitParam("RequestParam参数：ProcDefineID、ProcInstanceID、BusiData")
  @PostMapping("submmitprocess")
  public Object submmitprocess(@RequestBody RequestParam requestparam) {
    logger.info("提交流程数据");
    Object obj = JSONArray.toJSON(requestparam);

    logger.info("ProviderController--开启流程数据{}" + obj.toString());
    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程开启成功"); // 响应描述
    responseparam.setProcDefineID(requestparam.getProcDefineID());
    responseparam.setProcInstanceID(requestparam.getProcInstanceID());
    responseparam.setBusidata(requestparam.getBusiData());
    responseparam.setRespStamp("2021-10-10");
    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);

    logger.info("流程提交结果{}", responseobj);

    return responseobj;
  }

  @ApiOperation("流程详情图")
  @ApiImplicitParam("RequestParam参数：")
  @PostMapping("processdetailimag")
  public Object processdetailimag(@RequestBody RequestParam requestparam) {
    logger.info("流程详情图");
    logger.info("get ok");

    Object obj = JSONArray.toJSON(requestparam);

    logger.info("流程详情图参数{}" + obj.toString());

    java.net.URL url = ProviderController.class.getResource("/timg.gif");
    // 获取图片字节流
    List<Processtaskdeatil> maplist =
        processinstanceservice.getProcesstaskdeatilList("1634631865255");
    Processinstance Processinstance = processinstanceservice.getProcessinstance("1634631865255");

    byte[] data = null;

    try {

      InputStream input = url.openStream();
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      int numBytesRead = 0;
      while ((numBytesRead = input.read(buf)) != -1) {
        output.write(buf, 0, numBytesRead);
      }
      data = output.toByteArray();
      output.close();
      input.close();
    } catch (FileNotFoundException ex1) {
      ex1.printStackTrace();
    } catch (IOException ex1) {
      ex1.printStackTrace();
    }

    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程开启成功"); // 响应描述
    responseparam.setRespStamp("2021-10-10");
    responseparam.setProcInstanceImg(data);

    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);
    logger.info("ProviderController--开启流程数据{}", responseobj);
    return responseobj;
  }

  /**
   * @Description: 查找可退回节点
   *
   * @param [requestparam]
   * @return java.lang.Object
   * @throws
   * @author lijunyu
   * @date 2021/10/12 21:55 =========================================== 修改人：lijunyu 修改时间：2021/10/12
   *     21:55， 修改版本： 修改备注： ===========================================
   */
  @ApiOperation("可退回列表")
  @ApiImplicitParam("RequestParam参数：TaskDefKey")
  @PostMapping("findBackNodeList")
  public Object findBackNodeList(@RequestBody RequestParam requestparam) {
    Object obj = JSONArray.toJSON(requestparam);

    logger.info("可退回列表请求参数{}" + obj.toString());
    List<Processpermissionsrc> list =
        processinstanceservice.getProcesspermissionsrc(requestparam.getTaskDefKey());

    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程开启成功"); // 响应描述
    responseparam.setRespStamp("2021-10-10");
    responseparam.setNodeCount(Long.valueOf(list.size()));

    List<Backprocessnode> nodelist = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      Processpermissionsrc src = list.get(i);
      Backprocessnode node = new Backprocessnode();
      node.setNumber(i + "");
      node.setTaskName(src.getTaskname());
      node.setTaskDefKey(src.getPermition());
      nodelist.add(node);
    }

    responseparam.setNodeList(nodelist);

    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);
    logger.info("ProviderController--开启流程数据{}", responseobj);

    return responseobj;
  }

  @ApiOperation("退回流程")
  @ApiImplicitParam("RequestParam参数：ProcInstanceID")
  @PostMapping("backProcess")
  public Object backProcess(@RequestBody RequestParam requestparam) {
    Object obj = JSONArray.toJSON(requestparam);

    logger.info("退回流程请求参数{}" + obj.toString());

    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程开启成功"); // 响应描述
    responseparam.setRespStamp("2021-10-10");
    responseparam.setBusidata(requestparam.getBusiData());
    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);
    logger.info("ProviderController--退回流程返回参数{}", responseobj);

    return responseobj;
  }

  @ApiOperation("发送流程")
  @ApiImplicitParam("RequestParam参数：ProcInstanceID")
  @PostMapping("sendProcess")
  public Object sendProcess(@RequestBody RequestParam requestparam) {

    // 判断还有无代办事项，无代办事项则发送生效接口，有则毋需发送
    int count = processinstanceservice.getProcesstaskdeatillist(requestparam.getProcInstanceID());
    Object responseobj = getObject(requestparam, count);

    return responseobj;
  }

  private Object getObject(@RequestBody RequestParam requestparam, int count) {
    Object responseobj;
    Object obj = JSONArray.toJSON(requestparam);

    logger.info("发送流程请求参数{}" + obj.toString());

    String url = "http://localhost:8080/yc/sendtask";
    ResponseParam responseparam = new ResponseParam();

    Date nowdate = new Date();
    requestparam.setProcDefineID(nowdate.getTime() + "");

    String processkey = "";
    if (requestparam.getBusiData() != null) {
      Map map = requestparam.getBusiData();
      if (map.containsKey("processkey")) {
        processkey = (String) map.get("processkey");
      }
    }

    // 获取对应模块的流程权限--没有进入过流程则为该模块的第一个节点
    Processpermissionsrc nextProcesspermissionsrc = null;

    Processpermissionsrc lastProcesspermissionsrc = null;
    List<Processpermissionsrc> list =
        processinstanceservice.getNextProcesspermissionsrclist(processkey);
    if (requestparam.getTaskDefKey() == null) {
      nextProcesspermissionsrc = list.get(0);
    } else {
      // 上一个节点为当前结点，则为true，在循环下个时即为下个节点
      boolean flag = false;
      for (Processpermissionsrc src : list) {
        // 回退节点不为空则为回退操作
        if (requestparam.getReturnNodeNo() != null
            && requestparam.getReturnNodeNo().equals(src.getPermition())) {
          lastProcesspermissionsrc = src;
          break;
        }

        if (flag) {
          nextProcesspermissionsrc = src;
          break;
        } else if (requestparam.getTaskDefKey() != null
            && requestparam.getTaskDefKey().equals(src.getPermition())) {
          flag = true;
        }
      }
    }

    // 是否回会签
    boolean iscounter = false;

    // 退回
    if (requestparam.getReturnNodeNo() != null && lastProcesspermissionsrc != null) {
      requestparam.setTaskDefKey(lastProcesspermissionsrc.getPermition());
      requestparam.setTaskName(lastProcesspermissionsrc.getTaskname());
      // 下一节点不是会签，则无需发送多次任务；下一节点是会签或本节点还有流程没有处理完视为会签2
      if (lastProcesspermissionsrc.getIscounter().equals("Y") || count > 0) {
        iscounter = true;
      }
    } else
    // 根据当前任务key值，获取下一任务key值
    if (nextProcesspermissionsrc != null) {
      requestparam.setTaskDefKey(nextProcesspermissionsrc.getPermition());
      requestparam.setTaskName(nextProcesspermissionsrc.getTaskname());
      // 下一节点不是会签，则无需发送多次任务；下一节点是会签或本节点还有流程没有处理完视为会签2
      if (nextProcesspermissionsrc.getIscounter().equals("Y") || count > 0) {
        iscounter = true;
      }
    } else {
      // 没有下一个节点时可被视为生效
      //                url = "http://localhost:8090/assetdefaultdisposaltrust/test";
      url = "http://localhost:8080/yc/taskresult";
    }
    requestparam.setProcInstanceID(
        StringUtils.isNotBlank(requestparam.getProcInstanceID())
            ? requestparam.getProcInstanceID()
            : nowdate.getTime() + "");
    requestparam.setTaskID((new Date()).getTime() + "");
    requestparam.setTaskDealTellerNo(requestparam.getTaskDealTellerNo());
    requestparam.setBusiData(requestparam.getBusiData());

    responseparam.setPipRespCode("000000");
    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    if (iscounter) {
      Object param = requestparam.getBusiData().get("paramList");
      if (param != null) {
        Object jsonobj = JSONArray.toJSON(param);
        List<Param> dealuserlist =
            (List<Param>) JSONArray.parseArray(jsonobj.toString(), Param.class);
        for (Param dealuser : dealuserlist) {
          requestparam.setTaskID((new Date()).getTime() + "");
          requestparam.setTaskDealInstNo(dealuser == null ? null : dealuser.getKey());
          requestparam.setTaskDealTellerNo(null);
          if (count > 0) {

          } else {
            responseparam = getResponseParam(requestparam, url, responseparam);
            result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);
          }
        }
      }
    } else {
      responseparam = getResponseParam(requestparam, url, responseparam);
      result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);
    }

    responseobj = JSONArray.toJSON(result);
    logger.info("ProviderController--发送流程返回数据{}", responseobj);
    return responseobj;
  }

  private ResponseParam getResponseParam(
      @RequestBody RequestParam requestparam, String url, ResponseParam responseparam) {
    String json =
        JSONObject.toJSONStringWithDateFormat(
            requestparam, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);

    logger.info("发送接口：{}", url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);

    CloseableHttpResponse response = null;
    StringEntity stringEntity = new StringEntity(json, "UTF-8");
    stringEntity.setContentEncoding("UTF-8");
    stringEntity.setContentType("application/json");
    httpPost.setEntity(stringEntity);
    try {
      response = httpClient.execute(httpPost);
    } catch (IOException e) {
      e.printStackTrace();
    }
    HttpEntity entity = response.getEntity();
    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
      logger.debug("接口http未能成功返回错误码为{}", response.getStatusLine().getStatusCode());
    }

    try {
      String str = EntityUtils.toString(entity);
      logger.info("获取发送接口结果：{}", str);
      responseparam = JSONArray.parseObject(str, ResponseParam.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return responseparam;
  }

  @ApiOperation("撤销流程")
  @ApiImplicitParam("RequestParam参数：ProcInstanceID")
  @PostMapping("revokeProcess")
  public Object revokeProcess(@RequestBody RequestParam requestparam) {

    Object obj = JSONArray.toJSON(requestparam);

    logger.info("撤销流程请求参数{}" + obj.toString());

    ResponseParam responseparam = new ResponseParam();
    responseparam.setPipRespCode("000000"); // 响应码
    responseparam.setPipRespDesc("流程撤销成功"); // 响应描述
    responseparam.setRespStamp("2021-10-10");

    PortResult result = new PortResult(PortResult.RESULT_SUCCESS, "success", responseparam);

    Object responseobj = JSONArray.toJSON(result);
    logger.info("ProviderController--撤销流程数据{}", responseobj);

    return responseobj;
  }
}
