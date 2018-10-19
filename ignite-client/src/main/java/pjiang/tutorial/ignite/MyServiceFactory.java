package pjiang.tutorial.ignite;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  10:16 2018/10/19.
 */
public interface MyServiceFactory {
    MyService getMyService(MyType type);
}
