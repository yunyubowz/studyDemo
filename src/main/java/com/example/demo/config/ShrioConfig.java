package com.example.demo.config;

import com.example.demo.model.MyRealm;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@Configuration
public class ShrioConfig {


    /**
     * 注入 Shiro 过滤器
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        // 设置自定义的 securityManager
        shiroFilterFactoryBean.setSecurityManager((SecurityManager) securityManager);

        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilterFactoryBean.setLoginUrl("/open/login");
//        // 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/success");
//        // 设置未授权界面，权限认证失败会访问该 URL
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//
//        // LinkedHashMap 是有序的，进行顺序拦截器配置
       Map<String,String> filterChainMap = new LinkedHashMap<>();
//
//        // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
//        filterChainMap.put("/css/**", "anon");
//        filterChainMap.put("/imgs/**", "anon");
//        filterChainMap.put("/js/**", "anon");
//        filterChainMap.put("/swagger-*/**", "anon");
//        filterChainMap.put("/swagger-ui.html/**", "anon");
//        // 登录 URL 放行
        filterChainMap.put("/open/login", "anon");
        filterChainMap.put("/open/index", "authc");
        filterChainMap.put("/**", "authc");
//
//        // 以“/user/admin” 开头的用户需要身份认证，authc 表示要进行身份认证
//        filterChainMap.put("/user/admin*", "authc");
//        // “/user/student” 开头的用户需要角色认证，是“admin”才允许
//        filterChainMap.put("/user/student*/**", "roles[admin]");
//        // “/user/teacher” 开头的用户需要权限认证，是“user:create”才允许
//        filterChainMap.put("/user/teacher*/**", "perms[\"user:create\"]");
//
//        // 配置 logout 过滤器
//        filterChainMap.put("/logout", "logout");
//
//        // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        log.info("====shiroFilterFactoryBean注册完成====");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     * @return SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        // 将自定义 Realm 加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myAuthRealm());
        log.info("====securityManager注册完成====");
        return securityManager;
    }

    @Bean
    public MyRealm myAuthRealm() {
        MyRealm myRealm = new MyRealm();
        log.info("====myRealm注册完成=====");
        return myRealm;
    }

}
