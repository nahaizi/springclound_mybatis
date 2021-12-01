package com.lili.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyh
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Processinstance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 流程编号
     */
    private String processno;

    /**
     * 业务数据id
     */
    private Long busisrino;

    /**
     * 流程实例id
     */
    private String procinstanceid;

    /**
     * 流程实例状态
     */
    private String dealstatus;

    /**
     * 业务类型
     */
    private String businesstype;

    /**
     * 业务名称
     */
    private String businessname;

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
     * 重发标识
     */
    private String resendflag;

    /**
     * 重发标识
     */
    private String productcode;

    /**
     * 流程照片
     */
    private String processimage;


}
