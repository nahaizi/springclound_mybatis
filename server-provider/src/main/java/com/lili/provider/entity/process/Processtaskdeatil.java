package com.lili.provider.entity.process;

import lombok.Data;

import java.util.Date;

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
@Data
public class Processtaskdeatil {
    private Long id;

    /**
     * 流程定义ID
     */
    private String procdefineid;

    /**
     * 流程实例ID
     */
    private String procinstanceid;
    /**
     * 任务ID
     */
    private String taskid;
    /**
     * 任务名称
     */
    private String taskname;
    /**
     * 业务数据
     */
    private String busidata;
    /**
     * 节点Key
     */
    private String taskdefkey;
    /**
     * 任务处理状态
     */
    private String dealstatus;
    /**
     * 处理人
     */
    private String dealusername;
    /**
     * 处理时间
     */
    private Date dealtime;

    /**
     * 创建人
     */
    private String creatorid;
    /**
     * 创建时间
     */
    private Date createdtime;
    /**
     * 修改人
     */
    private String lastmodifierid;
    /**
     * 修改时间
     */
    private Date lastmodifiedtime;
    /**
     * 操作名称
     */
    private String buttonname;

    /**
     * 操作理由
     */
    private String reason;
}
