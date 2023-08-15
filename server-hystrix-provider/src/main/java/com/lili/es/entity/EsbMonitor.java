package com.lili.es.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EsbMonitor implements Serializable {
    private String uuid;
    private String sysId;
    private String callSysId;
    private String serverIp;
    private String remoteIp;
    private String pubItemName;
    private String parentPubItemName;
    private String sessionId;
    private Integer orderNum;
    private String monitorId;
    private Date startTime;
    private Long duration;
    private String status;
    private String resultCode;
    private String resultDesc;
    private Long dataSizeIn;
    private Long dataSizeOut;
    private String gatewayCode;
    private String tokenId;
    private String clientId;
    private String serverId;
}
