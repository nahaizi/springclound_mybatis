package com.lili.provider.entity.process;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @param
 * @author lijunyu
 * @Description: //请求参数
 * @return
 * @throws
 * @date ===========================================
 * 修改人：lijunyu，    修改时间：      修改版本：
 * 修改备注：
 * ===========================================
 */
@ApiModel("接口参数实体")
public class RequestParam {
    /**
     * 消费方会计日期
     */
    @ApiModelProperty("消费方会计日期")
    private String consumerAcctiDate;
    /**
     * 业务流水号
     */
    @ApiModelProperty(value = "业务流水号", example = "业务系统流水号，流程平台用之确认唯一一笔放款交易，各系统流水号长度最大60位")
    private String busiSriNo;
    /**
     * 重发标志
     */
    @ApiModelProperty(value = "重发标志", example = "0-否，1-是。默认填0")
    private String reSendFlag;
    /**
     * 请求时间戳
     */
    @ApiModelProperty(value = "请求时间戳", example = "格式为：YYYYMMDDhhmmssSSS因服务器时间有差异，仅作为参考" )
    private String reqStamp;
    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号", example = "流程发起机构代码" )
    private String instNo;
    /**
     * 操作柜员号
     */
    @ApiModelProperty(value = "操作柜员号", example = "操作柜员编码，系统用户名" )
    private String oprTellerNo;
    /**
     * 交易代码
     */
    @ApiModelProperty(value = "交易代码", example = "业务放款交易码，用于启动流程，流程平台根据系统代码+交易码+机构号确定唯一一个流程定义ID流程平台根据此识别关联流程模板的唯一标识" )
    private String txCode;
    /**
     * 业务数据
     */
    @ApiModelProperty(value = "业务数据", example = "响应时不填。交易具体的业务数据，流转到下一个任务。只有下一节点是“会签”时才填写，否则可不填写。" )
    private Map busiData;
    /**
     * 流程版本号
     */
    @ApiModelProperty(value = "流程版本号", example = "流程版本号" )
    private String flowableNo;
    /**
     * 流程数据
     */
    @ApiModelProperty(value = "流程数据", example = "流程流转所需的流程数据，如果下一个节点是分支节点，需要网关数据来判断走那个节点是，上送此“流程数据”。" )
    private Map gatewayData;


    /**
     * 流程定义id
     */
    @ApiModelProperty(value = "流程数据", example = "流程整合平台启动的流程文件编号，与业务交易对应" )
    private String procDefineID;
    /**
     * 流程实例id
     */
    @ApiModelProperty(value = "流程实例id", example = "流程整合平台生成流程实例编号，与该笔业务流水对应" )
    private String procInstanceID;
    /**
     * 流程实例批注内容
     */
    @ApiModelProperty(value = "流程实例批注内容", example = "补充说明任务提交的具体信息，例如同意放款等" )
    private String procInstancePostilContent;

    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id", example = "流程整合平台生成任务编号" )
    private String taskID;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称", example = "重复域。任务的中文名称" )
    private String taskName;
    /**
     * 任务处理机构号
     */
    @ApiModelProperty(value = "任务处理机构号", example = "任务由业务系统分配到柜员后填写。" )
    private String taskDealInstNo;
    /**
     * 任务处理柜员号
     */
    @ApiModelProperty(value = "任务处理柜员号", example = "任务由业务系统分配到柜员后填写。对应待办任务的“处理人”" )
    private String taskDealTellerNo;
    /**
     * 流程发起机构号
     */
    @ApiModelProperty(value = "流程发起机构号", example = "流程发起机构号。" )
    private String procStartInstNo;
    /**
     * 流程发起柜员号
     */
    @ApiModelProperty(value = "流程发起柜员号", example = "流程发起柜员号。" )
    private String procStartTellerNo;
    /**
     * 父流程实例id
     */
    @ApiModelProperty(value = "父流程实例id", example = "主流程包含子流程业务，主流程实例ID" )
    private String rootProcInstanceID;
    /**
     * 节点key
     */
    @ApiModelProperty(value = "节点key", example = "流程整合平台生成任务节点Key" )
    private String taskDefKey;

    /**
     * 任务批注内容
     */
    @ApiModelProperty(value = "任务批注内容", example = "补充说明任务提交的具体信息，例如同意放款等" )
    private String taskPostilContent;
    /**
     * 任务处理结果
     */
    @ApiModelProperty(value = "任务处理结果", example = "任务的处理状态0-拒绝，1-同意。" )
    private String taskDealResult;
    /**
     * 被退回节点编号
     */
    @ApiModelProperty(value = "被退回节点编号", example = "选择唯一一个任务退回的节点编号" )
    private String returnNodeNo;


    public String getConsumerAcctiDate() {
        return consumerAcctiDate;
    }

    public void setConsumerAcctiDate(String consumerAcctiDate) {
        this.consumerAcctiDate = consumerAcctiDate;
    }

    public String getBusiSriNo() {
        return busiSriNo;
    }

    public void setBusiSriNo(String busiSriNo) {
        this.busiSriNo = busiSriNo;
    }

    public String getReSendFlag() {
        return reSendFlag;
    }

    public void setReSendFlag(String reSendFlag) {
        this.reSendFlag = reSendFlag;
    }

    public String getReqStamp() {
        return reqStamp;
    }

    public void setReqStamp(String reqStamp) {
        this.reqStamp = reqStamp;
    }

    public String getInstNo() {
        return instNo;
    }

    public void setInstNo(String instNo) {
        this.instNo = instNo;
    }

    public String getOprTellerNo() {
        return oprTellerNo;
    }

    public void setOprTellerNo(String oprTellerNo) {
        this.oprTellerNo = oprTellerNo;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public Map getBusiData() {
        return busiData;
    }

    public void setBusiData(Map busiData) {
        this.busiData = busiData;
    }

    public String getFlowableNo() {
        return flowableNo;
    }

    public void setFlowableNo(String flowableNo) {
        this.flowableNo = flowableNo;
    }

    public Map getGatewayData() {
        return gatewayData;
    }

    public void setGatewayData(Map gatewayData) {
        this.gatewayData = gatewayData;
    }

    public String getProcDefineID() {
        return procDefineID;
    }

    public void setProcDefineID(String procDefineID) {
        this.procDefineID = procDefineID;
    }

    public String getProcInstanceID() {
        return procInstanceID;
    }

    public void setProcInstanceID(String procInstanceID) {
        this.procInstanceID = procInstanceID;
    }

    public String getProcInstancePostilContent() {
        return procInstancePostilContent;
    }

    public void setProcInstancePostilContent(String procInstancePostilContent) {
        this.procInstancePostilContent = procInstancePostilContent;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDealInstNo() {
        return taskDealInstNo;
    }

    public void setTaskDealInstNo(String taskDealInstNo) {
        this.taskDealInstNo = taskDealInstNo;
    }

    public String getTaskDealTellerNo() {
        return taskDealTellerNo;
    }

    public void setTaskDealTellerNo(String taskDealTellerNo) {
        this.taskDealTellerNo = taskDealTellerNo;
    }

    public String getProcStartInstNo() {
        return procStartInstNo;
    }

    public void setProcStartInstNo(String procStartInstNo) {
        this.procStartInstNo = procStartInstNo;
    }

    public String getProcStartTellerNo() {
        return procStartTellerNo;
    }

    public void setProcStartTellerNo(String procStartTellerNo) {
        this.procStartTellerNo = procStartTellerNo;
    }

    public String getRootProcInstanceID() {
        return rootProcInstanceID;
    }

    public void setRootProcInstanceID(String rootProcInstanceID) {
        this.rootProcInstanceID = rootProcInstanceID;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getTaskPostilContent() {
        return taskPostilContent;
    }

    public void setTaskPostilContent(String taskPostilContent) {
        this.taskPostilContent = taskPostilContent;
    }

    public String getTaskDealResult() {
        return taskDealResult;
    }

    public void setTaskDealResult(String taskDealResult) {
        this.taskDealResult = taskDealResult;
    }

    public String getReturnNodeNo() {
        return returnNodeNo;
    }

    public void setReturnNodeNo(String returnNodeNo) {
        this.returnNodeNo = returnNodeNo;
    }
}
