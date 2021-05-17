package com.example.demo.config;

import com.example.demo.service.HelloService;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yubo
 * @description springboot配置类
 * @date 2020/6/16
 */
@Configuration//指明当前类是一个配置类；就是来代替之前的spring配置
public class MyAppConfig {

    @Bean
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加组件了");
        return new HelloService();
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println("我是爸爸");
                Thread.sleep(5000);
                reentrantLock.unlock();
            }
        }.start();


        new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                while (reentrantLock.tryLock())
                System.out.println("我是爸爸");
                reentrantLock.unlock();
            }
        }.start();
    }
}
