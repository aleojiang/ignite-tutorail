package pjiang.tutorial.ignite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  16:45 2018/10/18.
 */
@RestController
@RequestMapping("/test")
public class MyController {
    
    @Autowired
    private MyServiceFactory myServiceFactory;
    
    @RequestMapping("/{type}/{name}")
    public String sayHello(@PathVariable("type") String type, @PathVariable("name") String name) {
        MyType myType = MyType.fromString(type);
        return myServiceFactory.getMyService(myType).sayHello(name);
    }
}
