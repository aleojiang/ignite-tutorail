package pjiang.tutorial.ignite;

import com.google.common.collect.Iterables;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.spring.SpringCacheManager;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  14:33 2018/10/18.
 */
@EnableCaching
@Configuration
@ConfigurationProperties("ignite")
public class IgniteClientConfig {
    
    public static final String C1 = "C1";
    public static final String C2 = "C2";
    public static final String C3 = "C3";
    
    private List<String> serverIpAddresses;
    private boolean clientMode;
    private long clientFailureTimeout;
    private long failureTimeout;
    
    public long getClientFailureTimeout() {
        return clientFailureTimeout;
    }
    
    public void setClientFailureTimeout(long clientFailureTimeout) {
        this.clientFailureTimeout = clientFailureTimeout;
    }
    
    public long getFailureTimeout() {
        return failureTimeout;
    }
    
    public void setFailureTimeout(long failureTimeout) {
        this.failureTimeout = failureTimeout;
    }
    
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
        cfg.setClientMode(clientMode);
        cfg.setClientFailureDetectionTimeout(clientFailureTimeout);
        cfg.setFailureDetectionTimeout(failureTimeout);
    
        cfg.setGridLogger(new Slf4jLogger(LoggerFactory.getLogger(Ignite.class)));
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        if (CollectionUtils.isEmpty(serverIpAddresses)) {
            throw new IllegalArgumentException("ipAddress should not be empty");
        }
        tcpDiscoveryVmIpFinder.setAddresses(serverIpAddresses);
        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);
        cfg.setDiscoverySpi(tcpDiscoverySpi);
        tcpDiscoverySpi.setClientReconnectDisabled(false);
        tcpDiscoverySpi.setReconnectCount(100);
    
        cfg.setCacheConfiguration(Iterables.toArray(cacheConfigurations(), CacheConfiguration.class));
    
        return cfg;
        
    }
    
    private List<CacheConfiguration> cacheConfigurations() {
        List<CacheConfiguration> cacheConfigs = new ArrayList<>();
        Duration ttl = Duration.ONE_MINUTE;
        cacheConfigs.add(touchedExpiryCacheConfiguration(ttl, C1));
        cacheConfigs.add(createdExpiryCacheConfiguration(ttl, C2));
        cacheConfigs.add(createdExpiryCacheConfiguration(ttl, C3));
        return cacheConfigs;
    }
    
    private CacheConfiguration touchedExpiryCacheConfiguration(Duration ttl, String cacheName) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration(cacheName);
        cacheConfiguration.setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(ttl));
        return cacheConfiguration;
    }
    
    private CacheConfiguration createdExpiryCacheConfiguration(Duration ttl, String cacheName) {
        CacheConfiguration cacheConfiguration = new CacheConfiguration(cacheName);
        cacheConfiguration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(ttl));
        return cacheConfiguration;
    }
    
    
//    @Bean(destroyMethod = "close")
//    Ignite igniteInstance(IgniteConfiguration igniteConfiguration) {
//        final Ignite ignite = Ignition.start(igniteConfiguration);
//        ignite.cluster().active();
//        return ignite;
//    }

    @Bean
    public CacheManager cacheManager() {
        SpringCacheManager springCacheManager = new SpringCacheManager();
        springCacheManager.setConfiguration(igniteConfiguration());
        return springCacheManager;
    }
    
}
