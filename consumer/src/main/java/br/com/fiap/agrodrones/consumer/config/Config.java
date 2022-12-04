package br.com.fiap.agrodrones.producer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Config {
    private static CachingConnectionFactory connectionFactory;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("jackal.rmq.cloudamqp.com");//TODO add hostname
            connectionFactory.setUsername("ydplgzhw");//TODO add username
            connectionFactory.setPassword("ZM2BGRHcsmNXLi3aaeqeCxwQyzP47Yhs");//TODO add password
            connectionFactory.setVirtualHost("ydplgzhw:ydplgzhw");//TODO add virtualhost

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }
}
