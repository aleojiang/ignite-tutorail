package pjiang.tutorial.ignite;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  10:10 2018/10/19.
 */
@Configuration
public class MyServiceBeanFactoryConfig {
    
    @Bean
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
        serviceLocatorFactoryBean.setServiceLocatorInterface(MyServiceFactory.class);
        return serviceLocatorFactoryBean;
    }
    
//    @Bean
//    public MyServiceFactory myServiceFactory() throws Exception {
//        return (MyServiceFactory) serviceLocatorFactoryBean().getObject();
//    }
    
}
