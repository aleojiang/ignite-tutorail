package pjiang.tutorial.ignite;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  10:09 2018/10/19.
 */
@Service("B")
public class MyFuckingService implements MyService {
    @Override
    @Cacheable(value = IgniteClientConfig.C2, key = "#p0")
    public String sayHello(String name) {
        return "I'm fucking " + name;
    }
}
