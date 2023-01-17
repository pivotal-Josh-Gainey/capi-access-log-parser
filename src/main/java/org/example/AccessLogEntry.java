package org.example;

import java.math.BigDecimal;

public class AccessLogEntry {
    private String host;
    private String path;
    private String fullUrl;
    private BigDecimal responseTime;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public BigDecimal getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(BigDecimal responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return host + ',' +
                path + ',' +
                fullUrl + ',' +
                responseTime;
    }
}

