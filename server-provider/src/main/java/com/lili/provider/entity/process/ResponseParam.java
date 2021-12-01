package com.lili.provider.entity.process;

import java.util.List;
import java.util.Map;

/**
 * @param
 * @author lijunyu
 * @Description: //响应参数
 * @return
 * @throws
 * @date ===========================================
 * 修改人：lijunyu，    修改时间：      修改版本：
 * 修改备注：
 * ===========================================
 */
public class ResponseParam {
    /**
     * 响应码
     */
    private String pipRespCode;
    /**
     * 响应描述
     */
    private String pipRespDesc;
    /**
     * 流程定义id
     */
    private String procDefineID;
    /**
     * 流程定义名称
     */
    private String procDefineName;
    /**
     * 流程实例id
     */
    private String procInstanceID;
    /**
     * 提供方会计日期
     */
    private String providerAcctiDate;
    /**
     * 响应时间戳
     */
    private String respStamp;
    /**
     * 响应码
     */
    private String respCode;
    /**
     * 响应描述
     */
    private String respDesc;
    /**
     * 是否已分配
     */
    private String claimFlag;
    /**
     * 节点数量
     */
    private Long nodeCount;

    /**
     * 	结果列表
     */
    private List<Backprocessnode> nodeList;

    /**
     * 记录条数
     */
    private Long recordCount;
    /**
     * 查询结果集
     */
    private List<Backprocessnode> taskList;
    /**
     * 流程实例图片
     */
    private byte[] procInstanceImg;

    private Map busidata;

    public Map getBusidata() {
        return busidata;
    }

    public void setBusidata(Map busidata) {
        this.busidata = busidata;
    }

    public String getPipRespCode() {
        return pipRespCode;
    }

    public void setPipRespCode(String pipRespCode) {
        this.pipRespCode = pipRespCode;
    }

    public String getPipRespDesc() {
        return pipRespDesc;
    }

    public void setPipRespDesc(String pipRespDesc) {
        this.pipRespDesc = pipRespDesc;
    }

    public String getProcDefineID() {
        return procDefineID;
    }

    public void setProcDefineID(String procDefineID) {
        this.procDefineID = procDefineID;
    }

    public String getProcDefineName() {
        return procDefineName;
    }

    public void setProcDefineName(String procDefineName) {
        this.procDefineName = procDefineName;
    }

    public String getProcInstanceID() {
        return procInstanceID;
    }

    public void setProcInstanceID(String procInstanceID) {
        this.procInstanceID = procInstanceID;
    }

    public String getProviderAcctiDate() {
        return providerAcctiDate;
    }

    public void setProviderAcctiDate(String providerAcctiDate) {
        this.providerAcctiDate = providerAcctiDate;
    }

    public String getRespStamp() {
        return respStamp;
    }

    public void setRespStamp(String respStamp) {
        this.respStamp = respStamp;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getClaimFlag() {
        return claimFlag;
    }

    public void setClaimFlag(String claimFlag) {
        this.claimFlag = claimFlag;
    }

    public Long getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Long nodeCount) {
        this.nodeCount = nodeCount;
    }

    public List<Backprocessnode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Backprocessnode> nodeList) {
        this.nodeList = nodeList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Backprocessnode> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Backprocessnode> taskList) {
        this.taskList = taskList;
    }

    public byte[] getProcInstanceImg() {
        return procInstanceImg;
    }

    public void setProcInstanceImg(byte[] procInstanceImg) {
        this.procInstanceImg = procInstanceImg;
    }
}

