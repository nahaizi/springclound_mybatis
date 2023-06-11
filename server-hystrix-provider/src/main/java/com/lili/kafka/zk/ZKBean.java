package com.lili.kafka.zk;

import java.io.Serializable;

public class ZKBean implements Serializable {

    private static final long serialVersionUID = -6057956208558425192L;

    private int id = -1;

    private String name;

    private String zkURL;

    private String version = "0.8.2.2";

    private boolean jmxEnable;

    private String jmxAuthUsername;

    private String jmxAuthPassword;

    private boolean jmxWithSsl;

    private int zkConnectionTimeout = 30;

    private int maxRetryCount = 3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZkURL() {
        return zkURL;
    }

    public void setZkURL(String zkURL) {
        this.zkURL = zkURL;
    }

    public boolean isJmxEnable() {
        return jmxEnable;
    }

    public void setJmxEnable(boolean jmxEnable) {
        this.jmxEnable = jmxEnable;
    }

    public String getJmxAuthUsername() {
        return jmxAuthUsername;
    }

    public void setJmxAuthUsername(String jmxAuthUsername) {
        this.jmxAuthUsername = jmxAuthUsername;
    }

    public String getJmxAuthPassword() {
        return jmxAuthPassword;
    }

    public void setJmxAuthPassword(String jmxAuthPassword) {
        this.jmxAuthPassword = jmxAuthPassword;
    }

    public boolean isJmxWithSsl() {
        return jmxWithSsl;
    }

    public void setJmxWithSsl(boolean jmxWithSsl) {
        this.jmxWithSsl = jmxWithSsl;
    }

    public int getZkConnectionTimeout() {
        return zkConnectionTimeout;
    }

    public void setZkConnectionTimeout(int zkConnectionTimeout) {
        this.zkConnectionTimeout = zkConnectionTimeout;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZKBean) {
            ZKBean anotherZKCluster = (ZKBean) obj;
            return this.id == anotherZKCluster.getId();
        } else {
            return false;
        }
    }

}
