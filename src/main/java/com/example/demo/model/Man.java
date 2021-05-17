package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

/**
 * @author yubo
 * @description 一个男人
 * @date 2020/6/1
 */
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @configurationproperties：告诉Springboot将本类中的所有属性和配置文件中相关的配置进行绑定
 * @Description //TODO
 * @Date 11:18 2020/6/1
 * @Param
 * @return
 **/
//@PropertySource(value={"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "man")
@Validated
public class Man {
    /**
     * 字面量${key}从环境变量、配置文件中获取值#{SpEL}
     **/
    @Value("${man.high}")
    //@Email
    private String high;//升高
    @Value("69kg")
    private String height;//体重
    @Value("#{9*2}cm")
    private String longs;//长度
    //@Value("${man.family}")
    private Map<Object,Object> family;//家庭
    @Value("1,2,4")
    private List<String> friend;//朋友
    private Wife wife;

    @Override
    public String toString() {
        return "Man{" +
                "high='" + high + '\'' +
                ", height='" + height + '\'' +
                ", longs='" + longs + '\'' +
                ", family=" + family +
                ", friend=" + friend +
                ", wife=" + wife +
                '}';
    }

    public Map<Object, Object> getFamily() {
        return family;
    }

    public void setFamily(Map<Object, Object> family) {
        this.family = family;
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

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public List<String> getFriend() {
        return friend;
    }

    public void setFriend(List<String> friend) {
        this.friend = friend;
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
