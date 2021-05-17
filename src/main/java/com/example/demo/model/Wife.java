package com.example.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yubo
 * @description 一个女人
 * @date 2020/6/1
 */
@Component
@ConfigurationProperties(prefix = "wife")
public class Wife {
    private String high;//身高
    private String height;//体重

    @Override
    public String toString() {
        return "Wife{" +
                "high='" + high + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
