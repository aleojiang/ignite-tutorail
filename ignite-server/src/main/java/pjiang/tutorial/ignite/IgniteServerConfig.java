package pjiang.tutorial.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  14:33 2018/10/18.
 */
@Configuration
@ConfigurationProperties("ignite")
public class IgniteServerConfig {
    
    private List<String> serverIpAddresses;
    private boolean clientMode;
    
    public boolean isClientMode() {
        return clientMode;
    }
    
    public void setClientMode(boolean clientMode) {
        this.clientMode = clientMode;
    }
    
    public List<String> getServerIpAddresses() {
        return serverIpAddresses;
    }
    
    public void setServerIpAddresses(List<String> serverIpAddresses) {
        this.serverIpAddresses = serverIpAddresses;
    }
    
    @Bean
    IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        
        cfg.setIgniteInstanceName("IgniteServer");
        
        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        
        tcpDiscoveryVmIpFinder.setAddresses(serverIpAddresses);
        
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);
        
        cfg.setClientMode(clientMode);
        
        return cfg;
        
    }
    
    @Bean(destroyMethod = "close")
    Ignite igniteInstance(IgniteConfiguration igniteConfiguration) {
        final Ignite ignite = Ignition.start(igniteConfiguration);
        ignite.cluster().active();
        return ignite;
    }
    
}
