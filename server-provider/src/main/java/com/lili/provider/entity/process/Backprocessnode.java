package com.lili.provider.entity.process;

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
public class Backprocessnode {

    /**
     * 序号
     */
    private String number;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务退回节点
     */
    private String taskDefKey;
    /**
     * 任务开始时间
     */
    private String taskStartStamp;
    /**
     * 任务结束时间
     */
    private String taskEndStamp;
    /**
     * 任务处理系统代码
     */
    private String taskDealSysCode;
    /**
     * 任务处理机构号
     */
    private String taskDealInstNo;
    /**
     * 任务处理柜员号
     */
    private String taskDealTellerNo;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getTaskStartStamp() {
        return taskStartStamp;
    }

    public void setTaskStartStamp(String taskStartStamp) {
        this.taskStartStamp = taskStartStamp;
    }

    public String getTaskEndStamp() {
        return taskEndStamp;
    }

    public void setTaskEndStamp(String taskEndStamp) {
        this.taskEndStamp = taskEndStamp;
    }

    public String getTaskDealSysCode() {
        return taskDealSysCode;
    }

    public void setTaskDealSysCode(String taskDealSysCode) {
        this.taskDealSysCode = taskDealSysCode;
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
}
