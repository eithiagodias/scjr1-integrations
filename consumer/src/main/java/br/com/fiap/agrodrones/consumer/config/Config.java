package br.com.fiap.agrodrones.producer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Config {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("xxx");//TODO add hostname
            connectionFactory.setUsername("xxx");//TODO add username
            connectionFactory.setPassword("xxx");//TODO add password
            connectionFactory.setVirtualHost("xxxx");//TODO add virtualhost

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
