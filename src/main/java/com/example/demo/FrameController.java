package com.example.demo;

import com.example.demo.config.MyAppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("open")
public class FrameController {
    public static void main(String[] args) {
        //通过文件对bean实现注入
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
        System.out.println(ctx.getBean("car"));
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);
        System.out.println(configApplicationContext.getBean("helloService"));
    }
    /**
     * 公用页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index.html";
    }

    /**
     * 公用页面
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login.html";
    }
}
