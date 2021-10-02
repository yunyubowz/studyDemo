package com.example.demo.distributedSystem.rabbitMQDemo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {
    static ConnectionFactory connectionFactory = new ConnectionFactory();
    static Connection connection = null;
    static {
        connectionFactory.setHost("192.168.133.130");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("yunyubo");
        connectionFactory.setPassword("yunyubo1532%");
        connectionFactory.setVirtualHost("Demo1");
    }

    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        try{
            connection = connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
