package wechat.uitest;

import wechat.page.WebBasePage_03;

/**
 * 工厂方法，运行时创建具体领域的basePage
 * 具体领域的basePage就可以完成数据化的一些处理
 */
public class UiAutoFactory_04 {

    public static BasePage_01 create(String driverName){
        //如果driverName==web或者driverName==selenium，就返回一个新的WebBasePage_03对象
        if(driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage_03();
        }

        return null;
//        if(driverName.equals("app") || driverName.equals("appium")){
//            return new AppBasePage();
//        }
//
//        if(driverName.equals("uiautomator")){
//            return new AppBasePage();
//        }
//
//        if(driverName.equals("atx")){
//            return new AppBasePage();
//        }
//
//        if(driverName.equals("macaca")){
//            return new AppBasePage();
//        }

    }
}
