package br.com.fiap.agrodrones.producer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Config {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("");//TODO add hostname
            connectionFactory.setUsername("");//TODO add username
            connectionFactory.setPassword("");//TODO add password
            connectionFactory.setVirtualHost("");//TODO add virtualhost

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
