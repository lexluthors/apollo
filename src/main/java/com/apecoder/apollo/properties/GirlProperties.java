package com.apecoder.apollo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "girl")
public class GirlProperties {

    private Integer aget;
    private String cupSize;

    public Integer getAget() {
        return aget;
    }

    public void setAget(Integer aget) {
        this.aget = aget;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }
}
